package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Title StatisticsController
 * @Author tuo
 * @Date 2020/7/17 10:28
 * 数据统计
 **/
@RestController
public class StatisticsController extends BaseController {
    @Autowired
    private StatisticsService statisticsService;
    /**
     *@Author tuo
     *@Date 2020/7/17 10:30
     * 单位资质统计
    **/
    @PostMapping("/queryQualifications")
    public ResultData queryQualifications(){
        List<Map> qualifications = statisticsService.qualifications();
        //如果为空查询失败
        if (qualifications != null) {
            return querySuccess(qualifications);
        }else {
            return queryFailed();
        }
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 11:17
     * 饼状图 已完成和完成的测绘
     **/
    @PostMapping("/mapping")
    public ResultData mapping(){
        List<Map> noCompletes = statisticsService.mapping();
        //如果为空查询失败
        if (noCompletes != null) {
            return querySuccess(noCompletes);
        }else {
            return queryFailed();
        }
    }
    /**
    *@Author tuo
    *@Date 2020/7/17 16:01
     * 查询不同人员等级的数量
    **/
    @GetMapping("/company")
    public ResultData company(@RequestParam("userId") Integer userId){
        List<Map> company = statisticsService.company(userId);
        //如果为空查询失败
        if (company != null) {
            return querySuccess(company);
        }else {
            return queryFailed();
        }
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 15:04
     * 查询每种设备的数量
     **/
    @GetMapping("/companyQuipment")
    public ResultData companyQuipment(@RequestParam("userId")Integer userId){
        List<Map> company = statisticsService.companyQuipment(userId);
        //如果为空查询失败
        if (company != null) {
            return querySuccess(company);
        }else {
            return queryFailed();
        }
    }
    /**
    *@Author tuo
    *@Date 2020/7/17
     * 查询项目数量
    **/
    @GetMapping("/companyeXiangMuNum")
    public ResultData companyeXiangMuNum(@RequestParam("userId")Integer userId){
        List<Map> company = statisticsService.companyeXiangMuNum(userId);
        //如果为空查询失败
        if (company != null) {
            return querySuccess(company);
        }else {
            return queryFailed();
        }
    }
    /**
    *@Author tuo
    *@Date 2020/7/17
     * 查询人员等级，设备，项目数量
    **/
    @GetMapping("/companyeQuanBu")
    public ResultData companyeQuanBu(@RequestParam("userId")Integer userId){
        List<Map> company = statisticsService.companyQuanBu(userId);
        //如果为空查询失败
        System.out.println(company);
        if (company != null) {
            return querySuccess(company);
        }else {
            return queryFailed();
        }
    }
    /**
    *@Author tuo
    *@Date 2020/7/17 15:59
     * 查询不同等级的人员和设备的数量
    **/
    @PostMapping("/grade")
    public ResultData grade(){
        List<Map> grade = statisticsService.grade();
        //如果为空查询失败
        if (grade != null) {
            return querySuccess(grade);
        }else {
            return queryFailed();
        }
    }
    /**
     *@Author tuo
     *@Date 2020/7/17 15:07
     * 查询所有的设备数量
     **/
    @PostMapping("/equipmentGrade")
    public ResultData equipmentGrade(){
        List<Map> equipment = statisticsService.equipmentGrade();
        //如果为空查询失败
        if (equipment != null) {
            return querySuccess(equipment);
        }else {
            return queryFailed();
        }
    }

    @PostMapping("/selectUserDanWei")
    public ResultData selectUserDanWei(String userId){
        if (null != userId) {
            List<MappingUnit> danWeiXinXi = statisticsService.selectUserDanWei(userId);
            return super.querySuccess(danWeiXinXi);
        }
        return super.queryFailed();
    }
    @PostMapping("/selectALLPrin")
    public ResultData selectALLPrin(String userId){
        if (null != userId) {
            List<Map> prin = statisticsService.selectALLPrin(userId);
            return super.querySuccess(prin);
        }
        return super.queryFailed();
    }
    @PostMapping("/selectJiShuRenYuan")
    public ResultData selectJiShuRenYuan(String userId){
        if (null != userId) {
            List<Map> jiShuRenYuan = statisticsService.selectJiShuRenYuan(userId);
            return super.querySuccess(jiShuRenYuan);
        }
        return super.queryFailed();
    }
}
