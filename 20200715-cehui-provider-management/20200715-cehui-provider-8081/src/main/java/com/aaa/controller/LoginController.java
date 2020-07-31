package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.LoginService;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 15:05
 * @Description
 **/
@RestController
public class LoginController extends CommonController<User> {
    @Autowired
    private LoginService loginService;

    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 15:06
     * @description:
     *      执行登录操作
     * @param map
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody Map map){
        TokenVo doLogin = loginService.doLogin(map);
        if(doLogin.getIsSuccess()){
            return super.loginSuccessData(doLogin.getToken());
        }else{
            switch (doLogin.getType()){
                case 1: return super.loginUserNoExist(null);
                case 2: return super.loginPasswordWrong(null);
                case 4: return super.systemWrong(null);
            }
        }
        return super.systemWrong(null);
    }
}
