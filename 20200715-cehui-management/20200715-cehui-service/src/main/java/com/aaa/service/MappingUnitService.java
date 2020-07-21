package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.AuditMapper;
import com.aaa.mapper.MappingUnitMapper;
import com.aaa.mapper.PrincipalMapper;
import com.aaa.model.Audit;
import com.aaa.model.MappingUnit;
import com.aaa.model.Principal;
import com.aaa.utils.IDUtils;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Autowired
    private AuditMapper auditMapper;
    @Autowired
    private PrincipalMapper principalMapper;

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
     * @date 2020/7/18 9:27
     * @description:
     *      在单位表中通过unit_name来模糊查询audit_status为2（已提交）的单位
     * @param pageNum
	 * @param pageSize
	 * @param unitName
     * @return com.github.pagehelper.PageInfo<com.aaa.model.MappingUnit>
     **/
    public PageInfo<MappingUnit> queryMappingUnitByAuditStatusByUnitName(Integer pageNum,Integer pageSize,String unitName){
        if(pageNum != null &&pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
            List<MappingUnit> mappingUnits = mappingUnitMapper.queryMappingUnitByAuditStatusByUnitName(unitName);
            if(mappingUnits != null){
                PageInfo<MappingUnit> pageInfo = new PageInfo<>(mappingUnits);
                return pageInfo;
            }
        }
        return null;
    }




    public Integer insertPrincipalByUserId(@RequestBody Principal principal){
        //1.判断前端的数据是否传过来
        if(principal.getUserId() != null){
            //传过来了
            //2.对新增的负责人添加创建时间、主键id
            //2.1.添加时间
            principal.setCreateTime(new Date());
            //2.2.添加id
            long id = Long.parseLong(IDUtils.getUUID());
            Principal principal1 = principalMapper.selectByPrimaryKey(id);
            //2.3.判断数据库中是否有相同的id
            if(principal1 == null){
                //不存在相同的
                //3.进行判断审核是否通过。通过对数据库中的数据进行增加；如果没有通过，则返回审核不通过。
                //TODO 未完成
            }

        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 10:45
     * @description:
     *      对数据进行审核
     *     修改审核表中的数据，通过单位表中的id与审核表中的ref_id进行相关联
     * @param map
     * @return java.lang.Integer
     **/
    public Integer updateAuditByRefId(Map map){
        Audit audit = new Audit();
        //1.判断前端传来的refId是否为空
        if(map.get("refId") != null){
            //不为空
            audit.setRefId((Long) map.get("refId"));
            //2.判断前端传来的status是否为空
            if(map.get("status") != null){
                //3.将map中审核相关的值放入Audit中
                audit.setStatus((Integer) map.get("status")).setSubmitTime(new Date());
                if(map.get("memo") != null){
                    audit.setMemo((String)map.get("memo"));
                }
                //4.进行修改操作
                Integer integer = auditMapper.updateAuditByUserId(audit);
                //5.判断修改操作是否成功
                if(integer > 0){
                    return integer;
                }
            }
        }
        return null;
    }
}
