package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.LoginLog;
import com.aaa.service.LoginLogService;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 16:58
 * @Description
 **/
@RestController
public class LoginLogController extends CommonController<LoginLog> {
    @Autowired
    private LoginLogService loginLogService;
    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog){
        TokenVo tokenVo = loginLogService.loginLog(loginLog);
        if(tokenVo.getIsSuccess()){
            return 1;
        }
        return 0;
    }
}
