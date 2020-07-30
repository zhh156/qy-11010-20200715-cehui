package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MenuMapper;
import com.aaa.model.Menu;
import com.aaa.redis.RedisService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.lettuce.core.RedisConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/16 22:42
 * @Description
 *      菜单
 **/
@Service
public class MenuService extends BaseService<Menu> {
    @Value(MENU_KEY)
    private String menuKey;

    @Autowired
    private MenuMapper menuMapper;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/16 22:57
     * @description:
     *      查询一级菜单
     *      菜单信息是需要存入缓存中的，也就是说菜单信息时需要从缓存中拿出来的
     *      ！！实际开发中必须要遵循的原则：所有的业务都必须要卸载service层，controller层不允许出现与业务相关的代码！！
     *
     *      以后写的项目都是前后端分离的（编写接口文档）
     *          前端调后端的controller数据，必须要规定好格式以及参数需要传什么
     *      公司使用的都是ajax，绝对没有同步，全部都是异步请求--->发送过来的和返回回去的都是json数据
     *      json数据格式
     *          {
     *              "key":"value",
     *              "key":"value",
     *              ....
     *          }
     *       json数据转换到java代码中Map是最好用的（因为json和map一模一样都是key-value形式）
     *       到公司之后会经常看到，返回的都是Map，有些公司接收前端发送的数据也是Map
     *
     *       业务是这样的---->如果redis有则从redis中取出，如果redis中没有则从mysql中查询在存入redis中
     *       TODO  现在有一个问题，如果redis中存储的数据与数据库中的数据不一致，那么redis中的数据就是脏数据
     * @param
     * @return java.util.List<com.aaa.model.Menu>
     **/
    public List queryOne(RedisService redisService){
        //1.为了防止每一次都查询数据库，无论缓存中是否有数据，第一步总是先查缓存
        //以后都不允许抛出Exception异常，这个异常是所有异常的父类，太具有概括性了，不够准确
        List menusRedis;
        try{
            //这一步可能会出现异常（无法连接redis）
            menusRedis = redisService.getList(menuKey);
        } catch (RedisConnectionException e){
            //catch中是需要写代码的，不要走到这一步就打印堆栈信息就完事了
            //有可能是因为网络波动，几秒后就会恢复
            //再从redis中查询一次
            menusRedis = redisService.getList(menuKey);
            if(null == menusRedis || menusRedis.size() <= 0){
                //真的说明redis抛异常了
                //有一句话叫做服务器正在维护，请稍后再试！
                //模拟http，定义状态码（200为成功，404是找不到，500为抛异常）
                menusRedis =  null;
            }else{
                //说明真的是网络的原因，现在恢复了，redis又正常运行了
                menusRedis = redisService.getList(menuKey);
            }
            e.printStackTrace();
            return menusRedis;
        }

        //2.判断从redis中取出的数据是否为null/长度是否为0
        if(null == menusRedis || menusRedis.size() <= 0){
            //说明redis中根本没有数据，需要从mysql中查询数据
            List menuList = null;
            try{
                //3.从mysql中进行查询数据
                menuList = menuMapper.queryOne();//这一步也可能会出现问题(数据库连接异常/数据库中可能没有这个库/数据库中国可能没有这个表
            }catch (Exception  e){
                //数据库如果抛出异常了，可以尝试重连（尝试重连三次）
                for (int i = 0; i < 3; i++) {
                    menuList = menuMapper.queryOne();
                    //在尝试重新连接中，有可能数据库恢复了，就可以查到数据了
                    if(menuList.size() > 0){
                        //数据库又重新连接上了
                        menuList = menuMapper.queryOne();
                        //别再去存redis了，因为catch就证明程序已经存在异常了，这里只是补救措施
                    }
                }
                //说明连接三次后还是没有连接上数据库（数据库确实存在问题）
                e.printStackTrace();
                return menuList;
            }

            //4.判断mysql中是否有数据
            if(menuList.size() > 0){
                //说明mysql中有数据
                //5.把mysql中缓存数据存入到redis中
                String setResult = "";
                try{
                    setResult = redisService.set(menuKey,menuList,NX,PX,null);//将查询到的数据放入缓存中也可能会出现异常
                    if("OK".equals(setResult.toUpperCase()) || "1".equals(setResult)){
                        //说明存储成功了，可以返回数据
                        return menuList;
                    }else{
                        //说明redis没有存储成功，在这里宁愿给用户一个错误的信息，也不能给用户null
                        return menuList;
                    }
                }catch (Exception e){
                    //这里的缓存存在问题
                    e.printStackTrace();
                    return menuList;
                }
            }else {
                //说明mysql中没有数据
                return menuList;
            }
        }else{
            //说明缓存中有数据，直接返回查到的数据就可以了
            return menusRedis;
        }
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
    public TokenVo updateMenu(Menu menu,RedisService redisService){
        TokenVo tokenVo1 = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(menu.getMenuId() != null){
            //传过来了
            //2.查询数据库中是否有这条数据
            TokenVo tokenVo = queryOneMenu(menu.getMenuId());
            //3.判断是否有
            if(tokenVo.getIsSuccess()){
                //有
                //4.执行更新操作
                menu.setModifyTime(new Date());
                int i = menuMapper.updateByPrimaryKeySelective(menu);
                //5.判断更新是否成功
                if(i > 0){
                    //成功
                    //说明mysql更新成功
                    //需要对redis缓存中的数据进行删除
                    Long aLong = redisService.delOne(menuKey);
                    if(aLong > 0L){
                        //缓存清空成功
                        return tokenVo1.setIsSuccess(true).setData(i);
                    }else{
                        //缓存清空失败
                        return tokenVo1.setIsSuccess(false).setOperation(5);
                    }
                }else{
                    //数据库更新失败
                    return tokenVo1.setIsSuccess(false).setOperation(6);
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
