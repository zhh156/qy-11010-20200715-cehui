package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Equipment;
import com.aaa.service.EquipmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:46
 * @Description
 *      仪器设备
 **/
@RestController
public class EquipmentController extends CommonController<Equipment> {
    @Autowired
    private EquipmentService equipmentService;
    @Override
    public BaseService<Equipment> getBaseService() {
        return equipmentService;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:26
     * @description:
     *      查询某个单位的仪器设备信息
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return com.aaa.base.ResultData
     **/
    @GetMapping("/queryEquipmentByUserId")
    public ResultData queryEquipmentByUserId(@RequestParam("userId")Object userId, @RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize") Integer pageSize){
        PageInfo pageInfo = equipmentService.queryEquipmentByUserId(Long.parseLong(userId.toString()), pageNum, pageSize);
        if(pageInfo != null ){
            return querySuccess(pageInfo);
        }
        return queryFailed();
    }
}
