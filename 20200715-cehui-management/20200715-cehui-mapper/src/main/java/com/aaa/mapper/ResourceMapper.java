package com.aaa.mapper;

import com.aaa.model.Resource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ResourceMapper extends Mapper<Resource> {
    /**
     * 删除某一个资源信息
     * @param refBizId
     * @param refBizType
     * @return
     */
    Integer deleteResourceByRefBizId(@Param("refBizId") Long refBizId,@Param("refBizType") String refBizType);
}