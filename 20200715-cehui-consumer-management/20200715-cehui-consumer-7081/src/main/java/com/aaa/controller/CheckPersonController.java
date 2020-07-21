package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.CheckPerson;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/18 16:23
 * @Description
 **/
@RestController
public class CheckPersonController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 16:22
     * @description:
     *      查询一定百分比的检查人员的信息
     * @param pageNum 当前页数
     * @param pageSize 每页的条数
     * @param percentage 查询的百分比数
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryCheckPersonByPercentage")
    public ResultData queryCheckPersonByPercentage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                   @RequestParam(name="pageSize",defaultValue =  "10")Integer pageSize, @RequestParam("percentage") Double percentage
                                                   ){
        return iProjectService.queryCheckPersonByPercentage(pageNum,pageSize,percentage);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 17:04
     * @description:
     *      插入抽查人员
     * @param checkPerson
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertCheckPerson")
    public ResultData insertCheckPerson(@RequestBody CheckPerson checkPerson){
        System.out.println(checkPerson.getName());
        return iProjectService.insertCheckPerson(checkPerson);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 17:16
     * @description:
     *      删除抽查人员
     * @param checkPerson
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/deleteCheckPerson")
    public ResultData deleteCheckPerson(@RequestBody CheckPerson checkPerson){
        return iProjectService.deleteCheckPerson(checkPerson);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/18 17:42
     * @description:
     *      修改抽查人员
     * @param checkPerson
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/updateCheckPerson")
    public ResultData updateCheckPerson(@RequestBody CheckPerson checkPerson){
        return iProjectService.updateCheckPerson(checkPerson);
    }


}
