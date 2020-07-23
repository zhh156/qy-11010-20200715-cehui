package com.aaa.mapper;

import com.aaa.model.Audit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AuditMapper extends Mapper<Audit> {
    /**
     * 查询某个测绘单位的审核记录信息
     * @param userId
     * @return
     */
    List<Map> queryAllAuditByUserId(@Param("userId")Long userId);

    /**
     * 修改审核表中的数据(包括单位的全部信息)，将单位表中的user_id与审核表中的user_id
     *  进行关联，从而查询单位表的所有信息
     * @param audit 审核信息
     * @return
     */
    Integer updateAuditByUserId(Audit audit);

    /**
     * 查询某个测绘单位的信息
     * @return
     */
    Audit queryAuditByUserId(@Param("userId")Long userId);
}