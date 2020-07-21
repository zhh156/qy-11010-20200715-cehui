package com.aaa.mapper;

import com.aaa.model.Equipment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface EquipmentMapper extends Mapper<Equipment> {
    /**
     * 根据单位的userId查询仪器设备信息
     * @param userId
     * @return
     */
    List<Map> queryEquipmentByUserId(@Param("userId")Long userId);
}