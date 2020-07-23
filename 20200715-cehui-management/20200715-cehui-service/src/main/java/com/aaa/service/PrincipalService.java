package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.PrincipalMapper;
import com.aaa.model.Principal;
import com.aaa.utils.IDUtils;
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
 * @Date Create in 2020/7/17 10:17
 * @Description
 *      负责人信息
 **/
@Service
public class PrincipalService extends BaseService<Principal> {
    @Autowired
    private PrincipalMapper principalMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:11
     * @description:
     *      查询某个单位的法人信息
     * @param userId 单位中的userId
	 * @param pageNum 当前页数
	 * @param pageSize  每页的条数
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo queryPrincipalByUserId(Long userId,Integer pageNum,Integer pageSize){
        //1.判断前端的数据是否传到这里
        if(userId != null && pageNum != null && pageSize != null){
            //已经传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.通过userId查询法人信息
            List<Map> maps = principalMapper.queryPrincipalByUserId(userId);
            //3.判断查询是否成功
            if(maps != null){
                //成功后进行返回
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 14:52
     * @description:
     *      添加单位的法人代表
     * @param type
	 * @param name
	 * @param idType
	 * @param idNumber
	 * @param age
	 * @param sex
	 * @param workYear
	 * @param duty
	 * @param title
	 * @param mappingYear
	 * @param major
	 * @param userId
     * @return com.aaa.vo.TokenVo
     **/
    public Principal insertPrincipalByUserId(String type,String name,String idType,String idNumber,
                                           Integer age,Integer sex,Integer workYear,String duty,
                                           String title,Integer mappingYear,String major,Long userId){
        //1.判断数据是否从前端传过来了
        if(type != null  && name != null && null != idType && null != idNumber && null != userId){
            //传过来了
            //2.将数据封装到principal对象中
            Principal principal = new Principal();
            //2.1指定id编号
            Long id = Long.parseLong(IDUtils.getTimeId());
            //2.2.判断数据库中是否已经存在将要设置的id(通过循环，查询出来的为空时跳出循环)
            Principal principal1 = null;
            do{
                principal1 = principalMapper.selectByPrimaryKey(id);
                id = Long.parseLong(IDUtils.getTimeId());
            }while(principal1 != null);
            //2.3.将数据封装到对象中
            principal.setId(id).setType(type).setName(name).setIdType(idType).setIdNumber(idNumber)
                    .setAge(age).setSex(sex).setWorkYear(workYear).setDuty(duty).setTitle(title)
                    .setMappingYear(mappingYear).setMajor(major).setUserId(userId).setCreateTime(new Date());
            //3.进行插入操作
            Integer add = super.add(principal);
            //4.判断插入操作是否成功
            if(add > 0){
                return principal;
            }
        }
        return null;
    }


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 15:40
     * @description:
     *      修改法人代表信息
     * @param type
	 * @param name
	 * @param idType
	 * @param idNumber
	 * @param age
	 * @param sex
	 * @param workYear
	 * @param duty
	 * @param title
	 * @param mappingYear
	 * @param major
	 * @param userId
	 * @param id
     * @return com.aaa.model.Principal
     **/
    public Principal updatePrincipalById(String type,String name,String idType,String idNumber,
                                             Integer age,Integer sex,Integer workYear,String duty,
                                             String title,Integer mappingYear,String major,Long userId,Long id){
        //1.判断数据是否从前端传过来了
        if(type != null  && name != null && null != idType && null != idNumber && null != userId){
            //传过来了
            //2.将数据封装到principal对象中
            Principal principal = new Principal();
            //2.3.将数据封装到对象中
            principal.setId(id).setType(type).setName(name).setIdType(idType).setIdNumber(idNumber)
                    .setAge(age).setSex(sex).setWorkYear(workYear).setDuty(duty).setTitle(title)
                    .setMappingYear(mappingYear).setMajor(major).setUserId(userId).setModifyTime(new Date()).setId(id);
            //3.进行插入操作
            Integer add = super.update(principal);
            //4.判断插入操作是否成功
            if(add > 0){
                return principal;
            }
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 16:12
     * @description:
     *      删除法人
     * @param id
     * @return java.lang.Boolean
     **/
    public Boolean deletePrincipalById(Long id){
        //1.判断前端是否将数据传过来了
        if(id != null){
            //传过来了
            //2.查看是否有这个法人
            Principal principal = principalMapper.selectByPrimaryKey(id);
            //3.判断是否有
            if(principal != null){
                //有
                //4.进行删除操作
                int i = principalMapper.deleteByPrimaryKey(id);
                //5.判断删除是否成功
                if(i > 0){
                    //成功
                    return true;
                }else{
                    return false;
                }
            }else{
                return true;
            }
        }
        return false;
    }
}
