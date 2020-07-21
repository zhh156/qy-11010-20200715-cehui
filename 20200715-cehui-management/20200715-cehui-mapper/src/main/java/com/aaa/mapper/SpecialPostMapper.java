package com.aaa.mapper;

import com.aaa.model.SpecialPost;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecialPostMapper extends Mapper<SpecialPost> {
    /**
     * 根据单位的useId查询特殊岗位人员信息
     * @param userId
     * @return
     */
    List<Map> querySpecialPostByUserId(@Param("userId")Long userId);
}