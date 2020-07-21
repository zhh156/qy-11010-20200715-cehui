package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.CheckPersonMapper;
import com.aaa.model.CheckPerson;
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
 * @Date Create in 2020/7/18 16:12
 * @Description
 *      检查人员
 **/
@Service
public class CheckPersonService extends BaseService<CheckPerson> {
    @Autowired
    private CheckPersonMapper checkPersonMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 16:17
     * @description:
     *      查询一定百分比的检查人员的信息
     * @param pageNum 当前页数
	 * @param pageSize 每页的条数
	 * @param percentage  查询的百分比数
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo queryCheckPersonByPercentage(Integer pageNum,Integer pageSize,Double percentage){
        //1.查询前端的数据是否传过来了
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
            //2.进行查询操作
            List<Map> maps = checkPersonMapper.queryCheckPersonByPercentage(percentage);
            //3.判断查询是否成功
            if(maps != null){
                //成功
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 19:59
     * @description:
     *      查询最后一个检查人员的id
     * @param
     * @return com.aaa.model.CheckPerson
     **/
    public CheckPerson queryLastId(){
        CheckPerson checkPerson = checkPersonMapper.queryLastId();
        if(checkPerson != null){
            return checkPerson;
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 16:58
     * @description:
     *      插入抽查人员
     * @param checkPerson
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo insertCheckPerson(CheckPerson checkPerson){
        TokenVo tokenVo = new TokenVo();
        //1.因为主键没有进行自增，所以需要自己指定,通过数据库的自增实现
        CheckPerson checkPerson2 = queryLastId();
        Long id = null;
        if(checkPerson2 != null){
            id = checkPerson2.getId() + 1;
        }
        //2.对实体类指定id、创建时间
        checkPerson.setId(id).setCreateTime(new Date());
        //3.进行插入操作
        int insert = checkPersonMapper.insert(checkPerson);
        //4.判断插入是否成功
        if (insert > 0) {
            //插入成功，将受影响的行数传过去
            return tokenVo.setIsSuccess(true).setData(insert);
        }
        //失败，系统异常
        return tokenVo.setIsSuccess(false).setType(4);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 17:07
     * @description:
     *      删除抽查人员
     * @param
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo deleteCheckPerson(CheckPerson checkPerson){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(checkPerson.getId() != null){
            //成功
            //2.查询数据库中是否有这个检查人员
            CheckPerson checkPerson1 = super.selectOne(checkPerson);
            if(checkPerson1 != null){
                //存在
                //3.进行删除操作
                Integer delete = super.delete(checkPerson);
                //4.判断删除操作是否成功
                if(delete > 0){
                    //成功，将受影响的行数传到前端
                    return tokenVo.setIsSuccess(true).setData(delete);
                }
            }else{
                //失败，删除的人员不存在
                return tokenVo.setIsSuccess(false).setOperation(1);
            }
        }
        //失败，系统异常
        return tokenVo.setIsSuccess(false).setType(4);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 17:20
     * @description:
     *      修改抽查人员
     * @param checkPerson
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo updateCheckPerson(CheckPerson checkPerson){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(checkPerson.getId() != null){
            //传过来了
            //2.通过id在数据库中进行查询数据
            CheckPerson cp = new CheckPerson();
            cp.setId(checkPerson.getId());
            CheckPerson checkPerson1 = super.selectOne(cp);
            //3.判断数据库中是否存在这个id
            if(checkPerson1 != null){
                //数据库中存在这个id
                //4.对实体类指定修改时间
                checkPerson.setModifyTime(new Date());
                //5.进行修改操作
                Integer update = super.update(checkPerson);
                //6.判断修改是否成功
                if(update > 0){
                    //成功，将受影响的行数传过去
                    return tokenVo.setIsSuccess(true).setData(update);
                }
            }else{
                //失败，修改的人员不存在
                return tokenVo.setIsSuccess(false).setOperation(1);
            }
        }
        //失败，系统异常
        return tokenVo.setIsSuccess(false).setType(4);
    }
}
