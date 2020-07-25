package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 12:20
 * @Description
 **/
@RestController
public class MappingProjectController extends BaseController {
    @Autowired
    private IProjectService iProjectService;


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:10
     * @description:
     *      查询某个单位的项目
     * @param userId 单位中的userId
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @param pageSize  项目的名称（模糊查询）
     * @return com.github.pagehelper.PageInfo
     **/
    @GetMapping("/queryMappingProjectByUserId")
    public ResultData queryMappingProjectByUserId(@RequestParam("userId") Long userId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                  @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize, @RequestParam("projectName") String projectName){
        return iProjectService.queryMappingProjectByUserId(userId,pageNum,pageSize,projectName);
    }

    /**
     * ymq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/getAllProjectResult")
    public ResultData getAllProjectResult(Integer pageNo, Integer pageSize){
        return iProjectService.selectAllProjectResult(pageNo, pageSize);
    }

    /**
     * ymq
     * @param projectType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/getAllProjectResultByType")
    public ResultData getAllProjectResultByType(String projectType, Integer pageNo, Integer pageSize){
        return iProjectService.selectAllProjectResultByType(projectType, pageNo, pageSize);
    }

    @PostMapping("/updateProjectResultStatusById")
    public ResultData updateProjectResultStatusById(Long id){
        return iProjectService.updateProjectResultStatusById(id);
    }
}
