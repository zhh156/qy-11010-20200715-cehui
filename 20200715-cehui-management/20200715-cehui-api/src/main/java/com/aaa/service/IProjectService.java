package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    ResultData doLogin(@RequestBody User user);

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
    ResultData queryAllAuditByUserId(@RequestParam("userId") Object userId,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/queryMappingUnitByAuditStatusAndNoCertificate")
    ResultData queryMappingUnitByAuditStatusAndNoCertificate(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName);

    @GetMapping("/queryMappingUnitByAuditStatusAndNoCertificate")
    ResultData queryMappingUnitByAuditStatusAndCertificate(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName);

    @GetMapping("/queryMappingUnitByAuditStatusByUnitName")
    ResultData queryMappingUnitByAuditStatusByUnitName(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("unitName")String unitName);

    @PostMapping("/updateAuditByRefId")
    ResultData updateAuditByRefId(@RequestBody Map map);

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
}
