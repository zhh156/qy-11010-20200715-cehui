package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Equipment;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * @param files1 所有权证明文件
     * @param files2 检定证明文件
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertEquipmentByUserId")
    public ResultData insertEquipmentByUserId(@RequestBody Equipment equipment, @RequestParam("files1") MultipartFile[] files1, @RequestParam("files2") MultipartFile[] files2){
        return iProjectService.insertEquipmentByUserId(equipment,files1,files2);
    }
}
