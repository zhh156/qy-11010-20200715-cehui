package com.aaa.base;


import com.aaa.utils.Map2BeanUtils;
import com.aaa.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.OrderStatic.ASC;
import static com.aaa.staticproperties.OrderStatic.DESC;

/**
* @author zhh
* @date 2020/7/8 16:49
* 通用service
*/
public abstract class BaseService<T> {
    /**
     * 自动注入通用mapper，可以通过T来指定mapper的类型
     */
    @Autowired
    private Mapper<T> mapper;

    /**
     * 全局变量，缓存子类的泛型类型
     */
    private Class<T> cache = null;

    /**
     * 确定mapper到底是哪个mapper（userMapper、roleMapper....）
     * @return
     */
    protected Mapper getMapper(){
        return mapper;
    }

    /**
     * 获取子类的泛型
     * @return
     */
    public Class<T> getTypeArgument(){
        if(null == cache){
            cache = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
     * 新增数据
     * @param t 泛型
     * @return
     */
    public Integer add(T t){
        return mapper.insert(t);
    }

    /**
     * 根据主键进行删除
     * @param t 主键
     * @return
     */
    public Integer delete(T t){
        return mapper.deleteByPrimaryKey(t);
    }

    /**
     * 根据主键进行批量删除
     * @param ids
     * @return
     */
    public Integer batchDelete(List ids){
        /**
         * delete from user(getTypeArgument()) where 1=1(Sqls.custom()-自定义的条件) and id in (1,2,3)
         * andIn("id",ids)-id为数据表的主键名称，ids为包含的id列表
         */
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id",ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * 更新操作
     * @param t
     * @return
     */
    public Integer update(T t){
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 更新多条数据
     * @param t
     * @param ids
     * @return
     */
    public Integer batchUpdate(T t,List<Integer> ids){
        /**
         * update user set username=#{username} where 1=1 and id in （1,2,3）
         */
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id",ids)).build();
        return mapper.updateByExampleSelective(t,example);
    }

    /**
     * 查询一条数据
     * @param t 主键/唯一键
     * @return
     */
    public T selectOne(T t){
        return mapper.selectOne(t);
    }

    /**
     * 实现自定义条件查询一条数据
     * @param where 自定一的where查询条件
     * @param fileds 需要查询的字段
     * @return
     */
    public T selectOneByFiled(Sqls where,String... fileds){
        return (T) selectByFileds(null,null,where,null,null,fileds);
    }

    /**
     * 实现列表的查询
     * @param t
     * @return
     */
    public List<T> selectList(T t){
        return mapper.select(t);
    }

    /**
     * 实现列表分页查询
     * @param t
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<T> selectListByPage(T t,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> tPageInfo = new PageInfo<T>(select);
        return tPageInfo;

    }
    /**
     * 实现自定义条件查询字段列表
     * @param sqls
     * @param orderByFiled
     * @param orderKey
     * @param fileds
     * @return
     */
    public List<T> selectListByFiled(Sqls sqls,String orderByFiled,String orderKey,String... fileds){
        return selectByFileds(null,null,sqls,orderByFiled,orderKey,fileds);
    }

    /**
     * 实现自定义条件查询字段列表的分页
     * @param pageNum
     * @param pageSize
     * @param sqls
     * @param orderByFiled
     * @param orderKey
     * @param fileds
     * @return
     */
    public PageInfo<T> selectListByPageAndFiled(Integer pageNum,Integer pageSize,Sqls sqls,String orderByFiled,String orderKey,String... fileds){
        return new PageInfo<T>(selectByFileds(pageNum,pageSize,sqls,orderByFiled,orderKey,fileds));
    }
    /**
     * 实现查询通用。不但可以作用于分页，还可以作用于排序和多条件查询全部信息或者某些字段
     * @param pageNum 当前页数
     * @param pageSize 每页的条数
     * @param sqls 自定义的where查询条件
     * @param orderByFiled 针对哪个字段进行排序
     * @param orderKey 排序（顺序、倒序）
     * @param fileds 指定查询的字段
     * @return
     */
    public List<T> selectByFileds(Integer pageNum,Integer pageSize,Sqls sqls,String orderByFiled,String orderKey,String... fileds){
        Example.Builder builder=null;
        if(fileds == null || fileds.length == 0){
            //查询所有的数据
            builder = Example.builder(getTypeArgument());
        }else{
            //查询所有的数据中的某些字段
            builder = Example.builder(getTypeArgument()).select(fileds);
        }
        if(sqls != null){
            //使用用户自定义的where条件
            builder = builder.where(sqls);
        }
        if(orderByFiled != null ){
            if((orderKey.toUpperCase()).equals(ASC)){
                builder = builder.orderByAsc(orderByFiled);
            }else if((orderKey.toUpperCase()).equals(DESC)){
                builder = builder.orderByDesc(orderByFiled);
            }else {
                builder = builder.orderByDesc(orderByFiled);
            }
        }
        Example example = builder.build();
        //进行分页
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        return getMapper().selectByExample(example);
    }

    /**
     * map转换为实体类型
     * @param map
     * @return
     */
    public T newInstance(Map map){
        return (T) Map2BeanUtils.map2Bean(map,getTypeArgument());
    }

    /**
     * 获取spring容器/获取spring的上下文
     * 在项目开始运行的时候，会去加载spring的配置
     *      如果需要在项目启动的时候也去加载自己的配置文件
     *      在spring的源码中有一个必须要看的方法（init（））
     *      init（）--->就是在项目启动的时候去加载spring的配置
     *      如果你的项目中也需要把某一些配置一开始就托管给spring就需要获取到spring的上下文（ApplicationContext）
     * @return
     */
    public ApplicationContext getApplicationContext(){
        return SpringContextUtils.getApplicationContext();
    }
}
