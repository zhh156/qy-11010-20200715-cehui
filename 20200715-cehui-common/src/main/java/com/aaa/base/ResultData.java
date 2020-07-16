package com.aaa.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zhh
* @date 2020/7/8 14:59
* 规定返回结果的格式
*/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {
    /**
     * 状态码
     */
    private String code;
    /**
     * 操作结果信息
     */
    private String msg;
    /**
     * 操作结果的细节/补充信息
     */
    private String detail;
    /**
     * 返回的结果，使用泛型（不指定返回的数据类型，可以为任意类型。此处的T可以使用Object来代替）
     */
    private T data;
}
