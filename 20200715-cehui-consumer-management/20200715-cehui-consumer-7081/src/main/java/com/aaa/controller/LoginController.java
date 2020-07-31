package com.aaa.controller;

import com.aaa.annotation.LoginAnnotation;
import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import com.aaa.utils.GetZuulFilterParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 14:43
 * @Description
 **/
@Controller
public class LoginController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/30 17:40
     * @description:
     *      跳转到登录界面
     * @param
     * @return java.lang.String
     **/
    @GetMapping("/turnLoginPage")
    public String turnLoginPage(){
        return "login";
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 15:10
     * @description:
     *      执行登录操作
     * @param request
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/doLogin")
    @ResponseBody
    @LoginAnnotation(operationType = "登录操作",operationName = "管理员登录")
    public ResultData doLogin(HttpServletRequest request){
        Map params = GetZuulFilterParamsUtils.getParams(request);
        if(null == params || params.isEmpty()){
            return super.loginFailed();
        }
        return  iProjectService.doLogin(params);
    }

}
