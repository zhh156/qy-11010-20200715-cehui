package com.aaa.mapper;

import com.aaa.model.Dict;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DictMapper extends Mapper<Dict> {
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 根据条件查询字典信息
     **/
    List<Dict> selectAlls(Map hashMap);
}