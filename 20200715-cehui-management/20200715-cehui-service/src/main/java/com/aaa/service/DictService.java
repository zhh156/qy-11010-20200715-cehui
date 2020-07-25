package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.DictMapper;
import com.aaa.model.Dict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title DictService
 * @Author tuo
 * @Date 2020/7/16 19:50
 **/
@Service
public class DictService extends BaseService<Dict> {
    @Autowired
    private DictMapper dictMapper;
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 根据条件查询字典信息
     **/
    public PageInfo selectAlls(@RequestBody Map hashMap){
        PageHelper.startPage(Integer.parseInt(hashMap.get("pageNo")+""),Integer.parseInt(hashMap.get("pageSize")+""));
        PageInfo pageInfo = new PageInfo(dictMapper.selectAlls(hashMap));
        if (pageInfo != null && !"".equals(pageInfo)) {
            return pageInfo;
        }
        return null;
    }
}
