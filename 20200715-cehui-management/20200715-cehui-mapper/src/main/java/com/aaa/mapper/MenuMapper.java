package com.aaa.mapper;

import com.aaa.model.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MenuMapper extends Mapper<Menu> {
    /**
     * 查询一级菜单
     * @return
     */
    List<Menu> queryOne();

    /**
     * 查询一级菜单对应的二级菜单或二级菜单对应的三级菜单
     * @param menuId
     * @return
     */
    List<Menu> queryTwoOrThree(@Param("menuId") Long menuId);

    /**
     * 根据菜单名称或者创建时间进行查询
     * @param menuName
     * @param startTime
     * @param endTime
     * @return
     */
    List<Menu> queryMenusByMenuNameOrCreateTime(@Param("menuName")String menuName, @Param("startTime")String startTime, @Param("endTime")String endTime);


    /**
     *@Author tuo
     *@Date 2020/7/21
     * 根据用户权限显示信息
     **/
    List<Map> queryInterface(String username);
}