package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Dict;
import com.aaa.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Title DictController
 * @Author tuo
 * @Date 2020/7/16
 **/
@RestController
public class DictController extends CommonController<Dict>{
    @Autowired
    private DictService dictService;

    @Override
    public BaseService<Dict> getBaseService() {
        return dictService;
    }

    /**
     * 字典查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/allDict")
    public ResultData allDict(@RequestParam("pageNo") int pageNo,
                              @RequestParam("pageSize") int pageSize) {
        return super.getListByPage(pageNo, pageSize);
    }
    /**
     *@Author tuo
     *@Date 2020/7/17
     * 根据条件查询字典信息
     **/
    @PostMapping("selectAll")
    public ResultData selectAll(@RequestBody Map hashMap){
        PageInfo pageInfo = dictService.selectAlls(hashMap);
        if ( null != pageInfo && !"".equals(pageInfo)) {
            return super.querySuccess(pageInfo);
        }
        return super.queryFailed();
    }
    /**
     * @author tuo
     * 删除字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/delDict")
    public ResultData delDict(@RequestBody Dict dict) {
        return super.deleteT(dict);
    }

    /**
     * @author tuo
     * 新增字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/addDict")
    public ResultData addDict(@RequestBody Dict dict) {
        return super.addT(dict);
    }

    /**
     * @author tuo
     * 修改字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/updDict")
    public ResultData updDict(@RequestBody Dict dict) {
        return super.updateT(dict);
    }
    /**
     * @author tuo
     * 查询单条字典信息
     * @Date 2020/7/16
     **/
    @PostMapping("/selectDict")
    public ResultData selectDict(@RequestBody Dict dict){
        return super.selectOneT(dict);
    }
}
