package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.SpecialPost;
import com.aaa.service.SpecialPostService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:47
 * @Description
 *      特殊人员
 **/
@RestController
public class SpecialPostController extends CommonController<SpecialPost> {
    @Autowired
    private SpecialPostService specialPostService;
    @Override
    public BaseService<SpecialPost> getBaseService() {
        return specialPostService;
    }


    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:35
     * @description:
     *      查询某个单位的特殊人员信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/querySpecialPostByUserId")
    public ResultData querySpecialPostByUserId(@RequestParam("userId")Object userId, @RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = specialPostService.querySpecialPostByUserId(Long.parseLong(userId.toString()), pageNum, pageSize);
        if(pageInfo != null ){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }
}
