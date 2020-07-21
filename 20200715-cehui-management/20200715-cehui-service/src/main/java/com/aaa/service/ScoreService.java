package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.ScoreMapper;
import com.aaa.model.Score;
import com.aaa.utils.IDUtils;
import com.aaa.vo.TokenVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 16:00
 * @Description
 *      分值表
 **/
@Service
public class ScoreService extends BaseService<Score> {
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 16:28
     * @description:
     *      根据测绘单位的主键id查询分值变化记录信息（包括附件）
     * @param id  查询的条件
	 * @param pageNum 当前页数
	 * @param pageSize  每页的条数
     * @return com.github.pagehelper.PageInfo<java.util.Map>
     **/
    public PageInfo<Map> queryAllScoreById(Object id, Integer pageNum, Integer pageSize){
        //1.判断前端的数据是否传到这里
        if(id != null && !"".equals(id) && pageNum != null && pageSize != null){
            //传到这里了
            //2.进行查询
            PageHelper.startPage(pageNum,pageSize);
            List<Map> maps = scoreMapper.queryAllScoreById(Long.parseLong(id+""));
            //3.判断查询是否成功
            if(null != maps){
                //成功
                PageInfo<Map> pageInfo = new PageInfo<Map>(maps);
                return pageInfo;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/20 8:05
     * @description:
     *      添加分值变化记录
     * @param scoreChange 增加/减少的分值
	 * @param score 当前分值
	 * @param unitId 关联单位编号
	 * @param operationType 增加(1) 减少（2）
	 * @param reason 增加/减少的原因
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo insertScoreById(Integer scoreChange,Integer score,Long unitId, String operationType, String reason){
        TokenVo tokenVo = new TokenVo();
        //1.判断数据是否从前端传过来了,从单位中是否查出了当前分值
        if(operationType != null && score != null && scoreChange != null && unitId != null){
            //成功
            //2.将数据封装到Score实体类中
            //2.1.指定id
            Long id = Long.parseLong(IDUtils.getTimeId());
            //2.2.判断数据库中是否已经存在将要设置的id(通过循环，查询出来的为空时跳出循环)
            Score score1 = null;
            do{
                score1 = scoreMapper.selectByPrimaryKey(id);
                id = Long.parseLong(IDUtils.getTimeId());
            }while(score1 != null);
            //2.3.将id封装到score实体类中
            Score score2 = new Score();
            score2.setId(id);
            //2.4.将变化的分值封装到实体类中
            if("增加".equals(operationType)){
                //增加
                score2.setScorePlus(scoreChange);
            }else if("减少".equals(operationType)){
                //减少
                score2.setScoreSubtract(scoreChange);
            }
            score2.setScore(score).setUnitId(unitId).setReason(reason).setCreateTime(new Date());
            //3.进行增加分值记录的操作
            int insert = scoreMapper.insert(score2);
            //4.判断操作是否成功
            if(insert > 0){
                //成功
                return tokenVo.setIsSuccess(true).setData(insert);
            }
        }
        return tokenVo.setIsSuccess(false);
    }
}
