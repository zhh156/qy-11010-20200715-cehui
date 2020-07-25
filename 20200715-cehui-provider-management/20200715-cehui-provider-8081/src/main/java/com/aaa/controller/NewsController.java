package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.News;
import com.aaa.service.NewsOpService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Title NewsController
 * @Author tuo
 * @Date 2020/7/20 8:52
 **/
@RestController
public class NewsController extends CommonController<News> {
    @Autowired
    private NewsOpService newsService;
    @Override
    public BaseService<News> getBaseService() {
        return newsService;
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 分页查询信息公开(可根据关键字进行查询)
    **/
    @PostMapping("/selectAllNews")
    public ResultData selectAllNews(@RequestBody HashMap hashMap){
        PageInfo pageInfo = newsService.selectAllNews(hashMap);
        if (pageInfo != null) {
            return super.querySuccess(pageInfo);
        }
        return super.queryFailed();
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 新增信息公开
    **/
    @PostMapping("/addNews")
    public ResultData addNews(@RequestBody News news){
        return super.addT(news);
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 修改信息公开
    **/
    @PostMapping("/updateNews")
    public ResultData updateNews(@RequestBody News news){
        return super.updateT(news);
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 删除一条信息
    **/
    @PostMapping("/deleteNewsOne")
    public ResultData deleteNewsOne(@RequestBody News news){
        return super.deleteT(news);
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 批量删除
    **/
    @PostMapping("/deleteNewsAll")
    public ResultData deleteNewsAll(@RequestParam("id") List<Long> id){
        Integer integer = newsService.deleteNewsAll(id);
        if (null != integer) {
            return super.deleteSuccess();
        }else {
            return super.deleteFailed();
        }
    }



}
