package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Resource;
import com.aaa.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:37
 * @Description
 *      附件表
 **/
@RestController
public class ResourceController extends CommonController<Resource> {
    @Autowired
    private ResourceService resourceService;

    @Override
    public BaseService<Resource> getBaseService() {
        return resourceService;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 14:29
     * @description:
     *      查询某个测绘单位的全部资源信息
     * @param id
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryResourceById")
    public ResultData queryResourceById(@RequestParam("id")Object id){
        List<Resource> resources = resourceService.queryResourceById(Long.parseLong(id.toString()));
        if(resources != null ){
            return querySuccess(resources);
        }
        return queryFailed();
    }
}
