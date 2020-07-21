package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Menu;
import com.aaa.service.MenuService;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/16 22:43
 * @Description
 *      菜单
 **/
@RestController
public class MenuController extends CommonController<Menu> {
    @Autowired
    private MenuService menuService;
    @Override
    public BaseService<Menu> getBaseService() {
        return menuService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 22:59
     * @description:
     *      查询一级菜单
     * @param
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryOne")
    public ResultData queryOne(){
        List<Menu> menus = menuService.queryOne();
        if(menus!=null && menus.size() > 0){
            return querySuccess(menus);
        }
        return queryFailed();
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 8:38
     * @description:
     *      查询一级菜单对应的二级菜单或二级菜单对应的三级菜单
     * @param menuId 一级菜单id
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryTwoOrThree")
    public ResultData queryTwoOrThree(@RequestParam("menuId") Object menuId){
        List<Menu> menus = menuService.queryTwoOrThree(Long.parseLong(menuId + ""));
        if(menus != null){
            return querySuccess(menus);
        }
        return queryFailed();
    }

    /**
     * 通过menu中的某一个字段查询单条数据
     * @param menu
     * @return
     */
    @PostMapping("/queryOneByPrimaryKey")
    public ResultData queryOneByPrimaryKey(@RequestBody Menu menu){
        return super.selectOneT(menu);
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
        TokenVo tokenVo = menuService.queryMenusByMenuNameOrCreateTime(map);
        if(tokenVo.getIsSuccess()){
            return querySuccess(tokenVo);
        }
        return queryFailed();
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
        menu.setCreateTime(new Date());
        return super.addT(menu);
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
        TokenVo tokenVo = menuService.updateMenu(menu);
        if(tokenVo.getIsSuccess()){
            return updateSuccess(tokenVo);
        }
        if(tokenVo.getType() == 1){
            return updateFailed("系统操作异常");
        }
        return updateFailed();
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
        return super.deleteT(menu);
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
        System.out.println(ids);
        Menu menu = new Menu();
        Integer delete = 0;
        for (Object id : ids) {
            menu.setMenuId(Long.parseLong(id+"") );
            Integer delete1 = menuService.delete(menu);
            if(delete1 > 0){
                delete +=1;
            }
        }
        if(delete == ids.size()){
            return deleteSuccess();
        }
        return deleteFailed();
    }
}
