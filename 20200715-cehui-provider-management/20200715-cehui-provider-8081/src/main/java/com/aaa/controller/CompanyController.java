package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;

import com.aaa.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title CompanyController
 * @Author tuo
 * @Date 2020/7/17 14:35
 **/
@RestController
public class CompanyController extends CommonController<MappingUnit> {
    @Autowired
    private StatisticsService statisticsService;
    @Override
    public BaseService<MappingUnit> getBaseService() {
        return statisticsService;
    }
    /**
    *@Author tuo
    *@Date 2020/7/17 14:43
     * 分页查询 单位信息
    **/
    @GetMapping("/queryCompanyPage")
    public ResultData queryCompanyPage(@RequestParam("pageNo")int pageNo,
                                       @RequestParam("pageSize") int pageSize){
        return getListByPage(pageNo,pageSize);
    }
}
