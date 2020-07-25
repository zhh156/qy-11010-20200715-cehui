package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MenuMapper;
import com.aaa.model.Menu;
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
 * @Date Create in 2020/7/16 22:42
 * @Description
 *      菜单
 **/
@Service
public class MenuService extends BaseService<Menu> {
    @Autowired
    private MenuMapper menuMapper;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 22:57
     * @description:
     *      查询一级菜单
     * @param
     * @return java.util.List<com.aaa.model.Menu>
     **/
    public List<Menu> queryOne(){
        return menuMapper.queryOne();
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 22:57
     * @description:
     *      查询一级菜单对应的二级菜单或者二级菜单对应的三级菜单
     * @param
     * @return java.util.List<com.aaa.model.Menu>
     **/
    public List<Menu> queryTwoOrThree(Long menuId){
        if(menuId != null && !menuId.equals("")){
            return menuMapper.queryTwoOrThree(menuId);
        }
        return null;
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
    public TokenVo queryMenusByMenuNameOrCreateTime(Map map){
        TokenVo tokenVo = new TokenVo();
        Integer pageNum = (Integer)map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        String menuName = (String) map.get("menuName");
        String  startTime = (String) map.get("startTime");
        String endTime = (String) map.get("endTime");
        //1.判断前端数据是否传过来
        if(pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.进行查询操作
            List<Menu> menus = menuMapper.queryMenusByMenuNameOrCreateTime(menuName, startTime, endTime);
            //3.判断查询是否成功
            if(menus != null){
                //成功
                //4.进行分页操作
                PageInfo<Menu> pageInfo = new PageInfo<>(menus);
                return tokenVo.setIsSuccess(true).setData(pageInfo);
            }
        }
        return tokenVo.setIsSuccess(false);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 16:51
     * @description:
     *      通过主键查询单个菜单
     * @param menuId
     * @return com.aaa.model.Menu
     **/
    public TokenVo queryOneMenu(Long menuId){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端是否将数据传过来了
        if(menuId != null){
            //2.查询
            Menu menu = menuMapper.selectByPrimaryKey(menuId);
            //3.判断查询是否成功
            if(menu != null){
                //成功
                return tokenVo.setIsSuccess(true).setData(menu);
            }
        }
        return tokenVo.setIsSuccess(false);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 17:00
     * @description:
     *      更新菜单
     * @param menu
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo updateMenu(Menu menu){
        TokenVo tokenVo1 = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(menu.getMenuId() != null){
            //传过来了
            //2.查询数据库中是否有这条数据
            TokenVo tokenVo = queryOneMenu((Long) menu.getMenuId());
            //3.判断是否有
            if(tokenVo.getIsSuccess()){
                //有
                //4.执行更新操作
                menu.setModifyTime(new Date());
                int i = menuMapper.updateByPrimaryKeySelective(menu);
                //5.判断更新是否成功
                if(i > 0){
                    //成功
                    return tokenVo1.setIsSuccess(true).setData(i);
                }
            }else {
                return tokenVo1.setIsSuccess(false).setOperation(1);
            }

        }
        return tokenVo1.setIsSuccess(false);
    }


    /**
     *@Author tuo
     *@Date 2020/7/21
     * 根据用户查询信息
     **/
    public List<Map> queryInterface(String username){
        if (username != null && !"".equals(username)) {
            List<Map> maps = menuMapper.queryInterface(username);
            if (maps != null) {
                return maps;
            }
            return null;
        }
        return null;
    }
}
