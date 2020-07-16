package com.aaa.status;
/**
* @author zhh
* @date 2020/7/8 15:29
* 用户登录状态
*/
public enum LoginStatus {
    /**
     * 登录可能出现的情况
     */
    LOGIN_SUCCESS("20001","登录成功"),
    LOGIN_FAILED("20002","登录失败"),
    USER_NOT_EXIST("20003","用户不存在"),
    PASSWORD_WR0NG("20004","密码错误"),
    USER_LOCKED("20005","用户被锁定"),
    SYSTEM_WRONG("20006","系统错误"),
    /**
     * 注册可能出现的情况
     */
    USER_EXIST("10001","用户已存在"),
    /**
     * 退出程序时，可能会出现的问题
     */
    LOGOUT_WRONG("10050","用户退出异常");

    /**
     * 构造方法
     * @param code
     * @param msg
     */
    LoginStatus(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
