package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.CheckPerson;
import com.aaa.service.CheckPersonService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.aaa.status.LoginStatus.USER_EXIST;
import static com.aaa.status.LoginStatus.USER_NOT_EXIST;
import static com.aaa.status.OperationStatus.OPERATION_WRONG;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/18 16:19
 * @Description
 *      检查人员
 **/
@RestController
public class CheckPersonController extends CommonController<CheckPerson> {
    @Autowired
    private CheckPersonService checkPersonService;
    @Override
    public BaseService<CheckPerson> getBaseService() {
        return checkPersonService;
    }

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
    public ResultData queryCheckPersonByPercentage(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("percentage") Double percentage){
        PageInfo pageInfo = checkPersonService.queryCheckPersonByPercentage(pageNum, pageSize, percentage);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
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
        TokenVo tokenVo = checkPersonService.insertCheckPerson(checkPerson);
        if(tokenVo.getIsSuccess()){
            return addSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 2){
            return addFailed(USER_EXIST.getMsg());
        }
        return addFailed(OPERATION_WRONG.getMsg());
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
        TokenVo tokenVo = checkPersonService.deleteCheckPerson(checkPerson);
        if(tokenVo.getIsSuccess()){
            return deleteSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 1){
            return deleteFailed(USER_NOT_EXIST.getMsg());
        }
        return deleteFailed(OPERATION_WRONG.getMsg());
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
        TokenVo tokenVo = checkPersonService.updateCheckPerson(checkPerson);
        if(tokenVo.getIsSuccess()){
            return updateSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 1){
            return updateFailed(USER_NOT_EXIST.getMsg());
        }
        return updateFailed(OPERATION_WRONG.getMsg());
    }
}
