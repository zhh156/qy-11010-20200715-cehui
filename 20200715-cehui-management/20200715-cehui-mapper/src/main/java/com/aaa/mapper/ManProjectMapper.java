package com.aaa.mapper;


import com.aaa.model.ManProject;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface
ManProjectMapper extends Mapper<ManProject> {


    List<ManProject> selectAllPros(ManProject manProject);

    List<ManProject> selectByTypes(ManProject manProject);

    //对项目类型进行统计

    List<Map> selectProjectType();
}