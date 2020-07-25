package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/21 21:01
 * @Description
 **/
@RestController
public class EquipmentController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 21:00
     * @description:
     *      添加设备信息
     * @param equipment
     * @param file
     * @return com.aaa.base.ResultData
     **/
    //TODO 这一块参数有问题
   /* @PostMapping("/insertEquipmentByUserId")
    public ResultData insertEquipmentByUserId(@RequestBody Equipment equipment, @RequestBody List<MultipartFile> file){
        return iProjectService.insertEquipmentByUserId(equipment,file);
    }*/
}
