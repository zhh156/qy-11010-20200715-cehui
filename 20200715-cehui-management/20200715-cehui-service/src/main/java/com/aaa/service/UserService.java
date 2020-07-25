package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.UserMapper;
import com.aaa.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User> {

     @Autowired
    private UserMapper userMapper;

    /**
        *@Author ymq
        *@Date  10:18
     * 查询所有用户
    **/

    public PageInfo selectAllUser(User user, Integer pageNo, Integer pageSize){
        try {
            if(!"".equals(pageNo) && !"".equals(pageSize)){
                PageInfo<User> userPageInfo =selectListByPage(user,pageNo,pageSize);

                if (!"".equals(userPageInfo) && null !=userPageInfo){
                    return  userPageInfo;
                }else {
                    PageInfo<User> userPageInfo1 =selectListByPage(user,pageNo,pageSize);
                return userPageInfo1;
                }

               }else {
                return null;

        }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
}




    /**
     *@Author ymq
     *@Date 2020/7/16 11:02
     * 根据主键id删除用户
     **/
    public Integer deleteUser(User user){
        //判断前段是否传值成功
        if (!"".equals(user) && null !=user){
            try {
                //执行删除操作
                Integer delete =delete (user);
                if (delete>0){
                    return delete;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    /**
             *@Author ymq
             *@Date 2020/7/16 11:58
          * 根据主键查询用户信息
         **/

         public User selectUserById(Long id){
             if(!"".equals(id) && null !=id){
                 try {
                     User user =userMapper.selectByPrimaryKey(id);

                     if(!"".equals(user) && null !=user){
                         return user;
                     }

                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
             return null;
         }


       /**
           *@Author ymq
           *@Date 2020/7/16 17:04
        * 使用动态sql实现分页查询
       **/
     public   PageInfo selectUserByFiles(Map map, Integer pageNo, Integer pageSize) {
         PageHelper.startPage(pageNo,pageSize);
         //判断前段是否传值成功
         try {
            //使用动态sql查询数据
             List<User> users =userMapper.selectUserByField(map);
             if(!"".equals(users) && null !=users){
                 //讲查询的结果放入
                 PageInfo<User> userPageInfo =new PageInfo<>(users);
                 //返回分页结果
                 return userPageInfo;
             }
             return null;
         }catch (Exception e){
             e.printStackTrace();
         }
         return null;
     }

    /**
     *@Author ymq
     *@Date 2020/7/17
     * 批量删除用户
     **/
    public Integer delUserAlls(List<Long> ids){
        Example example = Example.builder(User.class).where(Sqls.custom().andIn("id",ids)).build();
        return userMapper.deleteByExample(example);
    }
}

