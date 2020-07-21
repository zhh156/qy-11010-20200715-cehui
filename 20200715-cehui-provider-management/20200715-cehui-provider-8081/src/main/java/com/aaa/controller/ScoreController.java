package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Score;
import com.aaa.service.ScoreService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 16:18
 * @Description
 *      分值记录
 **/
@RestController
public class ScoreController extends CommonController<Score> {
    @Autowired
    private ScoreService scoreService;
    @Override
    public BaseService<Score> getBaseService() {
        return scoreService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 16:31
     * @description:
     *      通过测绘单位的主键id查询分值记录信息（包括附件）
     * @param id
	 * @param pageNum
	 * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryAllScoreById")
    public ResultData queryAllScoreById(@RequestParam("id")Object id,@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){
        PageInfo<Map> pageInfo = scoreService.queryAllScoreById(id, pageNum, pageSize);
        if(pageInfo != null){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }
}
