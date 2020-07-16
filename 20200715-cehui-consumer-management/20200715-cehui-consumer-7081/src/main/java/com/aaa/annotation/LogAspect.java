package com.aaa.annotation;

import com.aaa.model.LoginLog;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import com.aaa.utils.AddressUtils;
import com.aaa.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static com.aaa.staticproperties.TimeFormatProperties.TIME_FORMAT;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 16:51
 * @Description
 *      AOP
 *      @Slf4j：
 *          simple log for java
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 17:10
     * @description:
     *      定义切点信息
     *      这个时候不能再按照常规的切点（service/controller）
     * @param
     * @return void
     **/
    @Pointcut("@annotation(com.aaa.annotation.LoginAnnotation)")
    public void poincut(){
        //TODO nothing to do
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 18:06
     * @description:
     *      定义环形切面（就是具体来实现业务逻辑的方法）
     * @param proceedingJoinPoint 封装了目标路径中的所用到的所有参数
     * @return java.lang.Object
     **/
    @Around("poincut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException {
        Object result = null;
        try {
            //.proceed()：让目标方法执行
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //后置处理，将操作的信息放入到日志表中进行记录
        LoginLog loginLog = new LoginLog();
        //1.获取ip地址
        //获取Request对象
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        //String ipAddr = IPUtils.getIpAddr(request);需要一个HttpServletRequest对象
        String ipAddr="120.78.182.121";
        loginLog.setIp(ipAddr);
        //2.获取地理位置
        Map<String, Object> addresses = AddressUtils.getAddresses(ipAddr, "UTF-8");
        loginLog.setLocation(addresses.get("province")+"|"+addresses.get("key"));

        //3.获取Username--->想要获取username，必须要获取到目标方法的参数值（user.getUserName）
        Object[] args = proceedingJoinPoint.getArgs();
        User user = (User)args[0];
        loginLog.setUsername(user.getUsername());



        //4.获取操作类型以及具体操作的内容（反射）
        //4.1.获取目标类名（全限定名）
        String tarClassName = proceedingJoinPoint.getTarget().getClass().getName();
        String tarMethodName = proceedingJoinPoint.getSignature().getName();
        //4.2.获取类对象
        Class tarClass = Class.forName(tarClassName);
        //4.3.获取目标类中的所有方法
        Method[] methods = tarClass.getMethods();
        String operationType = "";
        String operationName = "";

        for (Method method : methods) {
            String methodName = method.getName();
            if(tarMethodName.equals(methodName)){
                //这个时候虽然一斤确定了目标方法没有问题，但是有可能会出现方法的重载
                //还需要对方法的参数列表进行判断
                //4.4.获取目标方法的参数
                Class[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == args.length){
                    //获取目标方法
                    operationType = method.getAnnotation(LoginAnnotation.class).operationType();
                    operationName = method.getAnnotation(LoginAnnotation.class).operationName();
                }
            }
        }
        loginLog.setOperationType(operationType);
        loginLog.setOperationName(operationName);
        //5.获取登录的时间
        String date = DateUtils.formatDate(new Date(), TIME_FORMAT);
        loginLog.setLoginTime(date);

        iProjectService.addLoginLog(loginLog);
        return result;
    }
}
