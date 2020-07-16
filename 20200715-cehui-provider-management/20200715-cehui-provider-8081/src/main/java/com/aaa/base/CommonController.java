package com.aaa.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.Sqls;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
* @author zhh
* @date 2020/7/8 17:05
*
*/
public abstract class CommonController<T> extends BaseController {
    public abstract BaseService<T> getBaseService();

    /**
     * 钩子函数，在新增之前所执行的业务
     * @param map
     */
    protected void beforeAdd(Object map){
        //TODO 钩子函数，在插入之前你需要执行的某些业务的时候进行调用
    }

    /**
     * 钩子函数，在新增之后所执行的业务
     * @param map
     */
    protected void afterAdd(Object map){
        //TODO 钩子函数，在插入之后你需要执行的某些业务的时候进行调用
    }

    /**
     * 添加操作
     * @param map
     * @return
     */
    public ResultData add(@RequestBody Map map){
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            Integer add = getBaseService().add(instance);
            if(add > 0){
                afterAdd(map);
                return addSuccess(add);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return addFailed();
    }

    /**
     * 添加操作，通过实体类
     * @param t
     * @return
     */
    public ResultData add(@RequestBody T t){
        beforeAdd(t);
        try {
            Integer add = getBaseService().add(t);
            if(add > 0){
                afterAdd(t);
                return addSuccess(add);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return addFailed();
    }

    /**
     * 删除操作
     * @param map
     * @return
     */
    public ResultData delete(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            Integer delete = getBaseService().delete(instance);
            if(delete > 0){
                return deleteSuccess();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return deleteFailed();
    }

    /**
     * 删除操作，通过实体类id
     * @param t
     * @return
     */
    public ResultData delete(@RequestBody T t){
        try {
            Integer delete = getBaseService().delete(t);
            if(delete > 0){
                return deleteSuccess();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return deleteFailed();
    }

    /**
     * 批量删除
     * @param map
     * @return
     */
    public ResultData batchDelete(@RequestBody Map map){
        List instance = (List)getBaseService().newInstance(map);
        try {
            Integer delete = getBaseService().batchDelete(instance);
            if(delete > 0){
                return deleteSuccess(delete);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return deleteFailed();
    }

    /**
     * 更新数据
     * @param map
     * @return
     */
    public ResultData update(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            Integer update = getBaseService().update(instance);
            if(update > 0){
                return updateSuccess(update);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return updateFailed();
    }
    /**
     * 更新数据,通过实体类
     * @param t
     * @return
     */
    public ResultData update(@RequestBody T t){
        try {
            Integer update = getBaseService().update(t);
            if(update > 0){
                return updateSuccess(update);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return updateFailed();
    }

    /**
     * 同时修改多条数据
     * @param map
     * @param ids
     * @return
     */
    public ResultData update(@RequestBody Map map, @RequestParam("Integer[]") List<Integer> ids){
        T instance = getBaseService().newInstance(map);
        try {
            Integer update = getBaseService().batchUpdate(instance,ids);
            if(update > 0){
                return updateSuccess(update);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return updateFailed();
    }

    /**
     * 查询一条数据
     * @param map
     * @return
     */
    public ResultData selectOne(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            T t = getBaseService().selectOne(instance);
            if(t != null && !t.equals("")){
                return querySuccess(t);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }
    /**
     * 查询一条数据中的某些字段
     * @param sqls
     * @param fileds
     * @return
     */
    public ResultData selectOneByFiled(@RequestParam("sqls") Sqls sqls, @RequestParam("String[]") String... fileds){
        try {
            T t = getBaseService().selectOneByFiled(sqls, fileds);
            if(t != null && !t.equals("")){
                return querySuccess(t);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }

    /**
     * 查询多条数据
     * @param map
     * @return
     */
    public ResultData selectList(@RequestBody Map map){
        T instance = getBaseService().newInstance(map);
        try {
            List<T> ts = getBaseService().selectList(instance);
            if(ts != null && ts.size() > 0){
                return querySuccess(ts);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }

    /**
     * 实现带有条件的分页多条数据某些字段的查询
     * @param pageNum
     * @param pageSize
     * @param sqls
     * @param orderByFiled
     * @param orderKey
     * @param fileds
     * @return
     */
    public ResultData selectListByPageAndFiled(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize, @RequestParam("sqls") Sqls sqls, @RequestParam("orderByFiled")String orderByFiled,
                                               @RequestParam("orderKey") String orderKey, @RequestParam("String[]") String... fileds){
        try {
            PageInfo<T> tPageInfo = getBaseService().selectListByPageAndFiled(pageNum, pageSize, sqls, orderByFiled, orderKey, fileds);
            if(tPageInfo != null && !tPageInfo.equals("")){
                return querySuccess(tPageInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }

    /**
     * 没有带条件的分页查询
     * @param map
     * @return
     */
    public ResultData selectListByPage(@RequestBody Map map, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        T instance = getBaseService().newInstance(map);
        try {
            PageInfo<T> tPageInfo = getBaseService().selectListByPage(instance, pageNum, pageSize);
            if(tPageInfo != null && !tPageInfo.equals("")){
                return querySuccess(tPageInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }

    /**
     * 对象的分页查询
     * @param t
     * @return
     */
    public ResultData selectListByPageT(@RequestBody T t, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize,@RequestParam("String[]") String... fileds){
        try {
            PageInfo<T> tPageInfo = getBaseService().selectListByPageAndFiled(pageNum,pageSize,null,null,null,fileds);
            if(tPageInfo != null && !tPageInfo.equals("")){
                return querySuccess(tPageInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }

    /**
     * 对象的分页查询
     * @param t
     * @return
     */
    public ResultData selectListByPage(@RequestBody T t, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        try {
            PageInfo<T> tPageInfo = getBaseService().selectListByPage(t, pageNum, pageSize);
            if(tPageInfo != null && !tPageInfo.equals("")){
                return querySuccess(tPageInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }

    /**
     * 实现带有条件的排序的列表的字段
     * @param sqls
     * @param orderByFiled
     * @param orderKey
     * @param fileds
     * @return
     */
    public ResultData selectListByPage(@RequestParam("sqls") Sqls sqls, @RequestParam("orderByFiled")String orderByFiled, @RequestParam("orderKey")String orderKey, @RequestParam("String[]")String... fileds){
        try {
            List<T> ts = getBaseService().selectListByFiled(sqls, orderByFiled, orderKey, fileds);
            if(ts != null && ts.size() > 0){
                return querySuccess(ts);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return queryFailed();
    }



    /**
     * 防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象
     * 必须在本地线程中获取对象
     * @return
     */
    public HttpServletRequest getServletRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if(requestAttributes instanceof ServletRequestAttributes){
            servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取当前客户端的session对象，如果不存在，则会重新创建一个
     * @return
     */
    public HttpSession getSession(){
        return getServletRequest().getSession();
    }

    /**
     * 获取当前客户端的session对象（如果不存在，则直接返回null）
     * @return
     */
    public HttpSession getExistSession(){
        return getServletRequest().getSession(false);
    }
}
