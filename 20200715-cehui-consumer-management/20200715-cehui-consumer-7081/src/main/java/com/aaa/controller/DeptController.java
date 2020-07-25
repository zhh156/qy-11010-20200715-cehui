package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Dept;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ymq
 * @Date 2020/7/17 14:39
 **/

@RestController
public class DeptController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author ymq
     * @Date 2020/7/17 14:40
     * 查询出一级部门
     **/
    @GetMapping("/firstDept")
    public ResultData firstDept() {
        return iProjectService.firstDept();
    }

    /**
     * @Author ymq
     * @Date 2020/7/17 15:01
     * 根据一级部门id查询出二级部门信息
     **/
    @GetMapping("/secondDept")
    public ResultData secondDept(@RequestParam("id") int id) {
        return iProjectService.secondDept(id);
    }

    /**
     * @Author ymq
     * @Date 2020/7/17 15:01
     **/

    @PostMapping("/addDept")
    public ResultData addDept(@RequestBody Dept dept) {
        return iProjectService.addDept(dept);
    }

    /**
     * @Author ymq
     * @Date 2020/7/17 15:01
     **/

    @PostMapping("/updateDept")
    public ResultData updateDept(@RequestBody Dept dept) {
        System.out.println(dept);
        return iProjectService.updateDept(dept);

    }

    /**
     * @Author ymq
     * @Date 2020/7/17 15:02
     **/

    @PostMapping("/deleteDept")
    public ResultData deleteDept(@RequestBody Dept dept) {
        System.out.println(dept);
        return iProjectService.deleteDept(dept);
    }
}
