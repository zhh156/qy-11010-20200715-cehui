package com.aaa.base;

import static com.aaa.status.LoginStatus.*;
import static com.aaa.status.OperationStatus.*;

/**
* @author zhh
* @date 2020/7/8 15:10
* 同一controller
 *  也就是说：所有的controller都需要继承这个controller，进行统一返回结果
*/
public class BaseController {
    /**
     * 登录成功，使用系统消息
     * @return
     */
    protected ResultData loginSuccess(){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 登录成功，使用自定义消息
     * @param msg
     * @return
     */
    protected ResultData loginSuccess(String msg){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 登录成功，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData loginSuccessData(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 登录成功，使用自定义消息，返回数据
     * @param data
     * @param msg
     * @return
     */
    protected ResultData loginSuccess(Object data, String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * 登录失败，使用系统消息
     * @return
     */
    protected ResultData loginFailed(){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }

    /**
     * 登录失败，使用自定义消息
     * @param msg
     * @return
     */
    protected ResultData loginFailed(String msg){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * 登录失败，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData loginFailed(Object data){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 登录失败，使用自定义的消息，返回数据
     * @param data
     * @param msg
     * @return
     */
    protected ResultData loginFailed(Object data, String msg){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * 登录失败，用户已被锁定
     * @return
     */
    protected ResultData loginUserLocked(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(USER_LOCKED.getCode());
        resultData.setMsg(USER_LOCKED.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 登录失败，用户不存在
     * @return
     */
    protected ResultData loginUserNoExist(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(USER_NOT_EXIST.getCode());
        resultData.setMsg(USER_NOT_EXIST.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 登录失败，密码错误
     * @param data
     * @return
     */
    protected ResultData loginPasswordWrong(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(PASSWORD_WR0NG.getCode());
        resultData.setMsg(PASSWORD_WR0NG.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 登录失败，系统错误
     * @param data
     * @return
     */
    protected ResultData systemWrong(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(SYSTEM_WRONG.getCode());
        resultData.setMsg(SYSTEM_WRONG.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 退出失败
     * @param data
     * @return
     */
    protected ResultData loginOut(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGOUT_WRONG.getCode());
        resultData.setMsg(LOGOUT_WRONG.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 注册失败，用户已存在
     * @param data
     * @return
     */
    protected ResultData loginUpUserExist(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(USER_EXIST.getCode());
        resultData.setMsg(USER_EXIST.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 新增成功，使用系统消息
     * @return
     */
    protected ResultData addSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMsg(INSERT_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 新增成功，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData addSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_SUCCESS.getCode());
        resultData.setMsg(INSERT_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 新增失败，使用系统消息
     * @return
     */
    protected ResultData addFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        return resultData;
    }

    /**
     * 新增失败，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData addFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 新增失败，使用系统消息，返回数据,添加失败信息细节
     * @param data
     * @return
     */
    protected ResultData addFailed(Object data, String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(INSERT_FAILED.getMsg());
        resultData.setData(data);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * 新增失败，使用自定消息
     * @param msg
     * @return
     */
    protected ResultData addFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * 新增失败，使用自定消息，返回数据
     * @param msg
     * @return
     */
    protected ResultData addFailed(String msg, Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(INSERT_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * 查询成功，使用系统消息
     * @return
     */
    protected ResultData querySuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_SUCCESS.getCode());
        resultData.setMsg(QUERY_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 查询成功，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData querySuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_SUCCESS.getCode());
        resultData.setMsg(QUERY_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 查询失败，使用系统消息
     * @return
     */
    protected ResultData queryFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_FAILED.getCode());
        resultData.setMsg(QUERY_FAILED.getMsg());
        return resultData;
    }

    /**
     * 查询失败，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData queryFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_FAILED.getCode());
        resultData.setMsg(QUERY_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 查询失败，使用系统消息，返回数据,添加失败信息细节
     * @param data
     * @return
     */
    protected ResultData queryFailed(Object data, String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_FAILED.getCode());
        resultData.setMsg(QUERY_FAILED.getMsg());
        resultData.setData(data);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * 查询失败，使用自定消息
     * @param msg
     * @return
     */
    protected ResultData queryFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * 查询失败，使用自定消息
     * @param msg
     * @return
     */
    protected ResultData queryFailed(String msg, Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(QUERY_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * 更新成功，使用系统消息
     * @return
     */
    protected ResultData updateSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(UPDATE_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 更新成功，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData updateSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_SUCCESS.getCode());
        resultData.setMsg(UPDATE_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 更新失败，使用系统消息
     * @return
     */
    protected ResultData updateFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        return resultData;
    }

    /**
     * 更新失败，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData updateFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 更新失败，使用系统消息，返回数据,添加失败信息细节
     * @param data
     * @return
     */
    protected ResultData updateFailed(Object data, String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(UPDATE_FAILED.getMsg());
        resultData.setData(data);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * 更新失败，使用自定消息
     * @param msg
     * @return
     */
    protected ResultData updateFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * 更新失败，使用自定消息，返回数据
     * @param msg
     * @return
     */
    protected ResultData updateFailed(String msg, Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(UPDATE_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
     * 删除成功，使用系统消息
     * @return
     */
    protected ResultData deleteSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(DELETE_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * 删除成功，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData deleteSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_SUCCESS.getCode());
        resultData.setMsg(DELETE_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 删除失败，使用系统消息
     * @return
     */
    protected ResultData deleteFailed(){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        return resultData;
    }

    /**
     * 删除失败，使用系统消息，返回数据
     * @param data
     * @return
     */
    protected ResultData deleteFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
     * 删除失败，使用系统消息，返回数据,添加失败信息细节
     * @param data
     * @return
     */
    protected ResultData deleteFailed(Object data, String detail){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(DELETE_FAILED.getMsg());
        resultData.setData(data);
        resultData.setDetail(detail);
        return resultData;
    }
    /**
     * 删除失败，使用自定消息
     * @param msg
     * @return
     */
    protected ResultData deleteFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
     * 删除失败，使用自定消息，返回数据
     * @param msg
     * @return
     */
    protected ResultData deleteFailed(String msg, Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(DELETE_FAILED.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
}
