package com.aaa.mapper;

import com.aaa.model.CheckPerson;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CheckPersonMapper extends Mapper<CheckPerson> {

    //查询一定百分比的检查人员的信息
    List<Map> queryCheckPersonByPercentage(@Param("percentage") Double percentage);

    /**
     * 查询最后一个检查人员的id值
     * @return
     */
    CheckPerson queryLastId();
}