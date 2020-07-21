package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingProject;
import com.aaa.service.MappingProjectService;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 12:00
 * @Description
 *      测绘项目
 **/
@RestController
public class MappingProjectController extends CommonController<MappingProject> {
    @Autowired
    private MappingProjectService mappingProjectService;
    @Override
    public BaseService<MappingProject> getBaseService() {
        return mappingProjectService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:10
     * @description:
     *      查询某个单位的项目
     * @param userId 单位中的userId
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @param pageSize  项目的名称（模糊查询）
     * @return com.github.pagehelper.PageInfo
     **/
    @GetMapping("/queryMappingProjectByUserId")
    public ResultData queryMappingProjectByUserId(@RequestParam("userId") Long userId, @RequestParam("pageNum") Integer pageNum,
                                                  @RequestParam("pageSize")Integer pageSize, @RequestParam("projectName") String projectName){
        TokenVo tokenVo = mappingProjectService.queryMappingProjectByUserId(userId, pageNum, pageSize, projectName);
        if(tokenVo.getIsSuccess()){
            return querySuccess(tokenVo);
        }
        return queryFailed();
    }
}
