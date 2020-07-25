package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Dept;
import com.aaa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
    *@Author ymq
    *@Date 2020/7/17 9:01
**/
@RestController
public class DeptController extends CommonController<Dept> {

    @Autowired
    private DeptService deptService;
    @Override
    public BaseService<Dept> getBaseService() {
        return deptService;
    }

    /**
        *@Author ymq
        *@Date 2020/7/17 9:03
     * 查询出一级部门
    **/
    @GetMapping("firstDept")
    public ResultData firstDept(){
        List<Dept> depts =deptService.firstDept();
        if(depts !=null && depts.size()>0){
         return super .querySuccess(depts);
        }
        return super.queryFailed();
    }

        /**
            *@Author ymq
            *@Date 2020/7/17 9:17
         *根据一级部门的ID查询出对应的二级部门
         *
        **/
        @GetMapping("/secondDept")
        public ResultData secondDept(@RequestParam("id") int id){
            List<Dept> depts =deptService.secondDept(id);
            if(depts !=null && depts.size()>0){
                return  super.querySuccess(depts);
            }
            return  super.queryFailed();
            }

        /**
        * @Author ymq
        * @Date 2020/7/17 9:26
        * 新增部门
        **/
        @PostMapping("/addDept")
        public ResultData addDept(@RequestBody Dept dept){
                System.out.println(dept);
            return super.addT(dept);
        }


        /**
            *@Author ymq
            *@Date 2020/7/17 10:24
         * 修改部门信息
        **/
        @PostMapping("/updateDept")
        public ResultData updateDept(@RequestBody Dept dept)
        {
            return super.updateT(dept);
        }
        /**
            *@Author ymq
            *@Date 2020/7/17 10:30
         * 删除部部门信息
        **/
        @PostMapping("deleteDept")
        public ResultData deleteDept(@RequestBody Dept dept)
        {
            return super.deleteT(dept);
        }
            /**
                *@Author ymq
                *@Date 2020/7/18 16:38
             * 批量删除
            **/
            @PostMapping("/delDeptAlls")
            public ResultData delDeptAlls(@RequestParam("ids") List<Long> ids){
                Integer integer = deptService.delDeptAlls(ids);
                if (integer > 0) {
                    return super.deleteSuccess();
                } else {
                    return super.deleteFailed();
                }
            }

}
