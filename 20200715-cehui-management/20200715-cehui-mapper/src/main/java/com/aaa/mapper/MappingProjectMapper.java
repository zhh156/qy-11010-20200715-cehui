package com.aaa.mapper;

import com.aaa.model.MappingProject;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MappingProjectMapper extends Mapper<MappingProject> {
    /**
     * 根据单位的userId查询项目
     * @param userId
     * @param projectName
     * @return
     */
    List<MappingProject> queryMappingProjectByUserId(@Param("userId")Long userId,@Param("projectName")String projectName);
}