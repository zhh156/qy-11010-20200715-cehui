package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController extends CommonController<User> {

    @Autowired
    private UserService userService;


    /**
        *@Author ymq
        *@Date 2020/7/16 11:18
     * 查询所有用户信息
    **/

        @GetMapping("/selectAllUser")
        public ResultData selectAllUser( @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
            PageInfo pageInfo = userService.selectAllUser(null, pageNo, pageSize);
            if(pageInfo.getSize() > 0){
                return querySuccess(pageInfo);
            }
            return queryFailed();
        }

        /**
                *@Author ymq
                *@Date 2020/7/16 11:21
             * 新增用户
            **/
        @PostMapping("/addUser")
        public ResultData addUser(@RequestBody User user){
            System.out.println(user);
            return super.addT(user);
        }

        /**
         * 根据主键删除
         * @param
         * @return
         */
        @PostMapping("/deleteUser")
        public ResultData deleteUser(@RequestBody User user){
            return  super.deleteT(user);
        }


        /**
         * 批量删除用户
         * @param ids
         * @return
         */
    /**
     *@Author ymq
     *@Date 2020/7/18 16:38
     * 批量删除
     **/
    @PostMapping("/delUserAlls")
    public ResultData delUserAlls(@RequestParam("ids") List<Long> ids){
        Integer integer = userService.delUserAlls(ids);
        if (integer > 0) {
            return super.deleteSuccess();
        } else {
            return super.deleteFailed();
        }
    }




    /**
            *@Author ymq
            *@Date 2020/7/16 11:22
         * 根据id 修改用户
        **/
        @PostMapping("/updateUser")

        public ResultData updateUser(@RequestBody User user) {
            return super.updateT(user);
        }


    /**
        *@Author ymq
        *@Date 2020/7/16 17:03
     * 使用动态sql 实现分页查询
     *
    **/
    @GetMapping("/selectUserByField")
    public PageInfo<User> selectUserByField(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){

        PageInfo<User> pageInfo = userService.selectUserByFiles(null,pageNo, pageSize);
        if (!"".equals(pageInfo) && null !=pageInfo){
            return pageInfo;
        }

        return null;
    }







    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }

}
