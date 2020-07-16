package com.aaa.status;

public enum OperationStatus {
    /**
     * 进行CRUD操作时可能出现的情况
     */
    INSERT_SUCCESS("10010","插入成功"),
    INSERT_FAILED("10011","插入失败"),
    QUERY_SUCCESS("10020","查询成功"),
    QUERY_FAILED("10021","查询失败"),
    UPDATE_SUCCESS("10030","更新成功"),
    UPDATE_FAILED("10031","更新失败"),
    DELETE_SUCCESS("10040","删除成功"),
    DELETE_FAILED("10041","删除失败");

    OperationStatus(String code,String msg){
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
