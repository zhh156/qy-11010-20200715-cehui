package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author ymq
     * @Date 2020/7/16 14:55
     * 用户查询
     **/

    @GetMapping("/selectAllUser")
    public ResultData selectAllUser(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return iProjectService.selectAllUser(pageNo, pageSize);
    }


    /**
     * @Author ymq
     * @Date 2020/7/16 18:43
     * 实现分页查询
     **/


    @GetMapping("/selectUserByField")
    public ResultData selectUserByField(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return iProjectService.selectUserByField(pageNo, pageSize);
    }


    /**
     * @Author ymq
     * @Date 2020/7/16 16:07
     * 新增用户
     **/
    @PostMapping("/addUser")
    public ResultData addUser(@RequestBody User user) {
        return iProjectService.addUser(user);
    }

    /**
     * @Author ymq
     * @Date 2020/7/17 16:31
     * 修改用户信息
     **/
    @PostMapping("/updateUser")
    public ResultData updateUser(@RequestBody User user) {
        return iProjectService.updateUser(user);
    }

    /**
     * @Author ymq
     * @Date 2020/7/16 19:32
     * 删除用户
     **/
    @PostMapping("/deleteUser")
    public ResultData deleteUser(@RequestBody User user) {
        return iProjectService.deleteUser(user);
    }
}
