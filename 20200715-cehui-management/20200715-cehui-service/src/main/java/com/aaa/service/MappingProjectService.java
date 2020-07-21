package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MappingProjectMapper;
import com.aaa.model.MappingProject;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 11:41
 * @Description
 *      测绘项目
 **/
@Service
public class MappingProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:10
     * @description:
     *      查询某个单位的项目
     * @param userId 单位中的userId
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @param pageSize  项目的名称（模糊查询）
     * @return com.github.pagehelper.PageInfo
     **/
    public TokenVo queryMappingProjectByUserId(Long userId, Integer pageNum, Integer pageSize, String projectName){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来
        if(userId != null && pageNum != null && pageSize != null){
            //传过来了
            //2.通过userId查询单位中的测绘项目信息
            PageHelper.startPage(pageNum,pageSize);
            List<MappingProject> mappingProjects = mappingProjectMapper.queryMappingProjectByUserId(userId, projectName);
            //3.判断查询是否成功
            if(mappingProjects != null){
                //成功
                PageInfo<MappingProject> pageInfo = new PageInfo<>(mappingProjects);
                return tokenVo.setIsSuccess(true).setData(pageInfo);
            }
        }
        return tokenVo.setIsSuccess(false);
    }

}
