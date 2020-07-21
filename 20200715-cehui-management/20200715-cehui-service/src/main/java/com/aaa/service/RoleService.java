package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.RoleMapper;
import com.aaa.model.Role;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/16 10:10
 * @Description
 *      角色
 **/
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 21:00
     * @description:
     *      查询角色信息（全部/部分/一个）
     * @param pageNum
	 * @param pageSize
	 * @param role
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo queryRole(Integer pageNum,Integer pageSize,Role role){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.查询所有
            List<Role> roles = super.selectList(role);
            //3.判断查询是否成功
            if(roles != null){
                //成功
                //4.进行分页
                PageInfo<Role> pageInfo = new PageInfo<>(roles);
                return tokenVo.setIsSuccess(true).setData(pageInfo);
            }
        }
        return tokenVo.setIsSuccess(false);
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
    public TokenVo queryRolesByRoleNameOrCreateTime(Map map){
        TokenVo tokenVo = new TokenVo();
        Integer pageNum = (Integer)map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        String roleName = (String) map.get("roleName");
        String  startTime = (String) map.get("startTime");
        String endTime = (String) map.get("endTime");
        //1.判断前端数据是否传过来
        if(pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.进行查询操作
            List<Role> roles = roleMapper.queryRolesByRoleNameOrCreateTime(roleName, startTime, endTime);
            //3.判断查询是否成功
            if(roles != null){
                //成功
                //4.进行分页操作
                PageInfo<Role> pageInfo = new PageInfo<>(roles);
                return tokenVo.setIsSuccess(true).setData(pageInfo);
            }
        }
        return tokenVo.setIsSuccess(false);
    }



    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 14:10
     * @description:
     *      查询一个角色信息，包括权限
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    public TokenVo selectOneRoleMenu(Role role){
        TokenVo tokenVo = new TokenVo();
        //1.查询前端的数据是否传过来
        if(role != null){
            //传过来了
            //2.进行查询操作
            List<Map<String, Object>> list = roleMapper.selectOneRoleMenu(role);
            //3.判断查询是否成功
            if(list != null){
                //成功
                return tokenVo.setIsSuccess(true).setData(list);
            }
        }
        return tokenVo.setIsSuccess(false);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 15:08
     * @description:
     *      添加角色信息
     * @param map
     * @return java.lang.Object
     **/
    public Integer addRole(Map map){
        //1.将前端获取到的map中的角色信息封装到Role中
        Role role = new Role().setRoleName((String) map.get("roleName")).setRemark((String) map.get("remark")).setCreateTime(new Date());
        /*//2.将角色添加到角色表中
        Integer add = super.add(role);
        //3.判断是否添加成功
        if(add > 0){
            //添加成功
            //4.获取最新添加的角色的id并返回
            return roleMapper.getAddRoleId();
        }*/
        /**
         * 如果在插入操作中想要获取插入数据的主键id
         *      1.需要在xml文件中添加，其中useGeneratedKeys-设置是否使用JDBC的getGeneratedKeys方法获取主键并赋值到keyProperty设置的实体类属性中
         *         <insert id="" useGeneratedKeys="true" keyProperty="实体类中的属性名" keyColumn="数据库中查询出来的字段名"></insert>
         *      2.在插入成功后，可以使用插入的实体类来获取主键id的值
         *          实体类.get实体类中的主键名()
         */
        //2.进行插入操作，同时返回插入数据的主键
        Integer integer = roleMapper.insertRole(role);
        if(integer != null){
            //3.通过实体类获取插入的数据的主键id
            return Integer.parseInt(role.getRoleId()+"");
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 16:36
     * @description:
     *      角色修改
     * @param map map中的key：
     *              roleName：角色名称
     *              roleId：角色id
     *              remark：角色描述
     * @return java.lang.Integer
     **/
    public TokenVo updateRole(Map map){
        TokenVo tokenVo = new TokenVo();
        //1.获取到要进行修改信息的角色id
        Object roleId = map.get("roleId");
        if(roleId != null && ! roleId.equals("")){
            //2.将要进行修改的角色信息封装到Role中
            Role role = new Role().setModifyTime(new Date());
            //3.判断角色名称是否为空，当为null时不会将roleName封装到role对象中
            if(map.get("roleName") != null){
                role.setRoleName((String) map.get("roleName"));
            }
            if(map.get("remark") != null){
                role.setRemark((String) map.get("remark"));
            }
            role.setRoleId(Long.parseLong(roleId.toString()));
            //4.对角色进行修改信息
            Integer update = super.update(role);
            //5.通过影响行数判断是否修改成功
            if(update > 0){
                //修改成功
                return tokenVo.setIsSuccess(true).setData(Integer.parseInt(roleId.toString()));
            }
        }
        return tokenVo.setIsSuccess(false);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 19:56
     * @description:
     *      删除角色信息
     * @param role
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo deleteRole(Role role){
        TokenVo tokenVo = new TokenVo();
        //1.判断传递的参数是否为空
        if(role != null){
            //存在
            //2.查询用户是否在数据库中
            Role role1 = super.selectOne(role);
            //将查询到的角色信息放入tokenVo中
            tokenVo.setData(role1.getRoleId());
            if(role1 != null ){
                //存在数据库中
                //3.进行删除操作
                Integer delete = super.delete(role);
                //4.判断删除是否成功
                if(delete > 0){
                    //删除成功
                    return tokenVo.setIsSuccess(true);
                }
            }else{
                //用户不存在数据库中
                return tokenVo.setIsSuccess(false).setOperation(1);
            }
        }
        //系统异常
        return tokenVo.setIsSuccess(false).setType(4);
    }
}
