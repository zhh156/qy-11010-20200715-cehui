package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.TechnicistMapper;
import com.aaa.model.MappingUnit;
import com.aaa.model.Technicist;
import com.aaa.utils.IDUtils;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 10:52
 * @Description
 *      技术人员
 **/
@Service
public class TechnicistService extends BaseService<Technicist> {
    @Autowired
    private TechnicistMapper technicistMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:10
     * @description:
     *      查询某个单位的技术人员信息
     * @param userId 单位中的userId
	 * @param pageNum 当前页数
	 * @param pageSize  每页的条数
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo queryTechnicistByUserId(Long userId,Integer pageNum,Integer pageSize){
        Technicist technicist = new Technicist();
        //1.判断前端的数据是否传过来
        if(userId != null && pageNum != null && pageSize != null){
            //传过来了
            technicist.setUserId(userId);
            //2.通过userId查询单位中的测绘人员
            PageInfo<Technicist> pageInfo = super.selectListByPage(technicist, pageNum, pageSize);
            //3.判断查询是否成功
            if(pageInfo != null){
                //成功
                return pageInfo;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 19:42
     * @description:
     *      添加技术人员信息
     * @param technicist
	 * @param mappingUnitService
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo insertTechnicistByUserId(Technicist technicist,MappingUnitService mappingUnitService,AuditService auditService){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(technicist.getName()!= null  && technicist.getMajorType() != null && null != technicist.getIdType() && null != technicist.getIdNumber()){
            //传过来了
            //2.指定id
            Long id = Long.parseLong(IDUtils.getTimeId());
            //2.1.判断数据库中是否已经存在将要设置的id(通过循环，查询出来的为空时跳出循环)
            Technicist technicist1 = null;
            do{
                technicist1 = technicistMapper.selectByPrimaryKey(id);
                id = Long.parseLong(IDUtils.getTimeId());
            }while(technicist1 != null);
            //2.2.将id封装到实体类中
            technicist.setId(id).setCreateTime(new Date());
            //3.添加技术人员
            int insert = technicistMapper.insert(technicist);
            //4.判断添加是否成功
            if(insert > 0){
                //成功
                //5.修改单位信息
                MappingUnit mappingUnit = new MappingUnit();
                mappingUnit.setUserId(technicist.getUserId());
                //5.1.查询相关单位用户编号的单位信息
                MappingUnit mappingUnit1 = mappingUnitService.queryOneMappingUnit(mappingUnit);
                //5.2.判断是哪种员工人数进行增加
                if(technicist.getPost()=="测绘"){
                    mappingUnit.setSurveyingNum(mappingUnit1.getSurveyingNum() + 1);
                }
                mappingUnit.setStaffNum(mappingUnit1.getStaffNum() + 1);
                //5.3.修改单位信息
                Integer integer = mappingUnitService.updateMappingUnitByUserId(technicist.getUserId(), mappingUnit);
                //5.4.判断修改是否成功
                if(integer != null){
                    //成功
                    return tokenVo.setIsSuccess(true);
                }else{
                    //单位信息修改失败
                    return tokenVo.setIsSuccess(false).setOperation(3);
                }
            }else {
                //技术人员添加失败
                return tokenVo.setIsSuccess(false).setOperation(4);
            }
            /*//查看审核是否通过
            Audit audit = auditService.queryAuditByUserId(technicist.getUserId());
            if(audit.getStatus() == 1){
                //通过
            }*/
        }
        //系统错误
        return tokenVo.setIsSuccess(false);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:01
     * @description:
     *      修改技术人员信息
     * @param technicist
	 * @param mappingUnitService
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo updateTechnicist(Technicist technicist, MappingUnitService mappingUnitService,AuditService auditService){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(technicist.getName()!= null  && technicist.getMajorType() != null && null != technicist.getIdType() && null != technicist.getIdNumber()){
            //通过
            technicist.setModifyTime(new Date());
            //3.修改技术人员
            Technicist technicist1 = technicistMapper.selectByPrimaryKey(technicist.getId());
            int insert = technicistMapper.updateByPrimaryKey(technicist);
            //4.判断是否成功
            if(insert > 0){
                //成功
                //5.修改单位信息
                MappingUnit mappingUnit = new MappingUnit();
                mappingUnit.setUserId(technicist.getUserId());
                //5.1.查询相关单位用户编号的单位信息
                MappingUnit mappingUnit1 = mappingUnitService.queryOneMappingUnit(mappingUnit);
                //5.2.判断技术人员的工作类型的变化，如果之前不是测绘，现在变为了测绘那么测绘人员总数就需要添加一
                if(!technicist.getPost().equals(technicist1.getPost())){
                    if("测绘".equals(technicist.getPost())){
                        if(!"测绘".equals(technicist1.getPost()) || technicist1.getPost() == null){
                            mappingUnit.setSurveyingNum(mappingUnit1.getSurveyingNum() + 1);
                            //5.3.修改单位信息
                            Integer update = mappingUnitService.updateMappingUnitByUserId(technicist.getUserId(),mappingUnit);
                            //5.4.判断修改是否成功
                            if(update > 0){
                                //成功
                                return tokenVo.setIsSuccess(true);
                            }else{
                                //单位信息修改失败
                                return tokenVo.setIsSuccess(false).setOperation(3);
                            }
                        }
                    }else if((!"测绘".equals(technicist.getPost()) || technicist.getPost() == null) && "测绘".equals(technicist1.getPost())){
                        mappingUnit.setSurveyingNum(mappingUnit1.getSurveyingNum() - 1);
                        //5.3.修改单位信息
                        Integer update = mappingUnitService.updateMappingUnitByUserId(technicist.getUserId(),mappingUnit);
                        //5.4.判断修改是否成功
                        if(update > 0){
                            //成功
                            return tokenVo.setIsSuccess(true);
                        }else{
                            //单位信息修改失败
                            return tokenVo.setIsSuccess(false).setOperation(3);
                        }
                    }
                }else{
                    //当前后职位一样时，直接返回成功
                    return tokenVo.setIsSuccess(true);
                }
            }else {
                //技术人员修改失败
                return tokenVo.setIsSuccess(false).setOperation(4);
            }
            /*//查看审核是否通过
            Audit audit = auditService.queryAuditByUserId(technicist.getUserId());
            if(audit.getStatus() == 1){
            }*/
        }
        //系统错误
        return tokenVo.setIsSuccess(false);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:25
     * @description:
     *      删除技术人员
     * @param technicist
	 * @param mappingUnitService
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo deleteTechnicist(Technicist technicist, MappingUnitService mappingUnitService,AuditService auditService){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(technicist.getId()!= null) {
            //2.判断数据库中是否有这个技术人员
            if (technicistMapper.select(technicist) != null) {
                //有
                //3.删除技术人员
                int delete = technicistMapper.delete(technicist);
                //3.判断是否成功
                if (delete > 0) {
                    //成功
                    //5.修改单位信息
                    MappingUnit mappingUnit = new MappingUnit();
                    mappingUnit.setUserId(technicist.getUserId());
                    //5.1.查询相关单位用户编号的单位信息
                    MappingUnit mappingUnit1 = mappingUnitService.queryOneMappingUnit(mappingUnit);
                    //判断是否存在这个技术人员
                    if(mappingUnit1 != null){
                        //存在
                        //5.2.判断技术人员的工作类型的变化，如果之前不是测绘，现在变为了测绘那么测绘人员总数就需要添加一
                        if ("测绘".equals(technicist.getPost())) {
                            mappingUnit.setSurveyingNum(mappingUnit1.getSurveyingNum() - 1);
                        }
                        //5.3.修改单位信息
                        mappingUnit.setStaffNum(mappingUnit1.getStaffNum() - 1);
                        Integer update = mappingUnitService.updateMappingUnitByUserId(technicist.getUserId(),mappingUnit);
                        //5.4.判断修改是否成功
                        if (update > 0) {
                            //成功
                            return tokenVo.setIsSuccess(true);
                        } else {
                            //单位信息修改失败
                            return tokenVo.setIsSuccess(false).setOperation(3);
                        }
                    }else{
                        return tokenVo.setIsSuccess(true);
                    }
                } else {
                    //技术人员删除失败
                    return tokenVo.setIsSuccess(false).setOperation(4);
                }
            } else {
                //技术员不存在
                return tokenVo.setIsSuccess(false).setOperation(1);
            }
            /*//查看审核是否通过
            Audit audit = auditService.queryAuditByUserId(technicist.getUserId());
            if(audit.getStatus() == 1){
                //通过
            }*/
        }
        return tokenVo.setIsSuccess(false);
    }
}
