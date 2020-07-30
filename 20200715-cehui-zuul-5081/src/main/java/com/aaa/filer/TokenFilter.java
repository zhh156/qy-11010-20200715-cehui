package com.aaa.filer;

import com.aaa.utils.GetParamsUtils;
import com.aaa.utils.PostParamsUtils;
import com.aaa.utils.SendParams2Controller;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/26 13:52
 * @Description
 *      过滤登录时token中的参数
 **/
public class TokenFilter  extends ZuulFilter {
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/26 13:54
     * @description:
     *      过滤器的类型，一共有四个类型
     *          pre：到达路由之前执行
     *          routing：刚到达路由还未开始执行路由中的逻辑执行
     *          post：执行完路由中的逻辑准备离开之前执行
     *          error：路由中抛出错误执行（仅限于路由中的错误）
     * @param
     * @return java.lang.String
     **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/26 13:59
     * @description:
     *      指定过滤器执行的顺序。
     *          数字越小越先执行。负数也是这个规则（0和-1的话，-1先执行）
     * @param
     * @return int
     **/
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/26 14:03
     * @description:
     *      是否需要开启过滤器，true为启用；false是不启用。
     *      这里需要些业务逻辑：
     *          值是写是否让过滤器生效
     * @param
     * @return boolean
     **/
    @Override
    public boolean shouldFilter() {
        //zuul无论如何都不能获取到路由中所传递的参数，必须要从请求头里获取信息
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String params = GetParamsUtils.getParams(request);
        //判断里边是不是传了token，如果存在token说明需要进行验证是否已经登录；如果没有token说明仅仅是跳转页面
        if(params.contains("token") && (params.contains("http://") || params.contains("https://"))){
            //确定里边传了token
            return true;
        }
        //确定没有传token，不需要进行验证
        try {
            SendParams2Controller.sendParams(PostParamsUtils.postParams(requestContext),requestContext,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/26 15:26
     * @description:
     *      过滤的业务逻辑
     * @param
     * @return java.lang.Object
     **/
    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
