package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 9:21
 * @Description
 **/
@RestController
public class MappingUintController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 9:28
     * @description:
     *      查询所有的测绘单位信息
     * @param
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAll")
    public ResultData queryAll(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(name="pageSize",defaultValue =  "10") Integer pageSize){
        return iProjectService.queryAll(pageNum,pageSize);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 17:32
     * @description:
     *      根据单位名进行模糊查询
     * @param unitName
     * @return TokenVo
     **/
    @GetMapping("/queryMappingUnitByUnitName")
    public ResultData queryMappingUnitByUnitName(@RequestParam("unitName") String unitName){
        return iProjectService.queryMappingUnitByUnitName(unitName);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 10:09
     * @description:
     *      查询单个数据信息
     * @param mappingUnit
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/queryOneMappingUnit")
    public ResultData queryOneMappingUnit(@RequestBody MappingUnit mappingUnit){
        return iProjectService.queryOneMappingUnit(mappingUnit);
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
    public ResultData queryPrincipalByUserId(@RequestParam("userId") Object userId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(name="pageSize",defaultValue =  "10") Integer pageSize){
        return iProjectService.queryPrincipalByUserId(userId,pageNum,pageSize);
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
    public ResultData queryTechnicistByUserId(@RequestParam("userId")Object userId, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                              @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize){
        return iProjectService.queryTechnicistByUserId(userId,pageNum,pageSize);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:26
     * @description:
     *      查询某个单位的仪器设备信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryEquipmentByUserId")
    public ResultData queryEquipmentByUserId(@RequestParam("userId")Object userId, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize){
        return iProjectService.queryEquipmentByUserId(userId,pageNum,pageSize);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:36
     * @description:
     *      查询某个单位的特殊人员信息
     * @param userId
	 * @param pageNum
	 * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/querySpecialPostByUserId")
    public ResultData querySpecialPostByUserId(@RequestParam("userId")Object userId, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize){
        return iProjectService.querySpecialPostByUserId(userId,pageNum,pageSize);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 14:29
     * @description:
     *      查询某个测绘单位的全部资源信息
     * @param id
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryResourceById")
    public ResultData queryResourceById(@RequestParam("id")Object id){
        return iProjectService.queryResourceById(id);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 16:31
     * @description:
     *      通过测绘单位的主键id查询分值记录信息（包括附件）
     * @param id
     * @param pageNum
     * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAllScoreById")
    public ResultData queryAllScoreById(@RequestParam("id")Object id,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize){
        return iProjectService.queryAllScoreById(id,pageNum,pageSize);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 15:55
     * @description:
     *      根据百分比和所在区域对单位进行查询
     * @param percentage 查询的百分比
     * @param ownedDistrict 查询的地区
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryMappingUnitByPercentage")
    public ResultData queryMappingUnitByPercentage(@RequestParam("percentage")Double percentage,@RequestParam("ownedDistrict")String ownedDistrict,
                                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                   @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize){
        return iProjectService.queryMappingUnitByPercentage(percentage,ownedDistrict,pageNum,pageSize);
    }


    //TODO feign中无法实现文件的上传，需要添加东西将文件上传到provider上
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 21:23
     * @description:
     *      通过测绘单位的主键id插入分值记录
     * @param unitId 进行改变分值的id
     * @param operationType 增加(1)/减少(2)
     * @param file 上传的附件
     * @param scoreChange 改变的分值
     * @param reason  分值变化的原因
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertScoreById")
    public ResultData insertScoreById(@RequestParam("unitId")Long unitId, @RequestParam("operationType")String operationType,
                                      @RequestParam("file") MultipartFile file, @RequestParam("scoreChange")Integer scoreChange,
                                      @RequestParam("reason")String reason){
        return iProjectService.insertScoreById(unitId,operationType,file,scoreChange,reason);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 19:39
     * @description:
     *      查询修改待审核的单位
     * @param pageNum 当前页数
     * @param pageSize 每页的条数
     * @param unitName  单位的名称（模糊查询）
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryMappingUnitByAuditStatusAndNoCertificate")
    public ResultData queryMappingUnitByAuditStatusAndNoCertificate(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                    @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize,@RequestParam("unitName")String unitName){
        return iProjectService.queryMappingUnitByAuditStatusAndNoCertificate(pageNum,pageSize,unitName);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 19:39
     * @description:
     *      查询修改待审核的单位
     * @param pageNum 当前页数
     * @param pageSize 每页的条数
     * @param unitName  单位的名称（模糊查询）
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryMappingUnitByAuditStatusAndCertificate")
    public ResultData queryMappingUnitByAuditStatusAndCertificate(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                    @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize,@RequestParam("unitName")String unitName){
        return iProjectService.queryMappingUnitByAuditStatusAndCertificate(pageNum,pageSize,unitName);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/22 20:29
     * @description:
     *          系统主页中的测绘单位查询
     * @param unitName 单位名称（模糊查询）
     * @param ownedDistrict 单位地域
     * @param qualificationLevel 单位资质
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryMappingUnitMain")
    public ResultData queryMappingUnitMain(@RequestParam("unitName") String unitName,@RequestParam("ownedDistrict") String ownedDistrict,@RequestParam("qualificationLevel") String qualificationLevel){
        return iProjectService.queryMappingUnitMain(unitName,ownedDistrict,qualificationLevel);
    }

}
