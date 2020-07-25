package com.aaa.mapper;

import com.aaa.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {

    //  根据id查询
    List<User> selectUserByField(Map map);
}