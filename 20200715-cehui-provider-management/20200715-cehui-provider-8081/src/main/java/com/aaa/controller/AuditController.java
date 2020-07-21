package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Audit;
import com.aaa.model.MappingUnit;
import com.aaa.service.AuditService;
import com.aaa.service.MappingUnitService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 20:26
 * @Description
 **/
@RestController
public class AuditController extends CommonController<Audit> {
    @Autowired
    private AuditService auditService;
    @Autowired
    private MappingUnitService mappingUnitService;
    @Override
    public BaseService<Audit> getBaseService() {
        return auditService;
    }
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
    public ResultData queryAllAuditByUserId(@RequestParam("userId") Object userId,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        PageInfo<Map> pageInfo = auditService.queryAllAuditByUserId(userId, pageNum, pageSize);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
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
    public ResultData updateAuditByUserId(@RequestParam("userId") Long userId,@RequestParam("status") Integer status,@RequestParam("memo")String memo){
        //1.对审核信息进行更改
        TokenVo tokenVo = auditService.updateAuditByUserId(userId, status, memo);
        //2.判断审核信息与单位信息是否修改成功
        if(tokenVo.getIsSuccess()){
            //成功
            //3.获取修改后的信息
            Audit audit = (Audit)tokenVo.getData();
            //4.对单位的信息进行修改
            MappingUnit mappingUnit = new MappingUnit().setUserId(audit.getUserId());
            Integer audiStatus = audit.getStatus();
            //4.1.当auditStatus为null时，说明没有进行审核，不需要对单位信息进行修改
            if(audiStatus != null){
                mappingUnit.setAuditStatus(audiStatus).setModifyTime(new Date());
                //4.2.单位信息进行修改
                TokenVo tokenVo1 = mappingUnitService.updateOfAudit(mappingUnit);
                //5.判断单位信息修改是否成功
                if(tokenVo1.getIsSuccess()){
                    //成功
                    return updateSuccess(tokenVo);
                }
            }
        }
        return updateFailed();
    }
}
