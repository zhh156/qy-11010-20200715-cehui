package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.ManProject;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ymq
 * @Date 2020/7/20 10:28
 * 项目管理Controller
 **/
@RestController
public class ManProjectController extends BaseController {

    @Autowired
    IProjectService iProjectService;


    /**
     * @Author ymq
     * @Date 2020/7/20 10:28
     **/
    @GetMapping("/allPro")
    public ResultData selectAllPros(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return iProjectService.selectAllPros(pageNo,pageSize);
    }

    /**
     * @Author ymq
     * @Date 2020/7/20
     * 新增项目信息
     **/
    @PostMapping("/addManProject")
    public ResultData addManProject(@RequestBody ManProject manProject) {
        return iProjectService.addManProject(manProject);
    }

    /**
     * @Author ymq
     * @Date 2020/7/20
     * 修改项目信息
     **/

    @PostMapping("/updateManProject")
    public ResultData updateManProject(@RequestBody ManProject manProject) {
        return iProjectService.updateManProject(manProject);
    }

    /**
     * @Author ymq
     * @Date 2020/7/20
     * 删除某个项目信息
     **/
    @PostMapping("/deleteManProject")
    public ResultData deleteManProject(@RequestBody ManProject manProject) {
        return iProjectService.deleteManProject(manProject);
    }

}
