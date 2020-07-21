package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Principal;
import com.aaa.service.PrincipalService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:41
 * @Description
 *      负责人信息
 **/
@RestController
public class PrincipalController extends CommonController<Principal> {
    @Autowired
    private PrincipalService principalService;


    @Override
    public BaseService<Principal> getBaseService() {
        return principalService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 10:21
     * @description:
     *      查询测绘单位的法人信息
     * @param userId
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryPrincipalByUserId")
    public ResultData queryPrincipalByUserId(@RequestParam("userId") Object userId, @RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = principalService.queryPrincipalByUserId(Long.parseLong(userId.toString()), pageNum, pageSize);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 20:47
     * @description:
     *      添加principal
     * @param principal
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertPrincipalByUserId")
    public ResultData insertPrincipalByUserId(@RequestBody Principal principal){

        //TODO 未完成
        return  null;
    }


}
