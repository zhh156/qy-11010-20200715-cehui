package com.aaa.mapper;

import com.aaa.model.News;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NewsMapper extends Mapper<News> {
    /**
     * 查询新闻信息
     * @param hashMap
     * @return
     */
    List<News> selectAllNews(HashMap hashMap);

    List<Map> allNews();
}