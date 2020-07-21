package com.aaa.mapper;

import com.aaa.model.Principal;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PrincipalMapper extends Mapper<Principal> {
    /**
     * 根据单位的userId查询法人的信息
     * @param userId
     * @return
     */
    List<Map> queryPrincipalByUserId(@Param("userId")Long userId);
}