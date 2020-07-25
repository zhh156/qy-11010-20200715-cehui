package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.DeptMapper;
import com.aaa.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.List;

@Service
public class DeptService extends BaseService<Dept> {

    @Autowired
    private DeptMapper deptMapper;

    /**
        *@Author ymq
        *@Date 2020/7/17 8:56
     * 查询出一级部门
    **/
    public List<Dept> firstDept() {

        return deptMapper.firstDept();
    }




    /**
        *@Author ymq
        *@Date 2020/7/17 8:58
     * 根据一级部门的ID查询出对应的二级部门
    **/

    public List<Dept> secondDept(int id) {
            return deptMapper.secondDept(id);
    }



    /**
        *@Author ymq
        *@Date 2020/7/18 16:43
     * 批量删除部门
    **/
    public Integer delDeptAlls(List<Long> ids){
        Example example = Example.builder(Dept.class).where(Sqls.custom().andIn("deptId",ids)).build();
        return deptMapper.deleteByExample(example);
    }

}
