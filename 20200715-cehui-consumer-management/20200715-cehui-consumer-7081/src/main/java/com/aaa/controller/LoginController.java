package com.aaa.controller;

import com.aaa.annotation.LoginAnnotation;
import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 14:43
 * @Description
 **/
@RestController
public class LoginController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 15:10
     * @description:
     *      执行登录操作
     * @param user
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/doLogin")
    @LoginAnnotation(operationType = "登录操作",operationName = "管理员登录")
    public ResultData doLogin(@RequestBody User user){
        System.out.println(user.getUsername()+"-"+user.getPassword());
        return  iProjectService.doLogin(user);
    }

}
