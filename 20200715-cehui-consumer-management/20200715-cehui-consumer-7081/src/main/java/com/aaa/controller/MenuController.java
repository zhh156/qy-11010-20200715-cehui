package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Menu;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 8:24
 * @Description
 **/
@RestController
public class MenuController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 8:32
     * @description:
     *      查询一级菜单
     * @param
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryOne")
    public ResultData queryOne(){
        return iProjectService.queryOne();
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 8:38
     * @description:
     *      查询一级菜单对应的二级菜单或者二级菜单中的三级菜单
     * @param menuId 一级菜单id
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryTwoOrThree")
    public ResultData queryTwoOrThree(@RequestParam("menuId") Object menuId){
        return iProjectService.queryTwoOrThree(menuId);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 16:19
     * @description:
     *      通过菜单名或创建时间来对菜单进行查询
     * @param map   map中的key：
     *                  pageNum：当前页数
     *                  pageSize：每页条数
     *                  menuName：菜单名（模糊查询）
     *                  startTime：开始时间
     *                  endTime：截止时间
     * @return com.aaa.vo.TokenVo
     **/
    @PostMapping("/queryMenusByMenuNameOrCreateTime")
    public ResultData queryMenusByMenuNameOrCreateTime(@RequestBody Map map){
        return iProjectService.queryMenusByMenuNameOrCreateTime(map);
    }

    /**
     * 通过menu中的某一个字段查询单条数据
     * @param menu
     * @return
     */
    @PostMapping("/queryOneByPrimaryKey")
    public ResultData queryOneByPrimaryKey(@RequestBody Menu menu){
        return iProjectService.queryOneByPrimaryKey(menu);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 8:45
     * @description:
     *      增加菜单
     * @param menu
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertMenu")
    public ResultData insertMenu(@RequestBody Menu menu){
        return iProjectService.insertMenu(menu);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 9:00
     * @description:
     *      更新菜单数据
     * @param menu
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateMenu")
    public ResultData updateMenu(@RequestBody Menu menu){
        return iProjectService.updateMenu(menu);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 9:04
     * @description:
     *      删除单条菜单数据
     * @param menu
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteMenu")
    public ResultData deleteMenu(@RequestBody Menu menu){
        return iProjectService.deleteMenu(menu);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 9:09
     * @description:
     *      删除多个菜单
     * @param ids
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteMenus")
    public ResultData deleteMenus(@RequestBody List<Object> ids){
        return iProjectService.deleteMenus(ids);
    }
    /**
     *@Author tuo
     *@Date 2020/7/21
     * 根据用户查询信息
     **/
    @PostMapping("/queryInterface")
    public ResultData queryInterface(@RequestParam("username")String username){
        return iProjectService.queryInterface(username);
    }
}
