package com.aaa.mapper;

import com.aaa.model.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends Mapper<Role> {
    /**
     * 通过角色名或者创建时间进行查找角色信息
     * @return
     */
    List<Role> queryRolesByRoleNameOrCreateTime(@Param("roleName") String roleName, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查询单个角色包括权限
     * @return
     */
    List<Map<String,Object>> selectOneRoleMenu(Role role);

    /**
     * 获取到最新添加的角色的id值
     * @return
     */
    Integer getAddRoleId();

    /**
     * 添加角色，成功后返回数据的自增主键id
     * @param role
     * @return
     */
    Integer insertRole(Role role);
}