package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.MappingUnitService;
import com.aaa.service.ResourceService;
import com.aaa.service.ScoreService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 9:20
 * @Description
 *      测绘单位
 **/
@RestController
public class MappingUintController extends CommonController<MappingUnit> {
    @Autowired
    private MappingUnitService mappingUnitService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ResourceService resourceService;
    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 9:28
     * @description:
     *      查询所有的测绘单位信息
     * @param
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAll")
    public ResultData queryAll(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        PageInfo<MappingUnit> mappingUnitPageInfo = mappingUnitService.queryAll(pageNum, pageSize);
        if(mappingUnitPageInfo != null){
            return querySuccess(mappingUnitPageInfo);
        }
        return queryFailed();
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
        TokenVo tokenVo = mappingUnitService.queryMappingUnitByUnitName(unitName);
        if(tokenVo.getIsSuccess()) {
            return querySuccess(tokenVo);
        }
        return queryFailed("系统异常");
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
        MappingUnit mappingUnit1 = mappingUnitService.queryOneMappingUnit(mappingUnit);
        if(mappingUnit1 != null ){
            return querySuccess(mappingUnit1);
        }
        return queryFailed();
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 21:23
     * @description:
     *      通过测绘单位的主键id插入分值记录
     * @param unitId 进行改变分值的id
     * @param operationType 增加(1)/减少(2)
     * @param file 上传的附件
     * @param scoreChange 改变的分值
     * @param reason  分值变化的原因0
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertScoreById")
    public ResultData insertScoreById(@RequestParam("unitId")Long unitId, @RequestParam("operationType")String operationType,
                                      @RequestParam("file") MultipartFile file, @RequestParam("scoreChange")Integer scoreChange,
                                      @RequestParam("reason")String reason){
        //添加分值记录
        TokenVo tokenVo = null;
        //修改单位信息
        TokenVo tokenVo1 = null;
        //1.查询单位的当前分值
        MappingUnit mappingUnit = mappingUnitService.queryMappingUnitByPrimaryKey(unitId);
        Integer score = null;
        //2.判断查询是否成功
        if(null != mappingUnit){
            //成功
            //3.获取单位的当前分值
            score = mappingUnit.getScore();
            //4.添加分值记录信息
            tokenVo = scoreService.insertScoreById(scoreChange, score, unitId, operationType, reason);
            //5.修改单位信息
            MappingUnit mappingUnit1 = new MappingUnit();
            //5.1.改变单位的当前分值
            if("增加".equals(operationType) ){
                score = score + scoreChange;
            }else if ("减少".equals(operationType)){
                score = score - scoreChange;
            }
            //5.2.判断单位的状态（score<60:黑名单；60<score<100:待定；100=<score:白名单）
            Integer unitStatus = null;
            if(score >= 100){
                //白名单
                unitStatus = 1;
            }else if(score < 60){
                //黑名单
                unitStatus = 2;
            }else{
                //待定
                unitStatus = 3;
            }
            //5.3.修改单位的信息
            mappingUnit1.setId(mappingUnit.getId()).setAuditStatus(2).setScore(score).setUnitStatus(unitStatus);
            tokenVo1 = mappingUnitService.updateMappingUnitByUserId(mappingUnit1);
            //6.上传文件和记录文件信息
            TokenVo tokenVo2 = resourceService.insertResourceById(file, "附件", unitId, null);
            //7.判断文件信息记录、单位修改信息、分值记录是否都成功
            if(tokenVo.getIsSuccess() && tokenVo1.getIsSuccess() && tokenVo2.getIsSuccess()){
                //成功
                return addSuccess("分值记录添加成功");
            }
            if(!tokenVo.getIsSuccess()){
                //分值记录添加失败
                return addFailed("分值记录添加失败");
            }
            if(!tokenVo1.getIsSuccess()){
                //单位信息修改失败
                return updateFailed("单位信息修改失败");
            }
            if(tokenVo2.getOperation() == 3){
                //文件上传失败
                return  addFailed("文件上传失败");
            }
        }
        //系统异常
        return addFailed("系统异常");
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
    public ResultData queryMappingUnitByAuditStatusAndNoCertificate(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName){
        PageInfo<MappingUnit> pageInfo = mappingUnitService.queryMappingUnitByAuditStatusAndNoCertificate(pageNum, pageSize,unitName);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
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
    public ResultData queryMappingUnitByAuditStatusAndCertificate(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName){
        PageInfo<MappingUnit> pageInfo = mappingUnitService.queryMappingUnitByAuditStatusAndCertificate(pageNum, pageSize,unitName);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
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
                                                   @RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize){
        PageInfo pageInfo = mappingUnitService.queryMappingUnitByPercentage(percentage, ownedDistrict, pageNum, pageSize);
        if(pageInfo != null){
            return  querySuccess(pageInfo);
        }
        return queryFailed();
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 18:17
     * @description:
     *      修改单位的信息
     * @param mappingUnit
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateMappingUnitByUserId")
    public ResultData updateMappingUnitByUserId(@RequestBody MappingUnit mappingUnit){
        TokenVo tokenVo = mappingUnitService.updateMappingUnitByUserId(mappingUnit);
        if(tokenVo.getIsSuccess()){
            return addSuccess("修改数据提交成功");
        }
        return addFailed("修改数据提交失败");
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
        List<MappingUnit> mappingUnits = mappingUnitService.queryMappingUnitMain(unitName, ownedDistrict, qualificationLevel);
        if(mappingUnits != null){
            return querySuccess(mappingUnits);
        }
        return queryFailed();
    }

}
