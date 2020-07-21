package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.PrincipalMapper;
import com.aaa.model.Principal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 10:17
 * @Description
 *      负责人信息
 **/
@Service
public class PrincipalService extends BaseService<Principal> {
    @Autowired
    private PrincipalMapper principalMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:11
     * @description:
     *      查询某个单位的法人信息
     * @param userId 单位中的userId
	 * @param pageNum 当前页数
	 * @param pageSize  每页的条数
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo queryPrincipalByUserId(Long userId,Integer pageNum,Integer pageSize){
        //1.判断前端的数据是否传到这里
        if(userId != null && pageNum != null && pageSize != null){
            //已经传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.通过userId查询法人信息
            List<Map> maps = principalMapper.queryPrincipalByUserId(userId);
            //3.判断查询是否成功
            if(maps != null){
                //成功后进行返回
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }
}
