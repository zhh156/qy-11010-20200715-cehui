package com.aaa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 15:13
 * @Description
 *      view object：只能在前端能够使用。简称vo
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {

    private String token;
    private Boolean isSuccess;
    /**
     * 1.账号不存在
     * 2.密码错误
     * 3.账号被锁定
     * 4.系统异常
     */
    private Integer type;
    /**
     * 操作的参数
     * 1.用户不存在
     * 2.用户已经存在
     * 3.文件上传失败
     *
     */
    private Integer operation;
    /**
     * 需要传的数据
     */
    private Object Data;
}
