package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @date 2020/7/17 20:32
     * @description:
     *      查询某一个测绘单位的项目审核所有信息
     * @param userId 测绘单位中的userId
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAllAuditByUserId")
    public ResultData queryAllAuditByUserId(@RequestParam("userId") Long userId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                            @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize){
        return iProjectService.queryAllAuditByUserId(userId,pageNum,pageSize);
    }

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

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 11:13
     * @description:
     *      对单位的各种信息更改之后提交审核
     * @param mappingUnit
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/submitAuditOfMappingUnit")
    public ResultData submitAuditOfMappingUnit(@RequestBody MappingUnit mappingUnit){
        return iProjectService.submitAuditOfMappingUnit(mappingUnit);
    }
}
