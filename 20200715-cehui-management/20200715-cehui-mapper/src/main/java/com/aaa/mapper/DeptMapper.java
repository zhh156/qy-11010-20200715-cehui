package com.aaa.mapper;

import com.aaa.model.Dept;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DeptMapper extends Mapper<Dept> {

    /**
     *@Author ymq
     *@Date a 8:53
     * 根据一级部门的ID查询出对应的二级部门
     **/

    List<Dept> secondDept(int id);

    /**
     *@Author ymq
     *@Date 2020/7/17 8:54
     * 查询出一级部门
     **/
    List<Dept> firstDept();
}