package com.aaa.mapper;

import com.aaa.model.Score;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ScoreMapper extends Mapper<Score> {
    /**
     * 通过测绘单位的编号查询分值变化情况
     * @param id
     * @return
     */
    List<Map> queryAllScoreById(@Param("id") Long id);
}