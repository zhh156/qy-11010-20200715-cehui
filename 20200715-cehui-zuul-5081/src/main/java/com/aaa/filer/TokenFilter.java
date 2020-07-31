package com.aaa.filer;

import com.aaa.utils.GetParamsUtils;
import com.aaa.utils.PostParamsUtils;
import com.aaa.utils.SendParams2ControllerUtils;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

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
     *          只是写是否让过滤器生效
     * @param
     * @return boolean
     **/
    @Override
    public boolean shouldFilter() {
        /**
         * 需求：
         *      这个过滤器是用来验证token是否正确的过滤器
         *      但是当用户需要进行登录的时候，该过滤器可以不生效
         *      也就是说只要判断到客户端地址栏中是否有login即可
         */
        //1.应该先获取HttpServletRequest对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //2.判断用户的请求方式（如果是跳转页面，则是GET请求方式并且没有参数；如果是执行登录操作，请求方式一定是POST请求方式，并且一定会有请求参数）
        String method = request.getMethod();
        String requestUrl = request.getRequestURL().toString();
        /**
         * 这一种形式虽然可以解决是否启动路由，但是会出现高并发的情况
         *      只要用户的请求是get，并且携带了http，就一定会执行该方法
         *      当用户较多的时候，就存在并发的情况，那么就意味着路由的QPS就比较高
         *      QPS:并发
         *      当QPS比较高的时候，路由的性能就会瞬间下降
         *
         * 这个时候我们可以增加一个判断条件，判断一下路径中是否包含doLogin/login
         *
         * 你的路由需要抗多高的QPS？
         *      不要太夸张
         *      峰值5000就可以了
         *
         *      项目难点：
         *            在写这个项目的时候，因为真实的环境中并没有使用到eureka，所以为了防止一写简单的并发，
         *            还需要使用到ribbon
         *            --->ribbon脱离eureka的时候，需要去配置ribbon项目的地址，然后使用consumer去调用的时候
         *            发现报错了--->发现找不到localhost--->这个问题就在于没有内网穿透
         *              解决内网穿透：
         *                  1.使用花生壳（但是花生壳收费）--->不好用，因为要收费
         *                  2.Ngrok（开源，免费）--->不适用于中国（网速绝对会非常卡，根本用不成）
         *                  3.NATAPP（内部底层其实就是Ngrok），其实2和3的使用方式一摸一样，只是2针对于国内无法使用
         *                          3可以直接用于国内服务器
         *
         */
        if(requestUrl.contains("/turnLoginPage") && ("GET".equals(method.toUpperCase()) && (requestUrl.contains("http://") || requestUrl.contains("https://")))){
            /**
             * 项目难点：
             *      zuul的内部实现原理是servlet，而且在控制servlet的时候可能是我的经验不足，没有理解设计
             *      者的意思，发现request对象是全局对象，而不是具体的某个线程对象
             *      造成线程中的request参数
             *      无法获取到request.getParameter("key")
             *      所以需要自己封装工具类来解决获取线程request对象
             */
            String params = GetParamsUtils.getParams(request);
            if(params.equals("")){
                SendParams2ControllerUtils.sendParams(null,requestContext,request);
                return false;
            }
        }
        /**
         * 另外一种可能性：
         *      如果客户端执行的是登录操作，那么使用的是POST请求，而且还一定会有参数(User对象)
         */
        if(requestUrl.contains("/doLogin") && (method.toUpperCase().equals("POST") && (requestUrl.contains("http://") || requestUrl.contains("https://")))){
            //判断获取POST方式中如果不等于null，则说明用户要执行登录操作
            JSONObject json = PostParamsUtils.postParams(requestContext);
            if(json != null){
                //说明有数据传进来了（User）--->说明用户要执行登录操作
                //return false说明并不需要启动路由过滤器，也就是说并不需要执行下面的哪个run()
                /**
                 * 这个时候并不会直接跳转到目标路径（/doLogin）
                 * 所以需要手动跳转，所以需要重写跳转源码（需要自定义工具类让他实现跳转）
                 */
                SendParams2ControllerUtils.sendParams(json,requestContext,request);
                return false;
            }
        }
        return true;
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
