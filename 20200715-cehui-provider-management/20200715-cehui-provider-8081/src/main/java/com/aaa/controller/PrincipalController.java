package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Audit;
import com.aaa.model.Principal;
import com.aaa.service.AuditService;
import com.aaa.service.PrincipalService;
import com.aaa.service.ResourceService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:41
 * @Description
 *      负责人信息
 **/
@RestController
public class PrincipalController extends CommonController<Principal> {
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private ResourceService resourceService;


    @Override
    public BaseService<Principal> getBaseService() {
        return principalService;
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
    public ResultData queryPrincipalByUserId(@RequestParam("userId") Object userId, @RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = principalService.queryPrincipalByUserId(Long.parseLong(userId.toString()), pageNum, pageSize);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 14:52
     * @description:
     *      添加单位的法人代表
     * @param type
     * @param name
     * @param idType
     * @param idNumber
     * @param age
     * @param sex
     * @param workYear
     * @param duty
     * @param title
     * @param mappingYear
     * @param major
     * @param userId
     * @return com.aaa.vo.TokenVo
     **/
    @PostMapping("/insertPrincipalByUserId")
    public ResultData insertPrincipalByUserId(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                          @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                          @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                          @RequestBody MultipartFile[] file){
        //1.判断单位审核是否通过
        Audit audit = auditService.queryAuditByUserId(userId);
        if(audit != null){
            Integer status = audit.getStatus();
            System.out.println(status);
            //2.通过审核
            if(status == 1){
                //3.添加法人代表
                Principal principal = principalService.insertPrincipalByUserId(type, name, idType, idNumber, age, sex, workYear, duty, title, mappingYear, major, userId);
                //4.判断法人代表添加是否成功
                if(principal != null){
                    //5.添加文件
                    Integer fileResult = 0;
                    TokenVo tokenVo = null;
                    if(file != null){
                        for (MultipartFile multipartFile : file) {
                            tokenVo = resourceService.insertResourceById(multipartFile, "身份证", principal.getId(), null);
                            if(tokenVo.getIsSuccess()){
                                fileResult = fileResult + 1;
                            }
                        }
                        if(fileResult != file.length){
                            return addFailed("资源添加失败");
                        }
                    }
                    return addSuccess(principal);
                }else{
                    return addFailed("法人代表添加失败");
                }
            }
        }
        return addFailed("审核未通过，不能添加");
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 15:43
     * @description:
     *      修改法人代表信息
     * @param type
	 * @param name
	 * @param idType
	 * @param idNumber
	 * @param age
	 * @param sex
	 * @param workYear
	 * @param duty
	 * @param title
	 * @param mappingYear
	 * @param major
	 * @param userId
	 * @param file
	 * @param id
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updatePrincipalById")
    public ResultData updatePrincipalById(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                              @RequestParam("age")Integer age, @RequestParam("sex") Integer sex, @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                              @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear, @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                              @RequestBody MultipartFile[] file,@RequestParam("id")Long id){
        //1.判断单位审核是否通过
        Audit audit = auditService.queryAuditByUserId(userId);
        Integer status = audit.getStatus();
        System.out.println(status);
        //2.通过审核
        if(status == 1){
            //3.修改法人代表
            Principal principal = principalService.updatePrincipalById(type, name, idType, idNumber, age, sex, workYear, duty, title, mappingYear, major, userId,id);
            //4.删除文件
            Boolean aBoolean = resourceService.deleteResourceByRefBizId(id, "身份证");
            if(aBoolean){
                //6.判断法人代表修改是否成功
                if(principal != null){
                    //成功
                    //5.添加文件
                    Integer fileResult = 0;
                    TokenVo tokenVo = null;
                    if(file != null){
                        for (MultipartFile multipartFile : file) {
                            tokenVo = resourceService.insertResourceById(multipartFile, "身份证", principal.getId(), null);
                            if(tokenVo.getIsSuccess()){
                                fileResult = fileResult + 1;
                            }
                        }
                        if(fileResult != file.length){
                            return addFailed("资源更新失败");
                        }
                    }
                    return addSuccess(principal);
                }else{
                    return updateFailed("法人代表更新失败");
                }
            }else{
                return deleteFailed("资源删除失败");
            }
        }
        return updateFailed("审核未通过，不能修改");
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 16:27
     * @description:
     *      删除法人代表及信息
     * @param id
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/deletePrincipalById")
    public ResultData deletePrincipalById(@RequestParam("id") Long id,@RequestParam("userId")Long userId){
        //1.判断单位审核是否通过
        Audit audit = auditService.queryAuditByUserId(userId);
        Integer status = audit.getStatus();
        //2.通过审核
        if(status == 1){
            //1.删除法人代表
            Boolean aBoolean = principalService.deletePrincipalById(id);
            if(aBoolean){
                //2.删除法人代表相对应的资源
                Boolean aBoolean1 = resourceService.deleteResourceByRefBizId(id, null);
                if(aBoolean1){
                    return deleteSuccess();
                }
            }
        }
        return deleteFailed("审核未通过，不能修改");
    }
}
