package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Technicist;
import com.aaa.service.TechnicistService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:44
 * @Description
 *      技术人员
 **/
@RestController
public class TechnicistController extends CommonController<Technicist> {
    @Autowired
    private TechnicistService technicistService;
    @Override
    public BaseService<Technicist> getBaseService() {
        return technicistService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:13
     * @description:
     *      查询某个单位的技术人员信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryTechnicistByUserId")
    public ResultData queryTechnicistByUserId(@RequestParam("userId")Object userId, @RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = technicistService.queryTechnicistByUserId(Long.parseLong(userId.toString()), pageNum, pageSize);
        if(pageInfo != null ){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }
}
