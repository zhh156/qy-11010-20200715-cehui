package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title StatisticsController
 * @Author tuo
 * @Date 2020/7/17 20:46
 **/
@RestController
public class StatisticsController {
    @Autowired
    private IProjectService iProjectService;
    /**
     *@Author tuo
     *@Date 2020/7/17 14:43
     * 分页查询 单位信息
     **/
    @GetMapping("/queryCompanyPage")
    ResultData queryCompanyPage(@RequestParam("pageNo")int pageNo,
                                @RequestParam("pageSize") int pageSize){
        return iProjectService.queryCompanyPage(pageNo,pageSize);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 10:30
     * 单位资质统计
     **/
    @PostMapping("/queryQualifications")
    ResultData queryQualifications(){
        return iProjectService.queryQualifications();
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 11:17
     * 饼状图 已完成和完成的测绘
     **/
    @PostMapping("/mapping")
    ResultData mapping(){
        return iProjectService.mapping();
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 16:01
     * 查询不同人员等级的数量
     **/
    @GetMapping("/company")
    ResultData company(@RequestParam("userId") Integer userId){
        System.out.println(userId);
        return iProjectService.company(userId);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 15:04
     * 查询每种设备的数量
     **/
    @GetMapping("/companyQuipment")
    ResultData companyQuipment(@RequestParam("userId")Integer userId){
        return iProjectService.companyQuipment(userId);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 15:59
     * 查询不同等级的人员和设备的数量
     **/
    @PostMapping("/grade")
    ResultData grade(){
        return iProjectService.grade();
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 15:07
     * 查询所有的设备数量
     **/
    @PostMapping("/equipmentGrade")
    ResultData equipmentGrade(){
        return iProjectService.equipmentGrade();
    }
    /**
    *@Author tuo
    *@Date 2020/7/17
     * 查询所有项目的数量
    **/
    @GetMapping("/companyeXiangMuNum")
    ResultData companyeXiangMuNum(@RequestParam("userId")Integer userId){
        return iProjectService.companyeXiangMuNum(userId);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 查询人员等级，设备，项目数量
     **/
    @GetMapping("/companyeQuanBu")
    ResultData companyeQuanBu(@RequestParam("userId")Integer userId){
        return iProjectService.companyeQuanBu(userId);
    }
}
