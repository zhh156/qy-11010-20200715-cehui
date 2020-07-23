package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Technicist;
import com.aaa.service.AuditService;
import com.aaa.service.MappingUnitService;
import com.aaa.service.TechnicistService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:44
 * @Description
 *      技术人员
 **/
@RestController
public class TechnicistController extends CommonController<Technicist> {
    @Autowired
    private TechnicistService technicistService;
    @Autowired
    private MappingUnitService mappingUnitService;
    @Autowired
    private AuditService auditService;
    @Override
    public BaseService<Technicist> getBaseService() {
        return technicistService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:13
     * @description:
     *      查询某个单位的技术人员信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryTechnicistByUserId")
    public ResultData queryTechnicistByUserId(@RequestParam("userId")Object userId, @RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = technicistService.queryTechnicistByUserId(Long.parseLong(userId.toString()), pageNum, pageSize);
        if(pageInfo != null ){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 19:45
     * @description:
     *      添加技术人员信息
     * @param technicist
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertTechnicistByUserId")
    public ResultData insertTechnicistByUserId(@RequestBody Technicist technicist){
        TokenVo tokenVo = technicistService.insertTechnicistByUserId(technicist, mappingUnitService,auditService);
        if(tokenVo.getIsSuccess()){
            return addSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 3){
            return updateFailed("单位信息修改失败");
        }
        if(tokenVo.getOperation() == 4){
            return addFailed("技术人员添加失败");
        }
        return addFailed("系统异常");
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:05
     * @description:
     *      修改技术人员的信息
     * @param technicist
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist){
        TokenVo tokenVo = technicistService.updateTechnicist(technicist, mappingUnitService,auditService);
        if(tokenVo.getIsSuccess()){
            return addSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 3){
            return updateFailed("单位信息修改失败");
        }
        if(tokenVo.getOperation() == 4){
            return addFailed("技术人员修改失败");
        }
        return addFailed("系统异常");
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:26
     * @description:
     *      删除技术人员的信息
     * @param technicist
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteTechnicist")
    public ResultData deleteTechnicist(@RequestBody Technicist technicist){
        TokenVo tokenVo = technicistService.deleteTechnicist(technicist, mappingUnitService,auditService);
        if(tokenVo.getIsSuccess()){
            return deleteSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 3){
            return deleteFailed("单位信息删除失败");
        }
        if(tokenVo.getOperation() == 4){
            return deleteFailed("技术人员修改失败");
        }
        if(tokenVo.getOperation() == 1){
            return deleteFailed("技术人员不存在");
        }
        return deleteFailed("系统异常");
    }
}
