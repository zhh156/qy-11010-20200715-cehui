package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Dict;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Title DictController
 * @Author tuo
 * @Date 2020/7/16 19:52
 **/
@RestController
public class DictController {
    @Autowired
    private IProjectService iProjectService;
    /**
     *@Author tuo
     *@Date 2020/7/16
     * 获取字典信息 分页
     **/
    @GetMapping("/allDict")
    ResultData allDict(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return iProjectService.allDict(pageNo, pageSize);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 根据条件查询字典信息
     **/
    @PostMapping("selectAll")
    ResultData selectAll(@RequestBody Map hashMap){
        return iProjectService.selectAll(hashMap);
    }
    /**
     * @author tuo
     * @description: 删除字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/delDict")
    ResultData delDict(@RequestBody Dict dict) {
        return iProjectService.delDict(dict);
    }

    /**
     * @author tuo
     * @description: 新增字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/addDict")
    ResultData addDict(@RequestBody Dict dict) {
        return iProjectService.addDict(dict);
    }

    /**
     * @author tuo
     * @description: 修改字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/updDict")
    ResultData updDict(@RequestBody Dict dict) {
        return iProjectService.updDict(dict);
    }
    /**
     * @author Joy
     * @description: 查询单条字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/selectDict")
    ResultData selectDict(@RequestBody Dict dict){
        return iProjectService.selectDict(dict);
    }

}
