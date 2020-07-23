package com.aaa.mapper;

import com.aaa.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MappingUnitMapper extends Mapper<MappingUnit> {

    /**
     * 通过单位的名称进行模糊查询
     * @param unitName
     * @return
     */
    List<MappingUnit> queryMappingUnitByUnitName(@Param("unitName")String unitName);

    /**
     * 查询修改待审核的单位
     * @return
     */
    List<MappingUnit> queryMappingUnitByAuditStatusAndNoCertificate(@Param("unitName") String unitName);

    /**
     * 单位审核的时候对单位信息的更新
     * @param mappingUnit
     * @return
     */
    Integer updateOfAudit(MappingUnit mappingUnit);

    /**
     * 查询注册后的需要审核的单位
     * @param uintName
     * @return
     */
    List<MappingUnit> queryMappingUnitByAuditStatusAndCertificate(@Param("unitName")String uintName);


    /**
     * 随机获取一定百分数的数据
     * @param percentage
     * @param ownedDistrict
     * @return
     */
    List<Map> queryMappingUnitByPercentage(@Param("percentage")Double percentage, @Param("ownedDistrict")String ownedDistrict);

    /**
     * 根据单位名、所属行政区、公司的资质等级进行查询公司
     * @param unitName 单位名（模糊查询）
     * @param ownedDistrict 所属行政区
     * @param qualificationLevel 公司的资质等级
     * @return
     */
    List<MappingUnit> queryMappingUnitMain(@Param("unitName")String unitName,@Param("ownedDistrict")String ownedDistrict,
                                           @Param("qualificationLevel")String qualificationLevel);
}