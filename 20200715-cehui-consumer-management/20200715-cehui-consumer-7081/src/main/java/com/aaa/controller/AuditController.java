package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/20 20:16
 * @Description
 **/
@RestController
public class AuditController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 20:03
     * @description:
     *      更新审核表中的记录
     * @param userId 从单位表中的userId获取到
     * @param status 审核的状态（0-通过；1-拒绝）
     * @param memo 备注
     * @return com.aaa.vo.TokenVo
     **/
    @GetMapping("/updateAuditByUserId")
    public ResultData updateAuditByUserId(@RequestParam("userId") Long userId, @RequestParam("status") Integer status, @RequestParam("memo")String memo){
        return iProjectService.updateAuditByUserId(userId,status,memo);
    }
}
