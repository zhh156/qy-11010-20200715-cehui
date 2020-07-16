package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.model.LoginLog;
import com.aaa.vo.TokenVo;
import org.springframework.stereotype.Service;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 16:59
 * @Description
 **/
@Service
public class LoginLogService extends BaseService<LoginLog> {
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 20:36
     * @description:
     *      存储日志数据
     * @param loginLog
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo loginLog(LoginLog loginLog){
        TokenVo tokenVo = new TokenVo();
        if(loginLog.getUsername() == null || loginLog.getLoginTime() == null){
            return tokenVo.setType(4).setIsSuccess(false);
        }
        return tokenVo.setIsSuccess(true);
    }
}
