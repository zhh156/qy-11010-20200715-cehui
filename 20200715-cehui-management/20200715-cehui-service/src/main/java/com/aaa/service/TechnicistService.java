package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.TechnicistMapper;
import com.aaa.model.Technicist;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
