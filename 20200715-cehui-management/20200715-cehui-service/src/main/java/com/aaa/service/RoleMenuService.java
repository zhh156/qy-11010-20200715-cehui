package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.RoleMenuMapper;
import com.aaa.model.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/16 15:43
 * @Description
 *      角色菜单中间表
 **/
@Service
public class RoleMenuService extends BaseService<RoleMenu> {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 15:47
     * @description:
     *      在添加角色的时候添加角色对应的权限
     * @param map 前端传来需要存储的数据
	 * @param addRoleId  添加角色后角色对应的id
     * @return java.lang.Boolean
     **/
    public Boolean addRoleMenu(Map map,Integer addRoleId){
        //1.判断角色对应的权限列表是否为空
        if(map.get("roleMenus") != null && map.get("roleMenus") != ""){
            //不为空
            //2.获取角色对应的权限列表
            //因为从前台获取到的数据为Object类型，所以需要将获取到的数据装换为String类型同时去掉可能出在的空格:" [1,2,3]"-->"[1,2,3]"
            String s = map.get("roleMenus").toString().replace(" ","");
            //将获取到的String类型装换为list类型(注意：需要将两端的"["和"]"去掉)："[1,2,3]"-->["1","2","3"]
            String[] roleMenus = s.substring(1, s.length() - 1).split(",");
            RoleMenu roleMenu1 = new RoleMenu();
            Integer add = 0;
            //3.循环添加角色对应的权限信息
            for (String roleMenu : roleMenus) {
                //4.在角色权限中间表的实体类中添加数据
                roleMenu1.setMenuId(Long.parseLong(roleMenu)).setRoleId(Long.parseLong(addRoleId.toString()));
                //5.进行添加
                 add +=super.add(roleMenu1);
            }
            //6.判断角色对应的权限是否都存入数据库中
            if(add == roleMenus.length){
                //全部存入
                return true;
            }else {
                return false;
            }
        }
        return true;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 16:51
     * @description:
     *     修改角色权限信息
     * @param map 前端传来的角色中的权限信息
	 * @param roleId 需要进行权限修改的角色id
     * @return java.lang.Boolean
     **/
    public Boolean updateRoleMenu(Map map,Integer roleId){
        //1.先查询角色对应的权限
        RoleMenu roleMenu2 = new RoleMenu().setRoleId(Long.parseLong(roleId.toString()));
        List<RoleMenu> result = super.selectList(roleMenu2);
        //2.判断角色的权限是否存在
        if(result != null){
            //存在
            //3.进行删除操作
            Integer integer = roleMenuMapper.deleteRoleMenus(roleId);
            //4.判断删除是否成功
            if(integer == result.size()){
                //成功
                //5.调用增加角色信息的方法
                return addRoleMenu(map,roleId);
            }else {
                //失败
                return false;
            }
        }
        //6.权限不存在的情况下，直接进行添加即可
        return addRoleMenu(map,roleId);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 18:10
     * @description:
     *      删除角色对应的权限
     * @param roleId
     * @return java.lang.Boolean
     **/
    public Boolean deleteRoleMenus(Integer roleId){
        //1.先查询角色对应的权限
        RoleMenu roleMenu = new RoleMenu().setRoleId(Long.parseLong(roleId.toString()));
        List<RoleMenu> roleMenus = super.selectList(roleMenu);
        //2.判断角色的权限是否存在
        if(roleMenus!=null && roleMenus.size()>0){
            //存在
            //3.进行删除操作
            Integer i = roleMenuMapper.deleteRoleMenus(roleId);
            //4.判断删除是否成功
            if(i == roleMenus.size()){
                //成功
                return true;
            }else {
                //失败
                return false;
            }
        }
        //5.角色对应的权限不存在，直接返回true
        return true;
    }
}
