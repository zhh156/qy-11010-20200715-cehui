package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Equipment;
import com.aaa.service.EquipmentService;
import com.aaa.service.ResourceService;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @Autowired
    private ResourceService resourceService;
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

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 21:00
     * @description:
     *      添加设备信息
     * @param equipment
	 * @param file
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/insertEquipmentByUserId")
    public ResultData insertEquipmentByUserId(@RequestBody Equipment equipment,@RequestBody List<MultipartFile> file){
        TokenVo tokenVo = equipmentService.insertEquipmentByUserId(equipment, file, resourceService);
        if(tokenVo.getIsSuccess()){
            return addSuccess(tokenVo);
        }
        if(tokenVo.getOperation() == 3){
            return addFailed("资源添加失败");
        }
        if(tokenVo.getOperation() ==  4){
            return addFailed("设备添加失败");
        }
        return addFailed("系统异常");
    }
}
