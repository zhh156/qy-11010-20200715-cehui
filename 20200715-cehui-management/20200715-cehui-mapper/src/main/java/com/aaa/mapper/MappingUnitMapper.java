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



    /**
     *@Author tuo
     *@Date  10:25
     * 饼状图 单位资质
     **/
    List<Map> qualifications();
    /**
     *@Author tuo
     *@Date 2020/7/17 10:47
     *饼状图项目类型统计 查询未完成的测绘
     **/
    List<Map> noComplete();
    /**
     *@Author tuo
     *@Date 2020/7/17 10:47
     *饼状图项目类型统计 查询已完成的测绘
     **/

    List<Map> complete();

    /**
     *@Author tuo
     *@Date 2020/7/17 15:11
     * 查询不同人员等级的数量
     **/
    List<Map> companyArtisan(Integer userId);
    /**
     *@Author tuo
     *@Date 2020/7/17 15:29
     * 查询每种设备的数量
     **/
    List<Map> companyeQuipment(Integer userId);
    /**
     *@Author tuo
     *@Date 2020/7/17 15:29
     * 查询项目数量
     **/
    List<Map> companyeXiangMuNum(Integer userId);
    /**
     *@Author tuo
     *@Date 2020/7/17 15:54
     * 查询不同等级的人员数量
     **/
    List<Map> peopleGrade();

    /**
     *@Author tuo
     *@Date 2020/7/17 15:54
     * 查询不同设备的数量
     **/
    List<Map> equipmentGrade();


    List<MappingUnit> selectUserDanWei(String userId);

    List<Map> selectALLPrin(String userId);

    List<Map> selectJiShuRenYuan(String userId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    List<MappingUnit> selectOneMappingUnit(Long id);

    /**
     * 白名单
     * @return
     */
    List<MappingUnit> selectStatusOneMappingUnit();

    /**
     * 黑名单
     * @return
     */
    List<MappingUnit> selectStatusTwoMappingUnit();
}