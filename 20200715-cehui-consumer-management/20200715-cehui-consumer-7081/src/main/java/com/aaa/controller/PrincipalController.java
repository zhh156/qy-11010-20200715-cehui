package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/21 15:34
 * @Description
 **/
@RestController
public class PrincipalController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
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
    public ResultData insertPrincipalByUserId(@RequestParam("type") String type, @RequestParam("name") String name,
                                              @RequestParam("idType") String idType, @RequestParam("idNumber") String idNumber,
                                              @RequestParam("age")Integer age, @RequestParam("sex") Integer sex,
                                              @RequestParam("workYear") Integer workYear, @RequestParam("duty") String duty,
                                              @RequestParam("title")String title, @RequestParam("mappingYear") Integer mappingYear,
                                              @RequestParam("major") String major, @RequestParam("userId") Long userId,
                                              @RequestParam("file") MultipartFile[] file){
        return iProjectService.insertPrincipalByUserId(type,name,idType,idNumber,age,sex,workYear,duty,title,mappingYear,major,userId,file);
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
                                          @RequestParam("file") MultipartFile[] file,@RequestParam("id")Long id){
        return iProjectService.updatePrincipalById(type,name,idType,idNumber,age,sex,workYear,duty,title,mappingYear,major,userId,file,id);
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
        return iProjectService.deletePrincipalById(id,userId);
    }
}
