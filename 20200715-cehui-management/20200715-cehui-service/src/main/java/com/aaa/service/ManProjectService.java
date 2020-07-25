package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.ManProjectMapper;
import com.aaa.model.ManProject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
        *@Author ymq
        *@Date 2020/7/18 9:16
     * 项目管理service
    **/
@Service
public class ManProjectService extends BaseService<ManProject> {

    @Autowired
    private ManProjectMapper manProjectMapper;



    /**
        *@Author ymq
        *@Date 2020/7/18
     * 实现查询项目列表
    **/

    public PageInfo selectAllPros(ManProject manProject, Integer pageNo, Integer pageSize){
        try {
            //判断前段是否传值成功
            if (!"".equals(pageNo) && !"".equals(pageSize)){
                //调用分页方法
                PageInfo<ManProject> userPageInfo =selectListByPage (manProject, pageNo, pageSize);
                //判断查询是否成功
                if (!"".equals(userPageInfo) && null !=userPageInfo){
                    //成功返回
                    return userPageInfo;
                }else {
                    //不成功再次查询
                    PageInfo<ManProject> userPageInfo1 =selectListByPage (manProject, pageNo, pageSize);
                    return userPageInfo1;
                }
            }else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        /**
            *@Author ymq
            *@Date 2020/7/18
         * 根据项目类型查询项目数据
        **/
    public PageInfo selectMappingProjectByType(ManProject manProject, Integer pageNo, Integer pageSize){
        //传入当前页和数量
        PageHelper.startPage(pageNo,pageSize);

        try {
            //调用查询方法
            List<ManProject> manProjects = manProjectMapper.selectByTypes(manProject);
            //判断是否查询出数据
            if (null !=manProject){
                //将查询的数据放入分页
                PageInfo<ManProject> manProjectPageInfo = new PageInfo<>(manProjects);
                return manProjectPageInfo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }
     }





