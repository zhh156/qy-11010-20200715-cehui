package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 15:02
 * @Description
 **/
@FeignClient(value = "CEHUI-INTERFACE-PROVIDER")
public interface IProjectService {
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody Map map);

    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLog loginLog);

    @GetMapping("/queryAllRole")
    ResultData queryAllRole(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/queryRolesByRoleNameOrCreateTime")
    ResultData queryRolesByRoleNameOrCreateTime(@RequestBody Map map);

    @PostMapping("/selectOneRole")
    ResultData selectOneRole(@RequestBody Role role);

    @PostMapping("/selectOneRoleMenu")
    ResultData selectOneRoleMenu(@RequestBody Role role);

    @PostMapping("/addRole")
    ResultData addRole(@RequestBody Map map);

    @PostMapping("/updateRole")
    ResultData updateRole(@RequestBody Map map);

    @PostMapping("/deleteRole")
    ResultData deleteRole(@RequestBody Role role);

    @PostMapping("/deleteRoles")
    ResultData deleteRoles(@RequestBody List<Object> ids);

    @GetMapping("/queryOne")
    ResultData queryOne();

    @GetMapping("/queryTwoOrThree")
    ResultData queryTwoOrThree(@RequestParam("menuId") Object menuId);

    @PostMapping("/queryMenusByMenuNameOrCreateTime")
    ResultData queryMenusByMenuNameOrCreateTime(@RequestBody Map map);

    @PostMapping("/queryOneByPrimaryKey")
    ResultData queryOneByPrimaryKey(@RequestBody Menu menu);

    @PostMapping("/insertMenu")
    ResultData insertMenu(@RequestBody Menu menu);

    @PostMapping("/updateMenu")
    ResultData updateMenu(@RequestBody Menu menu);

    @PostMapping("/deleteMenu")
    ResultData deleteMenu(@RequestBody Menu menu);

    @PostMapping("/deleteMenus")
    ResultData deleteMenus(@RequestBody List<Object> ids);

    @GetMapping("/queryAll")
    ResultData queryAll(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/queryMappingUnitByUnitName")
    ResultData queryMappingUnitByUnitName(@RequestParam("unitName") String unitName);

    @PostMapping("/queryOneMappingUnit")
    ResultData queryOneMappingUnit(@RequestBody MappingUnit mappingUnit);

    @GetMapping("/queryPrincipalByUserId")
    ResultData queryPrincipalByUserId(@RequestParam("userId") Object userId,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/queryTechnicistByUserId")
    ResultData queryTechnicistByUserId(@RequestParam("userId")Object userId,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/queryEquipmentByUserId")
    ResultData queryEquipmentByUserId(@RequestParam("userId")Object userId,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/querySpecialPostByUserId")
    ResultData querySpecialPostByUserId(@RequestParam("userId")Object userId,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/queryMappingProjectByUserId")
    ResultData queryMappingProjectByUserId(@RequestParam("userId") Long userId, @RequestParam("pageNum") Integer pageNum,
                                                  @RequestParam("pageSize")Integer pageSize, @RequestParam("projectName") String projectName);

    @GetMapping("/queryResourceById")
    ResultData queryResourceById(@RequestParam("id")Object id);

    @GetMapping("/queryAllScoreById")
    ResultData queryAllScoreById(@RequestParam("id")Object id,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @PostMapping("/insertScoreById")
    ResultData insertScoreById(@RequestParam("unitId")Long unitId, @RequestParam("operationType")String operationType,
                                      @RequestParam("file") MultipartFile file, @RequestParam("scoreChange")Integer scoreChange,
                                      @RequestParam("reason")String reason);

    @GetMapping("/queryAllAuditByUserId")
    ResultData queryAllAuditByUserId(@RequestParam("userId") Long userId,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/queryMappingUnitByAuditStatusAndNoCertificate")
    ResultData queryMappingUnitByAuditStatusAndNoCertificate(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName);

    @GetMapping("/queryMappingUnitByAuditStatusAndNoCertificate")
    ResultData queryMappingUnitByAuditStatusAndCertificate(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName);

    @GetMapping("/queryMappingUnitByPercentage")
    ResultData queryMappingUnitByPercentage(@RequestParam("percentage")Double percentage,@RequestParam("ownedDistrict")String ownedDistrict,
                                                   @RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize);
    @GetMapping("/queryCheckPersonByPercentage")
    ResultData queryCheckPersonByPercentage(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("percentage") Double percentage);

    @PostMapping("/insertCheckPerson")
    ResultData insertCheckPerson(@RequestBody CheckPerson checkPerson);

    @PostMapping("/deleteCheckPerson")
    ResultData deleteCheckPerson(@RequestBody CheckPerson checkPerson);

    @PostMapping("/updateCheckPerson")
    ResultData updateCheckPerson(@RequestBody CheckPerson checkPerson);

    @GetMapping("/updateAuditByUserId")
    ResultData updateAuditByUserId(@RequestParam("userId") Long userId,@RequestParam("status") Integer status,@RequestParam("memo")String memo);

    @PostMapping("/submitAuditOfMappingUnit")
    ResultData submitAuditOfMappingUnit(@RequestBody MappingUnit mappingUnit);


    @PostMapping(value = "/insertPrincipalByUserId",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {"application/json;charset=UTF-8"} )
    ResultData insertPrincipalByUserId(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                              @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                              @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                              @RequestPart(value = "file",required = false) MultipartFile[] file);
    @PostMapping(value = "/updatePrincipalById",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {"application/json;charset=UTF-8"})
    ResultData updatePrincipalById(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                          @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                          @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                          @RequestPart(value = "file",required = false) MultipartFile[] file,@RequestParam("id")Long id);

    @GetMapping("/deletePrincipalById")
    ResultData deletePrincipalById(@RequestParam("id") Long id,@RequestParam("userId")Long userId);

    @PostMapping("/insertTechnicistByUserId")
    ResultData insertTechnicistByUserId(@RequestBody Technicist technicist);

    @PostMapping("/updateTechnicist")
    ResultData updateTechnicist(@RequestBody Technicist technicist);

    @PostMapping("/deleteTechnicist")
    ResultData deleteTechnicist(@RequestBody Technicist technicist);

    @PostMapping(value = "/insertEquipmentByUserId",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {"application/json;charset=UTF-8"})
    ResultData insertEquipmentByUserId(@RequestBody Equipment equipment,@RequestPart(value = "files1",required = false) MultipartFile[] files1,
                                       @RequestPart(value = "files2",required = false) MultipartFile[] files2);

    @GetMapping("/queryMappingUnitMain")
    ResultData queryMappingUnitMain(@RequestParam("unitName") String unitName,@RequestParam("ownedDistrict") String ownedDistrict,@RequestParam("qualificationLevel") String qualificationLevel);

    /**
     *@Author tuo
     *@Date 2020/7/16 10:55
     * 获取字典信息
     **/
    @PostMapping("/queryDict")
    List<Dict> selectDict();


    /**
     *@Author tuo
     *@Date 2020/7/16 16:53
     * 字典分页查询
     **/
    @GetMapping("/allDict")
    ResultData allDict(@RequestParam("pageNo") int currentPage, @RequestParam("pageSize") int pageSize);

    /**
     *@Author tuo
     *@Date 2020/7/17
     * 根据条件查询字典信息
     **/
    @PostMapping("selectAll")
    ResultData selectAll(@RequestBody Map hashMap);
    /**
     *@Author tuo
     *@Date 2020/7/16 12:05
     * 新增字典信息
     **/
    @PostMapping("/addDict")
    ResultData addDict(@RequestBody Dict dict);
    /**
     *@Author tuo
     *@Date 2020/7/16 14:42
     * 修改字典信息
     **/
    @PostMapping("/updateDict")
    Integer updateDict(@RequestBody Dict dict);

    /**
     * @author tuo
     * @description: 字典删除
     * @Date 2020/7/16
     **/
    @PostMapping("/delDict")
    ResultData delDict(@RequestBody Dict dict);

    /**
     * @author tuo
     * @description: 更新字典
     * @Date 2020/7/16
     **/
    @PostMapping("/updDict")
    ResultData updDict(@RequestBody Dict dict);

    /**
     * @author tuo
     * @description:
     * @Date 2020/7/16
     *查询单条字典信息
     **/
    @PostMapping("/selectDict")
    ResultData selectDict(@RequestBody Dict dict);

    /**
     *@Author tuo
     *@Date 2020/7/17 10:30
     * 单位资质统计
     **/
    @PostMapping("/queryQualifications")
    ResultData queryQualifications();
    /**
     *@Author tuo
     *@Date 2020/7/17 11:17
     * 饼状图 已完成和完成的测绘
     **/
    @PostMapping("/mapping")
    ResultData mapping();
    /**
     *@Author tuo
     *@Date 2020/7/17 16:01
     * 查询
     **/
    @GetMapping("/company")
    ResultData company(@RequestParam("userId")Integer userId);
    /**
     *@Author tuo
     *@Date 2020/7/17 15:04
     * 查询每种设备的数量
     **/
    @GetMapping("/companyQuipment")
    ResultData companyQuipment(@RequestParam("userId")Integer userId);
    /**
     *@Author tuo
     *@Date 2020/7/17 15:59
     * 查询不同等级的人员和设备的数量
     **/
    @PostMapping("/grade")
    ResultData grade();
    /**
     *@Author tuo
     *@Date 2020/7/17 15:07
     * 查询所有的设备数量
     **/
    @PostMapping("/equipmentGrade")
    ResultData equipmentGrade();
    /**
     *@Author tuo
     *@Date 2020/7/17 14:43
     * 分页查询 单位信息
     **/
    @GetMapping("/queryCompanyPage")
    ResultData queryCompanyPage(@RequestParam("pageNo")int pageNo,
                                @RequestParam("pageSize") int pageSize);
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 查询项目数量
     **/
    @GetMapping("/companyeXiangMuNum")
    ResultData companyeXiangMuNum(@RequestParam("userId")Integer userId);

    /**
     *@Author tuo
     *@Date 2020/7/17
     * 查询人员等级，设备，项目数量
     **/
    @GetMapping("/companyeQuanBu")
    ResultData companyeQuanBu(@RequestParam("userId")Integer userId);

    /**
     *@Author tuo
     *@Date 2020/7/18 11:03
     * 分页查询项目信息
     **/
    @GetMapping("selectByMappingProjectAll")
    ResultData selectByMappingProjectAll(@RequestParam("projectName")String projectName,
                                         @RequestParam("pageNo")int pageNo,
                                         @RequestParam("pageSize")int pageSize);

    /**
     *@Author tuo
     *@Date 2020/7/18 10:54
     * 根据id查询项目信息
     **/
    @GetMapping("/selectById")
    ResultData selectById(@RequestParam("id") Long id);

    /**
     *@Author tuo
     *@Date 2020/7/18 10:55
     * 查询项目审核记录
     **/
    @GetMapping("/selectByShenHeId")
    ResultData selectByShenHeId(@RequestParam("id") Long id);

    /**
     *@Author tuo
     *@Date 2020/7/18 10:55
     * 分页查询汇交成果
     **/
    @GetMapping("/selectAllHuiJiao")
    ResultData selectAllHuiJiao(@RequestParam("projectName")String projectName,
                                @RequestParam("pageNo")int pageNo,
                                @RequestParam("pageSize")int pageSize);

    /**
     *@Author tuo
     *@Date 2020/7/18 10:55
     * 分页查询汇交成果审核
     **/
    @GetMapping("/selectAllHuiJiaoShenHe")
    ResultData selectAllHuiJiaoShenHe(@RequestParam("projectName")String projectName,
                                      @RequestParam("pageNo")int pageNo,
                                      @RequestParam("pageSize")int pageSize);
    /**
     *@Author tuo
     *@Date 2020/7/18 10:55
     *
     **/
    @GetMapping("/selectAllHuiJiaoXiangQing")
    ResultData selectAllHuiJiaoXiangQing(@RequestParam("id") Long id);

    /**
     *@Author tuo
     *@Date 2020/7/18 10:55
     * 分页查询汇交成果
     **/
    @GetMapping("/selectChengGuoJiLu")
    ResultData selectChengGuoJiLu(@RequestParam("id") Long id,
                                  @RequestParam("pageNo")int pageNo,
                                  @RequestParam("pageSize")int pageSize);

    /**
     *@Author tuo
     *@Date 2020/7/18 10:55
     * 分页查询项目审核
     **/
    @GetMapping("/selectByMappingProjectShenHe")
    ResultData selectByMappingProjectShenHe(@RequestParam("projectName")String projectName,
                                            @RequestParam("pageNo")int pageNo,
                                            @RequestParam("pageSize")int pageSize);
    /**
     *@Author tuo
     *@Date 2020/7/18
     * 修改项目审核状态
     **/
    @PostMapping("/updateXiangMu")
    ResultData updateXiangMu(@RequestBody MappingProject mappingProject);

    /**
     *@Author tuo
     *@Date 2020/7/20
     * 分页查询信息公开(可根据关键字进行查询)
     **/
    @PostMapping("/selectAllNews")
    ResultData selectAllNews(@RequestBody HashMap hashMap);

    /**
     *@Author tuo
     *@Date 2020/7/20
     * 新增信息公开
     **/
    @PostMapping("/addNews")
    ResultData addNews(@RequestBody News news);

    /**
     *@Author tuo
     *@Date 2020/7/20
     * 修改信息公开
     **/
    @PostMapping("/updateNews")
    ResultData updateNews(@RequestBody News news);

    /**
     *@Author tuo
     *@Date 2020/7/20
     * 删除一条信息
     **/
    @PostMapping("/deleteNewsOne")
    ResultData deleteNewsOne(@RequestBody News news);

    /**
     *@Author tuo
     *@Date 2020/7/20
     * 批量删除
     **/
    @PostMapping("/deleteNewsAll")
    ResultData deleteNewsAll(@RequestParam("id") List<Long> id);

    /**
     *@Author tuo
     *@Date 2020/7/21
     * 根据用户查询信息
     **/
    @PostMapping("/queryInterface")
    ResultData queryInterface(@RequestParam("username")String username);

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/25 14:23
     * @description:
     *      测试通过feign进行文件上传的操作
     * @param file
     * @return com.aaa.base.ResultData
     **/
    @PostMapping(value = "/uploadImage",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {"application/json;charset=UTF-8"})
    ResultData uploadImage(@RequestPart(value = "file",required = false)MultipartFile[] file);

    /**
     * ymq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllUser")
    ResultData selectAllUser( @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    /**
     * ymq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectUserByField")
    ResultData selectUserByField(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    /**
     *@Author ymq
     *@Date 2020/7/16 17:17
     * 新增用户
     **/
    @PostMapping("/addUser")
    ResultData addUser(@RequestBody User user);

    /**
     *@Author ymq
     *@Date 2020/7/17 16:06
     * 修改用户信息
     **/
    @PostMapping("/updateUser")
    ResultData updateUser(@RequestBody User user);

    /**
     *@Author ymq
     *@Date 2020/7/16 17:23
     * 删除用户
     **/
    @PostMapping("/deleteUser")
    ResultData deleteUser(@RequestBody User user);

    /**
     *@Author ymq
     *@Date 2020/7/17 14:36
     * 查询一级部门信息
     **/
    @GetMapping("firstDept")
    ResultData firstDept();

    /**
     *@Author ymq
     *@Date 2020/7/17 14:46
     * 根据一级部门的ID查询出对应的二级部门
     **/
    @GetMapping("/secondDept")
    ResultData secondDept(@RequestParam("id") int id);

    /**
     *@Author ymq
     *@Date 2020/7/17 14:54
     * 新增部门
     **/
    @PostMapping("/addDept")
    ResultData addDept(@RequestBody Dept dept);

    /**
     *@Author ymq
     *@Date 2020/7/17 14:55
     * 修改部门信息
     **/
    @PostMapping("/updateDept")
    ResultData updateDept(@RequestBody Dept dept);
    /**
     *@Author ymq
     *@Date a 14:55
     * 删除部门信息
     **/
    @PostMapping("/deleteDept")
    ResultData deleteDept(@RequestBody Dept dept);



    /**
     *@Author ymq
     *@Date 2020/7/20
     * 查询所有项目列表
     **/
    @GetMapping("/allPro")
    ResultData  selectAllPros(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);



    /**
     *@Author ymq
     *@Date 2020/7/20
     * 新增项目
     **/
    @PostMapping("addManProject")
    ResultData addManProject(@RequestBody ManProject manProject);

    /**
     *@Author ymq
     *@Date 2020/7/20
     * 修改项目
     **/
    @PostMapping("updateManProject")
    ResultData updateManProject(@RequestBody ManProject manProject);

    /**
     *@Author ymq
     *@Date 2020/7/20
     * 删除项目
     **/
    @PostMapping("deleteManProject")
    ResultData deleteManProject(@RequestBody ManProject manProject);



    /**
     *@Author ymq
     *@Date 2020/7/24 11:38
     * 黑白名单管理
     **/
    @GetMapping("selectOneMappingUnit")
    ResultData selectOneMappingUnit(@RequestParam("id") Long id);


    /**
     *@Author ymq
     *@Date 2020/7/24 14:26
     * 白名单查询
     **/
    @PostMapping("/selectStatusOneMappingUnit")
    ResultData selectStatusOneMappingUnit(@RequestBody Map hashMap);


    /**
     *@Author ymq
     *@Date 2020/7/24 19:12
     * 黑名单查询
     **/
    @PostMapping("/selectStatusTwoMappingUnit")
    ResultData selectStatusTwoMappingUnit(@RequestBody Map hashMap);


    /**
     * ymq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllProjectResult")
    ResultData selectAllProjectResult(@RequestParam("pageNo") Integer pageNo,
                                                    @RequestParam("pageSize") Integer pageSize);

    /**
     * ymq
     * @param projectType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllProjectResultByType")
    ResultData selectAllProjectResultByType(@RequestParam("projectType") String projectType,
                                                          @RequestParam("pageNo") Integer pageNo,
                                                          @RequestParam("pageSize") Integer pageSize);

    /**
     * ymq
     * @param id
     * @return
     */
    @PostMapping("/updateProjectResultStatusById")
    ResultData updateProjectResultStatusById(@RequestParam("id") Long id);

}
