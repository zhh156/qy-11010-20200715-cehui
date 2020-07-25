package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingProject;
import com.aaa.service.MappingProjectService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     *@Author ymq
     *@Date 2020/7/20
     * 项目汇交-根据项目类型查询
     *  条件查询 根据项目类型 projectType，查询所有的 项目汇交信息，进行分页
     *      项目类型分为：基础测绘，专业测绘
     **/
    @GetMapping("/selectAllProjectResultByType")
    public ResultData selectAllProjectResultByType(@RequestParam("projectType") String projectType,
                                                                 @RequestParam("pageNo") Integer pageNo,
                                                                 @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectResultByType 方法，得到查询结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectResultByType(projectType, pageNo, pageSize);

        // 判断 结果是否为空
        if (null != projectPageInfo){
            // 说明结果不为空，查询成功返回结果
            return querySuccess(projectPageInfo);
        }else {
            // 查询失败，返回null
            return queryFailed();
        }
    }



    /**
     *@Author ymq
     *@Date 2020/7/20 18:47
     * 项目汇交
     *  查询所有未提交的汇交成果
     * 汇交成果状态 results_status=3
     **/
    @GetMapping("/selectAllProjectResult")
    public ResultData selectAllProjectResult(@RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("pageSize") Integer pageSize) {
        // 调用 mappingProjectService 中的 selectAllProjectResult 方法，返回结果
        PageInfo<MappingProject> projectPageInfo = mappingProjectService.selectAllProjectResult(pageNo, pageSize);

        // 判断 结果不为空
        if (null != projectPageInfo){
            // 说明结果不为空，查询成果，返回结果
            return querySuccess(projectPageInfo);
        }else {
            // 查询失败，返回null
            return queryFailed();
        }
    }



    /**
     *@Author ymq
     *@Date 2020/7/20 19:29
     * 项目汇交- 操作 修改项目汇交状态
     **/
    @PostMapping("/updateProjectResultStatusById")
    public ResultData updateProjectResultStatusById(@RequestParam("id") Long id) {
        // 调用 mappingProjectService 中的 updateProjectResultStatusById 方法，返回受影响行数
        Boolean aBoolean = mappingProjectService.updateProjectResultStatusById(id);
        return updateSuccess(aBoolean);
    }
}
