package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_login_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginLog implements Serializable {
    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 登录时间
     */
    @Column(name = "LOGIN_TIME")
    private String loginTime;

    /**
     * 登录地点
     */
    @Column(name = "LOCATION")
    private String location;

    /**
     * IP地址
     */
    @Column(name = "IP")
    private String ip;

    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @Column(name = "OPERATION_NAME")
    private String operationName;

    /**
     * 获取用户名
     *
     * @return USERNAME - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取登录时间
     *
     * @return LOGIN_TIME - 登录时间
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime == null ? null : loginTime.trim();
    }

    /**
     * 获取登录地点
     *
     * @return LOCATION - 登录地点
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置登录地点
     *
     * @param location 登录地点
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 获取IP地址
     *
     * @return IP - IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return OPERATION_TYPE
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * @return OPERATION_NAME
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @param operationName
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }
}