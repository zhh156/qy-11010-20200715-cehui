package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.SpecialPostMapper;
import com.aaa.model.SpecialPost;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 11:30
 * @Description
 *      特殊岗位人员
 **/
@Service
public class SpecialPostService extends BaseService<SpecialPost> {

    @Autowired
    private SpecialPostMapper specialPostMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:10
     * @description:
     *      查询某个单位的特殊人员信息
     * @param userId 单位中的userId
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo querySpecialPostByUserId(Long userId, Integer pageNum, Integer pageSize){
        //1.判断前端的数据是否传过来
        if(userId != null && pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.通过userId查询单位中的特殊人员信息
            List<Map> maps = specialPostMapper.querySpecialPostByUserId(userId);
            //3.判断查询是否成功
            if(maps != null){
                //成功
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }
}
