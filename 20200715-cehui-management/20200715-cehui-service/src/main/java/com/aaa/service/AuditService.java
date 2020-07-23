package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.AuditMapper;
import com.aaa.model.Audit;
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
 * @Date Create in 2020/7/16 10:05
 * @Description
 *      单位审核
 **/
@Service
public class AuditService extends BaseService<Audit> {
    @Autowired
    private AuditMapper auditMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 20:22
     * @description:
     *      查询某一测绘单位的所有审核信息
     * @param userId
	 * @param pageNum
	 * @param pageSize
     * @return com.github.pagehelper.PageInfo<java.util.Map>
     **/
    public PageInfo<Map> queryAllAuditByUserId(Long userId,Integer pageNum,Integer pageSize){
        //1.判断前端的数据是否传到这里
        if(pageNum != null && pageSize != null){
            //传过来了
            //2.进行查询操作
            PageHelper.startPage(pageNum,pageSize);
            List<Map> maps = auditMapper.queryAllAuditByUserId(userId);
            PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
            //3.判断查询是否成功
            if(pageInfo.getSize() > 0){
                return pageInfo;
            }
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 20:03
     * @description:
     *      更新审核表中的记录
     * @param userId 从单位表中的userId获取到
     * @param status 审核的状态（0-通过；1-拒绝）
	 * @param memo 备注
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo updateAuditByUserId(Long userId,Integer status,String memo){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来了
        if(null != userId){
            //传过来了
            Audit audit = new Audit();
            //2.将数据放入实体类中
            audit.setStatus(status).setMemo(memo).setAuditTime(new Date()).setUserId(userId);
            //3.进行更新操作
            Integer integer = auditMapper.updateAuditByUserId(audit);
            //4.判断更新是否成功
            if(integer > 0){
                //成功
                return  tokenVo.setIsSuccess(true).setData(audit);
            }
        }
        return tokenVo.setIsSuccess(false);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 15:13
     * @description:
     *      通过userId查询审核记录表中的某一条数据
     * @param userId
     * @return com.aaa.model.Audit
     **/
    public Audit queryAuditByUserId(Long userId){
        //1.判断前端数据是否传入过来
        if(userId != null){
            //2.判断查询是否成功
            Audit audit = auditMapper.queryAuditByUserId(userId);
            if(audit != null){
                //成功
                return audit;
            }
        }
        return null;
    }
}
