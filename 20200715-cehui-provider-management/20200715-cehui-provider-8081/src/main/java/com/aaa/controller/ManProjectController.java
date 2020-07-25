package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.ManProject;
import com.aaa.service.ManProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
    *@Author ymq
    *@Date 2020/7/18 10:56
 * 项目管理实现类
**/


@RestController
public class ManProjectController extends CommonController<ManProject> {

    @Autowired
    private ManProjectService manProjectService;

    /**
     * @Author ymq
     * @Date 2020/7/18
     * 查询项目列表
     **/
    @Override
    public BaseService<ManProject> getBaseService() {
        return manProjectService;
    }

    /**
        *@Author ymq
        *@Date 2020/7/18 09:41
     * 查询所有测绘项目
    **/


    @GetMapping("/allPro")
    public ResultData selectAllPros(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = manProjectService.selectAllPros(null, pageNo, pageSize);
        if(pageInfo.getSize() >0){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }



    /**
        *@Author ymq
        *@Date 2020/7/18 11:11
     * 根据项目类型查询项目
    **/
    @PostMapping("/selectAllProjectResultByType")
    public ResultData selectAllProjectResultByType(@RequestBody ManProject manProject, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        try {
            PageInfo pageInfo = manProjectService.selectMappingProjectByType(manProject, pageNo, pageSize);
            if (null !=pageInfo){
                return querySuccess(pageInfo);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryFailed();
    }

        /**
            *@Author ymq
            *@Date 2020/7/18 10:41
         * 新增测绘项目
        **/
        @PostMapping("/addManProject")
        public ResultData addUser(@RequestBody ManProject manProject){
            return  super.addT(manProject);
        }

        /**
            *@Author ymq
            *@Date 2020/7/18 11:51
         * 修改项目信息
        **/
        @PostMapping("/updateManProject")
        public ResultData updateManProject(@RequestBody ManProject manProject){
            System.out.println(manProject);
            return super.updateT(manProject);
        }

        /**
            *@Author ymq
            *@Date 2020/7/18 12:00
         * 删除项目信息
        **/
        @PostMapping("/deleteManProject")
        public ResultData deleteManProject(@RequestBody ManProject manProject){
            System.out.println(manProject);
            return super.deleteT(manProject);
        }
}