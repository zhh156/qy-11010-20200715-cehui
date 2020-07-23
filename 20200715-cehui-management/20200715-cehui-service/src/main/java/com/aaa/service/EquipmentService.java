package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.EquipmentMapper;
import com.aaa.model.Equipment;
import com.aaa.utils.IDUtils;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 11:18
 * @Description
 *      仪器设备
 **/
@Service
public class EquipmentService extends BaseService<Equipment> {
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 11:10
     * @description:
     *      查询某个单位的仪器设备信息
     * @param userId 单位中的userId
     * @param pageNum 当前页数
     * @param pageSize  每页的条数
     * @return com.github.pagehelper.PageInfo
     **/
    public PageInfo queryEquipmentByUserId(Long userId, Integer pageNum, Integer pageSize){
        //1.判断前端的数据是否传过来
        if(userId != null && pageNum != null && pageSize != null){
            //传过来了
            PageHelper.startPage(pageNum,pageSize);
            //2.通过userId查询单位中的仪器设备
            List<Map> maps = equipmentMapper.queryEquipmentByUserId(userId);
            //3.判断查询是否成功
            if(maps != null){
                //成功
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 20:37
     * @description:
     *      添加仪器设备信息
     * @param equipment
	 * @param resourceService
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo insertEquipmentByUserId(Equipment equipment, List<MultipartFile> file, ResourceService resourceService){
        TokenVo tokenVo = new TokenVo();
        //1.判断前端的数据是否传过来
        if(equipment.getUserId() != null){
            //传过来了
            //2.指定id
            Long id = Long.parseLong(IDUtils.getTimeId());
            //2.1.判断数据库中是否已经存在将要设置的id(通过循环，查询出来的为空时跳出循环)
            Equipment equipment1 = null;
            do{
                equipment1 = equipmentMapper.selectByPrimaryKey(id);
                id = Long.parseLong(IDUtils.getTimeId());
            }while(equipment1 != null);
            equipment.setId(id).setCreateTime(new Date());
            //3.增加操作
            int insert = equipmentMapper.insert(equipment);
            //4.判断增加操作是否成功
            if(insert > 0){
                //成功
                //5.上传文件
                //TODO 对于多个文件的上传存在问题，怎么能够对多个文件进行分类循环上传
                TokenVo tokenVo1 = resourceService.insertResourceById(file.get(0), "所有权证明", id, null);
                TokenVo tokenVo2 = resourceService.insertResourceById(file.get(1), "检定证明", id, null);
                //6.判断上传是否成功
                if(tokenVo1.getIsSuccess() && tokenVo2.getIsSuccess()){
                    return tokenVo.setIsSuccess(true);
                }else {
                    //添加资源失败
                    return tokenVo.setIsSuccess(false).setOperation(3);
                }
            }else{
                //仪器添加失败
                return tokenVo.setIsSuccess(false).setOperation(4);
            }
        }
        return tokenVo.setIsSuccess(false);
    }
}
