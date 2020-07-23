package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Technicist;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/21 19:47
 * @Description
 **/
@RestController
public class TechnicistController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 19:45
     * @description:
     *      添加技术人员信息
     * @param technicist
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertTechnicistByUserId")
    public ResultData insertTechnicistByUserId(@RequestBody Technicist technicist){
        return iProjectService.insertTechnicistByUserId(technicist);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:05
     * @description:
     *      修改技术人员的信息
     * @param technicist
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist){
        return iProjectService.updateTechnicist(technicist);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:26
     * @description:
     *      删除技术人员的信息
     * @param technicist
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteTechnicist")
    public ResultData deleteTechnicist(@RequestBody Technicist technicist){
        return iProjectService.deleteTechnicist(technicist);
    }
}
