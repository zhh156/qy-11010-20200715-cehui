package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.NewsMapper;
import com.aaa.model.News;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title NewsService
 * @Author tuo
 * @Date 2020/7/20 8:36
 **/
@Service
public class NewsOpService extends BaseService<News> {
    @Autowired
    private NewsMapper newsMapper;
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 分页查询信息公开（可根据条件进行查询）
    **/
    public PageInfo selectAllNews(@RequestBody HashMap hashMap){
        PageHelper.startPage(Integer.parseInt(hashMap.get("pageNo")+""),Integer.parseInt(hashMap.get("pageSize")+""));
       PageInfo page = new PageInfo(newsMapper.selectAllNews(hashMap));
        if (page != null) {
            return page;
        }
        return null;
    }
    /**
    *@Author tuo
    *@Date 2020/7/20
     * 批量删除信息公开
    **/
    public Integer deleteNewsAll(List<Long> id){
        Example example = Example.builder(News.class).where(Sqls.custom().andIn("id",id)).build();
        return newsMapper.deleteByExample(example);
    }

    /**
     * ymq
     * @return
     */
    public List<Map> allNews() {
        return newsMapper.allNews();
    }
}
