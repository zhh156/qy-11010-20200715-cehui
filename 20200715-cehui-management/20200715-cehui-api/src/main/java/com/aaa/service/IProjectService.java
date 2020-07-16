package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.LoginLog;
import com.aaa.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 15:02
 * @Description
 **/
@FeignClient(value = "CEHUI-INTERFACE-PROVIDER")
public interface IProjectService {
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 15:04
     * @description:
     *      执行登录操作
     * @param user
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody User user);

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/15 16:57
     * @description:
     *      新增日志
     * @param loginLog
     * @return java.lang.Integer
     **/
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLog loginLog);
}
