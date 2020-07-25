package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.News;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Title NewController
 * @Author tuo
 * @Date 2020/7/23 8:45
 **/
@RestController
public class NewController {
    @Autowired
    private IProjectService iProjectService;
    /**
     *@Author tuo
     *@Date 2020/7/20
     * 分页查询信息公开(可根据关键字进行查询)
     **/
    @PostMapping("/selectAllNews")
    public ResultData selectAllNews(@RequestBody HashMap hashMap){
        return iProjectService.selectAllNews(hashMap);
    }
    /**
     *@Author tuo
     *@Date 2020/7/20
     * 新增信息公开
     **/
    @PostMapping("/addNews")
    public ResultData addNewsOp(@RequestBody News news){
        return iProjectService.addNews(news);

    }
    /**
     *@Author tuo
     *@Date 2020/7/20
     * 修改信息公开
     **/
    @PostMapping("/updateNews")
    public ResultData updateNewsOp(@RequestBody News news){
        return iProjectService.updateNews(news);
    }
    /**
     *@Author tuo
     *@Date 2020/7/20
     * 删除一条信息
     **/
    @PostMapping("/deleteNewsOne")
    public ResultData deleteNewsOne(@RequestBody News news){
        return iProjectService.deleteNewsOne(news);
    }
    /**
     *@Author tuo
     *@Date 2020/7/20
     * 批量删除
     **/
    @PostMapping("/deleteNewsAll")
    public ResultData deleteNewsAll(@RequestParam("id") List<Long> id){
        return iProjectService.deleteNewsAll(id);
    }




}
