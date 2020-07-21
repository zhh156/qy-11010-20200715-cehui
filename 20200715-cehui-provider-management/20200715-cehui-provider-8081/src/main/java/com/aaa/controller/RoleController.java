package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Role;
import com.aaa.service.RoleMenuService;
import com.aaa.service.RoleService;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.aaa.status.LoginStatus.*;
import static com.aaa.status.OperationStatus.*;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/16 10:12
 * @Description
 *      角色表
 **/
@RestController
public class RoleController extends CommonController<Role> {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public BaseService<Role> getBaseService() {
        return roleService;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 10:15
     * @description:
     *      查询所有角色,带有分页
     * @param
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAllRole")
    public ResultData queryAllRole(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        TokenVo tokenVo = roleService.queryRole(pageNum, pageSize, null);
        if(tokenVo.getIsSuccess()){
            return querySuccess(tokenVo);
        }
        return queryFailed();
    }

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
        TokenVo tokenVo = roleService.queryRolesByRoleNameOrCreateTime(map);
        if(tokenVo.getIsSuccess()){
            return querySuccess(tokenVo);
        }
        return queryFailed();
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
        return super.selectOneT(role);
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
        TokenVo tokenVo = roleService.selectOneRoleMenu(role);
        if(tokenVo.getIsSuccess()){
            return querySuccess(tokenVo);
        }
        return queryFailed();
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 11:11
     * @description:
     *      更新角色表
     * @param map
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateRole")
    public ResultData updateRole(@RequestBody Map map){
        //1.更新角色表
        TokenVo tokenVo = roleService.updateRole(map);
        //2.判断是否成功
        if(tokenVo.getIsSuccess()){
            //成功
            //3.更新权限
            Boolean updateRoleMenu = roleMenuService.updateRoleMenu(map,(Integer) tokenVo.getData());
            //4.判断权限是否更新成功
            if(updateRoleMenu){
                //成功
                return updateSuccess();
            }
        }
        return updateFailed();
    }


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 16:12
     * @description:
     *      添加角色同时添加角色的权限信息
     * @param map
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/addRole")
    public ResultData addRole(@RequestBody Map map){
        //1.插入角色信息
        Integer integer = roleService.addRole(map);
        //2.判断角色插入是否成功
        if(integer != null){
            //3.成功后插入角色对应的权限信息
            Boolean aBoolean = roleMenuService.addRoleMenu(map, integer);
            //4.判断角色对应的权限信息是否插入成功
            if(aBoolean){
                //插入成功
                return addSuccess();
            }
        }
        return addFailed();
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
        //1.删除角色信息,并放入tokenVo中
        TokenVo tokenVo = roleService.deleteRole(role);
        //2.判断删除是否成功
        if(tokenVo.getIsSuccess()){
            //成功
            //3.删除角色对应的权限
            Boolean aBoolean = roleMenuService.deleteRoleMenus(Integer.parseInt(tokenVo.getData().toString()));
            //4.判断删除权限是否成功
            if(aBoolean){
                //成功
                return deleteSuccess("删除成功");
            }
        }else{
            //系统异常
            if(tokenVo.getType()==4){
                return deleteFailed(OPERATION_WRONG.getMsg());
            }
            //用户不存在
            if(tokenVo.getOperation()==1){
                return deleteFailed(USER_NOT_EXIST.getMsg());
            }
        }
        return deleteFailed(OPERATION_WRONG.getMsg());
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
        if(ids != null && ids.size() > 0){
            Role role = new Role();
            //用于判断权限删除是否成功
            Integer result = 0;
            for (Object id : ids) {
                //1.将id存入role实体类中
                role.setRoleId(Long.parseLong(id.toString()));
                //2.删除角色信息,并放入tokenVo中
                TokenVo tokenVo = roleService.deleteRole(role);
                //2.判断删除是否成功
                if(tokenVo.getIsSuccess()){
                    //成功
                    //3.删除角色对应的权限
                    Boolean aBoolean = roleMenuService.deleteRoleMenus(Integer.parseInt(tokenVo.getData().toString()));
                    //4.判断删除权限是否成功
                    if(aBoolean){
                        //成功
                        result = result + 1;
                    }
                }else{
                    //系统异常
                    if(tokenVo.getType()==4){
                        return deleteFailed(OPERATION_WRONG.getMsg());
                    }
                    //用户不存在
                    if(tokenVo.getOperation()==1){
                        return deleteFailed(USER_NOT_EXIST.getMsg());
                    }
                }
            }
            //判断删除的角色的数量和传入的角色数量是否相同
            if(result == ids.size()){
                //相同
                return deleteSuccess();
            }
        }
        return deleteFailed("删除时数据不能为空");
    }
}
