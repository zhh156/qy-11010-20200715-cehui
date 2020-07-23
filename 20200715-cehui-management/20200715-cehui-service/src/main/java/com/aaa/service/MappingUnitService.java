package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MappingUnitMapper;
import com.aaa.model.MappingUnit;
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
 * @Date Create in 2020/7/17 9:19
 * @Description
 *      测绘单位
 **/
@Service
public class MappingUnitService extends BaseService<MappingUnit> {
    @Autowired
    private MappingUnitMapper mappingUnitMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 10:32
     * @description:
     *      查询所有，带分页
     * @param pageNum
	 * @param pageSize
     * @return java.lang.Object
     **/
    public PageInfo<MappingUnit> queryAll(Integer pageNum, Integer pageSize){
        //1.判断前端的pageNum和pageSize是否传过来
        if(pageNum != null && pageSize != null ){
            //传过来
            //2.查询
            PageInfo<MappingUnit> mappingUnitPageInfo = super.selectListByPage(null, pageNum, pageSize);
            //3.判断查询是否成功
            if(mappingUnitPageInfo != null ){
                //成功
                return mappingUnitPageInfo;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 17:32
     * @description:
     *      根据单位名进行模糊查询
     * @param unitName
     * @return TokenVo
     **/
    public TokenVo queryMappingUnitByUnitName(String unitName){
        TokenVo tokenVo = new TokenVo();
        //1.判断前方的数据是否传过来了
        if(unitName != null){
            //传过来了
            //2.进行查询
            List<MappingUnit> mappingUnits = mappingUnitMapper.queryMappingUnitByUnitName(unitName);
            //3.判断查询是否成功
            if(mappingUnits != null){
                //成功
                return tokenVo.setIsSuccess(true).setData(mappingUnits);
            }
        }
        return tokenVo.setIsSuccess(false);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 10:40
     * @description:
     *      查询单个信息
     * @param mappingUnit
     * @return com.aaa.model.MappingUnit
     **/
    public MappingUnit queryOneMappingUnit(MappingUnit mappingUnit){
        //1.判断前端的数据是否传过来
        if(mappingUnit != null){
            //传过来了
            //2.查询单条数据
            MappingUnit mappingUnit1 = super.selectOne(mappingUnit);
            //3.判断查询是否成功
            if(mappingUnit1 != null){
                //成功
                return mappingUnit1;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 15:52
     * @description:
     *      根据百分比和所在区域对单位进行查询
     * @param percentage
     * @param ownedDistrict
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo queryMappingUnitByPercentage(Double  percentage, String ownedDistrict,Integer pageNum,Integer pageSize){
        //1.判断前端的数据是否传到后端
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
            //2.进行查询数据
            List<Map> maps = mappingUnitMapper.queryMappingUnitByPercentage(percentage,  ownedDistrict);
            //3.判断查询是否成功
            if(maps != null){
                //成功
                //4.进行分页
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 8:53
     * @description:
     *      根据主键id查询单位信息
     * @param id 单位的主键id
     * @return com.aaa.model.MappingUnit
     **/
    public MappingUnit queryMappingUnitByPrimaryKey(Long id){
        //1.判断数据是否传过来了
        if(id != null){
            //传过来了
            //2.通过主键进行查询操作
            MappingUnit mappingUnit = mappingUnitMapper.selectByPrimaryKey(id);
            //3.判断操作是否成功
            if(mappingUnit != null){
                //成功
                return mappingUnit;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 18:17
     * @description:
     *      修改单位的信息
     * @param mappingUnit
     * @return com.aaa.base.ResultData
     **/
    public TokenVo updateMappingUnitByUserId(MappingUnit mappingUnit){
        TokenVo tokenVo = new TokenVo();
        //1.判断数据是否从前端穿过来
        if(mappingUnit != null){
            //传过来了
            //2.对对象进行赋值操作
            mappingUnit.setModifyTime(new Date());
            if(mappingUnit.getAuditStatus() == null){
                mappingUnit.setAuditStatus(3);
            }
            //3.进行修改操作
            Integer update = mappingUnitMapper.updateByPrimaryKeySelective(mappingUnit);
            //4.判断操作是否成功
            if(update > 0){
                //成功
                return tokenVo.setIsSuccess(true).setData(update);
            }
        }
        return tokenVo.setIsSuccess(false);
    }


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 8:45
     * @description:
     *     查询修改待审核的单位
     * @param
     * @return java.util.List<com.aaa.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> queryMappingUnitByAuditStatusAndNoCertificate(Integer pageNum,Integer pageSize,String unitName){
        //1.查看前端的数据是否传过来了
        if(pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.进行查询操作
            List<MappingUnit> mappingUnits = mappingUnitMapper.queryMappingUnitByAuditStatusAndNoCertificate(unitName);
            //3.判断是否查询成功
            if(null != mappingUnits){
                //成功
                PageInfo<MappingUnit> pageInfo =  new PageInfo<>(mappingUnits);
                return pageInfo;
            }
        }
        return null;
    }

    /**
     * 对审核后的单位进行审核状态和时间的修改
     * @param mappingUnit
     * @return
     */
    public TokenVo updateOfAudit(MappingUnit mappingUnit){
        TokenVo tokenVo = new TokenVo();
        Integer integer = mappingUnitMapper.updateOfAudit(mappingUnit);
        if(integer > 0){
            return tokenVo.setIsSuccess(true).setData(integer);
        }
        return tokenVo.setIsSuccess(false);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 21:42
     * @description:
     *      查询注册待审核的单位
     * @param pageNum
	 * @param pageSize
	 * @param unitName
     * @return com.github.pagehelper.PageInfo<com.aaa.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> queryMappingUnitByAuditStatusAndCertificate(Integer pageNum,Integer pageSize,String unitName){
        //1.查看前端的数据是否传过来了
        if(pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.进行查询操作
            List<MappingUnit> mappingUnits = mappingUnitMapper.queryMappingUnitByAuditStatusAndCertificate(unitName);
            //3.判断是否查询成功
            if(null != mappingUnits){
                //成功
                PageInfo<MappingUnit> pageInfo =  new PageInfo<>(mappingUnits);
                return pageInfo;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/22 19:03
     * @description:
     *      通过userId对单位信息进行更改
     * @param userId
     * @return com.aaa.model.MappingUnit
     **/
    public Integer updateMappingUnitByUserId(Long userId,MappingUnit mappingUnit){
        MappingUnit mappingUnit1 = new MappingUnit();
        if(userId != null){
            //1.根据userId查询单位的信息
            mappingUnit1 = super.selectOne(mappingUnit1.setUserId(userId));
            //2.判断查询是否成功
            if(mappingUnit1.getId() != null){
                //成功
                //3.更新单位的信息
                Integer update = super.update(mappingUnit.setId(mappingUnit1.getId()));
                //4.判断更新是否成功
                if(update > 0){
                    return update;
                }
            }
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/22 20:26
     * @description:
     *      主页系统中的测绘单位
     * @param unitName
	 * @param ownedDistrict
	 * @param qualificationLevel
     * @return com.aaa.vo.TokenVo
     **/
    public List<MappingUnit> queryMappingUnitMain(String unitName,String ownedDistrict,String qualificationLevel){
        List<MappingUnit> mappingUnits = mappingUnitMapper.queryMappingUnitMain(unitName, ownedDistrict, qualificationLevel);
        if(mappingUnits != null){
            return mappingUnits;
        }
        return null;
    }
}
