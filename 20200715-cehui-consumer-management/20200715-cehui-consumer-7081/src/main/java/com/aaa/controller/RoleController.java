package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Role;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/16 10:16
 * @Description
 *      角色
 **/
@RestController
public class RoleController extends BaseController {

    @Autowired
    private IProjectService iProjectService;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 17:22
     * @description:
     *      查询所有角色，带有分页
     * @param pageNum 当前页数
	 * @param pageSize 每页条数
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAllRole")
    public ResultData queryAllRole(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(name="pageSize",defaultValue =  "3") Integer pageSize){
        return iProjectService.queryAllRole(pageNum,pageSize);
    }

    //TODO 待测试，有问题
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 21:56
     * @description:
     *      通过用户名或者创建时间对角色进行查询
     * @param map map中的key：
     *              pageNum：当前页数
     *              pageSize：每页的条数
     *              roleName：角色的名称（模糊查询）
     *              startTime：起始时间
     *              endTime：结束时间
     * @return com.aaa.vo.TokenVo
     **/
    @PostMapping("/queryRolesByRoleNameOrCreateTime")
    public ResultData queryRolesByRoleNameOrCreateTime(@RequestBody Map map){
        return iProjectService.queryRolesByRoleNameOrCreateTime(map);
    }


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 12:00
     * @description:
     *      查询一条角色信息
     * @param role
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/selectOneRole")
    public ResultData selectOneRole(@RequestBody Role role){
        return iProjectService.selectOneRole(role);
    }


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 14:17
     * @description:
     *      查询单个角色的具体信息包括角色对应的权限
     * @param role
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/selectOneRoleMenu")
    public ResultData selectOneRoleMenu(@RequestBody Role role){
        return iProjectService.selectOneRoleMenu(role);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 11:33
     * @description:
     *      删除角色信息
     * @param role
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteRole")
    public ResultData deleteRole(@RequestBody Role role){
        System.out.println(role.getRoleId());
        return iProjectService.deleteRole(role);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 14:24
     * @description:
     *      删除多个角色的信息
     * @param ids
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteRoles")
    public ResultData deleteRoles(@RequestBody List<Object> ids){
        return iProjectService.deleteRoles(ids);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 16:12
     * @description:
     *      添加角色同时添加角色的权限信息
     *          map中的key都有
     *              roleName：角色名称
     *              remark：备注
     *              roleMenus：角色对应的权限列表[]
     * @param map
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/addRole")
    public ResultData addRole(@RequestBody Map map){
        return iProjectService.addRole(map);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 11:11
     * @description:
     *      更新角色表同时更新角色对应的权限信息
     *      map中的key都有
     *             roleId：角色对应的id
     *             roleName：角色名称
     *             remark：备注
     *             roleMenus：角色对应的权限列表[]
     * @param map
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateRole")
    public ResultData updateRole(@RequestBody Map map){
        return iProjectService.updateRole(map);
    }

}
