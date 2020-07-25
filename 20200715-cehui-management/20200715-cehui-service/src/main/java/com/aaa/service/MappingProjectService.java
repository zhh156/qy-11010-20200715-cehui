package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MappingProjectMapper;
import com.aaa.model.MappingProject;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    /**
     *@Author tuo
     *@Date 2020/7/18 11:28
     * 分页查询汇交成果
     **/
    public PageInfo<Map> selectByMappingProjectAll(String projectName,Integer pageNo,Integer pageSize){
        PageInfo<Map> projectPageInfo = null;
        try {
            //设置分页
            PageHelper.startPage(pageNo,pageSize);
            //调用方法查询结果
            List<Map> allProject = mappingProjectMapper.selectByMappingProjectAll(projectName);
            //分页
            projectPageInfo = new PageInfo<Map>(allProject);
            //判断是否为空
            if (projectPageInfo != null &&!"".equals(projectPageInfo)) {
                return projectPageInfo;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18 9:09
     * 根据id进行查询
     **/
    public List<Map> selectById(Long id){
        try {
            if (null != id && !"".equals(id)){
                //根据id查询项目信息
                List<Map> mappingProject = mappingProjectMapper.selectByMappingProjectOne(id);
                //判断是否存在
                if (null != mappingProject && !"".equals(mappingProject)) {
                    return mappingProject;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18 9:09
     * 查询项目审核记录
     **/
    public List<Map> selectByShenHeId(Long id){
        try {
            if (null != id && !"".equals(id)){
                //根据id查询项目信息
                List<Map> shenHeId = mappingProjectMapper.selectByShenHeId(id);
                //判断是否存在
                if (!"".equals(shenHeId)) {
                    return shenHeId;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18 11:28
     * 分页查询汇交成果
     **/
    public PageInfo<Map> selectAllHuiJiao(String projectName,Integer pageNo,Integer pageSize){
        PageInfo<Map> projectPageInfo = null;
        try {
            //设置分页
            PageHelper.startPage(pageNo,pageSize);
            //调用方法查询结果
            List<Map> allProject = mappingProjectMapper.selectAllHuiJiao(projectName);
            //分页
            projectPageInfo = new PageInfo<Map>(allProject);
            //判断是否为空
            if (null != projectPageInfo &&!"".equals(projectPageInfo)) {
                return projectPageInfo;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18 11:28
     * 分页查询汇交成果审核
     **/
    public PageInfo<Map> selectAllHuiJiaoShenHe(String projectName,Integer pageNo,Integer pageSize){
        PageInfo<Map> projectPageInfo = null;
        try {
            //设置分页
            PageHelper.startPage(pageNo,pageSize);
            //调用方法查询结果
            List<Map> huiJiaoShenHe = mappingProjectMapper.selectAllHuiJiaoShenHe(projectName);
            //分页
            projectPageInfo = new PageInfo<Map>(huiJiaoShenHe);
            //判断是否为空
            if (null != projectPageInfo &&!"".equals(projectPageInfo)) {
                return projectPageInfo;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18 9:09
     * 根据id查询汇交成果详情信息
     **/
    public List<Map> selectAllHuiJiaoXiangQing(Long id){
        try {
            if (null !=id && !"".equals(id)){
                //根据id查询项目信息
                List<Map> huiJiaoXiangQing = mappingProjectMapper.selectAllHuiJiaoXiangQing(id);
                //判断是否存在
                if (null != id && !"".equals(id)) {
                    return huiJiaoXiangQing;
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *@Author tuo
     *@Date 2020/7/18 11:28
     * 分页查询汇交成果记录
     **/
    public PageInfo<Map> selectChengGuoJiLu(Long id,Integer pageNo,Integer pageSize){
        PageInfo<Map> projectPageInfo = null;
        try {
            //设置分页
            PageHelper.startPage(pageNo,pageSize);
            //调用方法查询结果
            List<Map> allProject = mappingProjectMapper.selectChengGuoJiLu(id);
            //分页
            projectPageInfo = new PageInfo<Map>(allProject);
            //判断是否为空
            if (null != projectPageInfo &&!"".equals(projectPageInfo)) {
                return projectPageInfo;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18 11:28
     * 分页查询项目审核
     **/
    public PageInfo<Map> selectByMappingProjectShenHe(String projectName,Integer pageNo,Integer pageSize){
        PageInfo<Map> projectPageInfo = null;
        try {
            PageHelper.startPage(pageNo,pageSize);
            List<Map> allProject = mappingProjectMapper.selectByMappingProjectShenHe(projectName);
            projectPageInfo = new PageInfo<Map>(allProject);
            if (null != projectPageInfo &&!"".equals(projectPageInfo)) {
                return projectPageInfo;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     *@Author tuo
     *@Date 2020/7/18
     *系统主页-->测绘成果
     **/
    public List<Map> selectCeHuiChengGuo(HashMap hashMap){
        List<Map> maps = mappingProjectMapper.selectCeHuiChengHuo(hashMap);
        if (null != maps && !"".equals(maps)) {
            return maps;
        }
        return null;
    }

    /**
     *@Author ymq
     *@Date 2020/7/20
     * 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有的 项目汇交信息，进行分页
     **/
    public PageInfo<MappingProject> selectAllProjectResultByType(String projectType, Integer pageNo, Integer pageSize) {
        PageInfo<MappingProject> pageInfoByType = null;
        try {
            // 设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(pageNo, pageSize);
            // 调用 mappingProjectMapper 中的 selectAllProjectResultByType 方法，得到查询结果
            List<MappingProject> allByProjectType = mappingProjectMapper.selectAllProjectResultByType(projectType);
            // 将查询的结果 进行分页
            pageInfoByType = new PageInfo<MappingProject>(allByProjectType);
        } catch (IllegalArgumentException e) {
            // 非法参数异常
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != pageInfoByType && !"".equals(pageInfoByType)) {
            // 说明结果不为空，查询成功，返回查询的数据
            return pageInfoByType;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     *@Author ymq
     *@Date 2020/7/18 18:32
     * 查询所有未提交的汇交成果
    汇交成果状态 results_status=3
     **/

    public PageInfo<MappingProject> selectAllProjectResult(Integer paneNo,Integer pageSize){
        PageInfo<MappingProject> projectPageInfo =null;
        try {
            //设置分页，pageNO 当前页数，pageSize 每页数据个数
            PageHelper.startPage(paneNo,pageSize);
            //调用 mappingProjectMapper 中的 selectAllProjectResult 方法，得到查询结果
            List<MappingProject> mappingProjectList = (List<MappingProject>) mappingProjectMapper.selectAllProjectResult();
            // 将查询的结果 进行分页
            projectPageInfo = new PageInfo<>(mappingProjectList);
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 判断 结果是否为空
        if (null != projectPageInfo && !"".equals(projectPageInfo)){
            // 说明结果不为空，查询成功，返回分页结果
            return projectPageInfo;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

    /**
     * @Author ymq
     * @Date 2020/7/20 19:32
     * 项目汇交管理-操作-需改项目汇交状态
     **/
    public Boolean updateProjectResultStatusById(Long id) {
        int i = 0;
        try {
            // 调用 mappingProjectMapper 中的 updateProjectResultStatusById 方法，返回受影响行数
            i = mappingProjectMapper.updateProjectResultStatusById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断受影响行数是否大于0
        if (i > 0) {
            // 说明修改成功，返回true
            return true;
        } else {
            // 修改失败，返回false
            return false;
        }
    }
}
