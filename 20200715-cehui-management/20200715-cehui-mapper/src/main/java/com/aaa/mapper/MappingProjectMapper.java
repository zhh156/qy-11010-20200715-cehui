package com.aaa.mapper;

import com.aaa.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MappingProjectMapper extends Mapper<MappingProject> {
    /**
     * 根据单位的userId查询项目
     * @param userId
     * @param projectName
     * @return
     */
    List<MappingProject> queryMappingProjectByUserId(@Param("userId")Long userId,@Param("projectName")String projectName);

    /**
     *@Author tuo
     *@Date 2020/7/18 8:48
     *查询项目详情信息
     **/
    List<Map> selectByMappingProjectAll(String projectName);
    /**
     *@Author tuo
     *@Date 2020/7/18 8:48
     * 根据id查询项目详情信息
     **/
    List<Map> selectByMappingProjectOne(Long id);
    /**
     *@Author tuo
     *@Date 2020/7/18 8:50
     * 根据id查询项目审核记录
     **/
    List<Map> selectByShenHeId(Long id);
    /**
     *@Author tuo
     *@Date 2020/7/18 8:50
     * 查询汇交成果信息
     **/
    List<Map> selectAllHuiJiao(String projectName);
    /**
     *@Author tuo
     *@Date 2020/7/18 8:50
     * 查询汇交成果审核
     **/
    List<Map> selectAllHuiJiaoShenHe(String projectName);
    /**
     *@Author tuo
     *@Date 2020/7/18 8:50
     * 根据id查询汇交成果详情信息
     **/
    List<Map> selectAllHuiJiaoXiangQing(Long id);

    /**
     *@Author tuo
     *@Date 2020/7/18 8:50
     * 根据id查询汇交成果审核记录
     **/
    List<Map> selectChengGuoJiLu(Long id);

    /**
     *@Author tuo
     *@Date 2020/7/18 8:50
     * 查询项目审核
     **/
    List<Map> selectByMappingProjectShenHe(String projectName);

    /**
     *@Author tuo
     *@Date 2020/7/18
     *系统主页-->测绘成果
     **/
    List<Map> selectCeHuiChengHuo(HashMap hashMap);

    /**
     *@Author ymq
     *@Date 2020/7/18 18:27
     *  *  查询所有未提交的汇交成果
     *      *      汇交成果状态 results_status=3
     **/
    List<MappingProject> selectAllProjectResult();

    /**
     *@Author ymq
     *@Date 2020/7/18 18:25
     * 条件查询 根据项目类型 projectType，查询所有未提交的汇交成果
     *      *      项目类型分为：基础测绘，专业测绘
     **/
    List<MappingProject> selectAllProjectResultByType(String projectType);


    /**
     *@Author ymq
     *@Date 2020/7/18 18:26
     *   *  修改汇交成果状态 results_status=2
     *      *  场景：点击按钮提交汇交成果项目
     **/
    int updateProjectResultStatusById(Long id);
}