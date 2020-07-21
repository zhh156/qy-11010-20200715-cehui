package com.aaa.mapper;

import com.aaa.model.RoleMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMenuMapper extends Mapper<RoleMenu> {
    /**
     * 删除角色对应的权限
     * @param roleId
     * @return
     */
    Integer deleteRoleMenus(@Param("roleId") Integer roleId);
}