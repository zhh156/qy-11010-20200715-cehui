package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MappingUnitMapper;
import com.aaa.model.MappingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Title StatisticsService
 * @Author tuo
 * @Date 2020/7/17 10:17
 **/
@Service
public class StatisticsService extends BaseService<MappingUnit> {
    @Autowired
    private MappingUnitMapper mappingUnitMapper;
    /**
     * 饼状图1 单位资质
     */
    public List<Map> qualifications(){
        return mappingUnitMapper.qualifications();
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 10:47
     *饼状图项目类型统计 查询未完成的测绘
     **/
    public List<Map> mapping(){
        List<Map> noComplete = mappingUnitMapper.noComplete();
        List<Map> complete = mappingUnitMapper.complete();
        noComplete.addAll(complete);
        return noComplete;
    }

    /**
    *@Author tuo
    *@Date 2020/7/17 15:04
     * 查询不同人员等级的数量
    **/
    public List<Map> company(Integer userId){
        return mappingUnitMapper.companyArtisan(userId);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 查询每种设备的数量
     **/
    public List<Map> companyQuipment(Integer userId){
        return mappingUnitMapper.companyeQuipment(userId);
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 查询项目数量
    **/
    public List<Map> companyeXiangMuNum(Integer userId){
       return mappingUnitMapper.companyeXiangMuNum(userId);
    }
    public List<Map> companyQuanBu(Integer userId){
        List<Map> company1 = mappingUnitMapper.companyArtisan(userId);
        List<Map> company2 = mappingUnitMapper.companyeQuipment(userId);
        List<Map> company3 = mappingUnitMapper.companyeXiangMuNum(userId);
        company1.addAll(company2);
        company1.addAll(company3);
        return company1;
    }
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 查询不同等级的人员和设备的数量
     **/
    public List<Map> grade(){
        List<Map> grade1 = mappingUnitMapper.peopleGrade();
        List<Map> grade2 = mappingUnitMapper.equipmentGrade();
        grade1.addAll(grade2);
        return grade1;
    }
    /**
    *@Author tuo
    *@Date 2020/7/17
     * 查询所有的设备数量
    **/
    public List<Map> equipmentGrade(){
        return mappingUnitMapper.equipmentGrade();
    }

    public List<MappingUnit> selectUserDanWei(String userId){
        if (null != userId && !"".equals(userId)) {
            List<MappingUnit> danWeiXinXi = mappingUnitMapper.selectUserDanWei(userId);
            return danWeiXinXi;
        }
        return null;
    }
    public List<Map> selectALLPrin(String userId){
        if (null != userId && !"".equals(userId)) {
            List<Map> prin = mappingUnitMapper.selectALLPrin(userId);
            return prin;
        }
        return null;
    }
    public List<Map> selectJiShuRenYuan(String userId){
        if (null != userId && !"".equals(userId)) {
            List<Map> jiShuRenYuan = mappingUnitMapper.selectJiShuRenYuan(userId);
            return jiShuRenYuan;
        }
        return null;
    }

}
