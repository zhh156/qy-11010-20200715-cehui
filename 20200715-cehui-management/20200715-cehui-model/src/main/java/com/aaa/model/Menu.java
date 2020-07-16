package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Menu implements Serializable {
    /**
     * 菜单/按钮ID
     */
    @Id
    @Column(name = "MENU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @Column(name = "PARENT_ID")
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 对应路由path
     */
    @Column(name = "PATH")
    private String path;

    /**
     * 对应路由组件component
     */
    @Column(name = "COMPONENT")
    private String component;

    /**
     * 权限标识
     */
    @Column(name = "PERMS")
    private String perms;

    /**
     * 图标
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 排序
     */
    @Column(name = "ORDER_NUM")
    private Double orderNum;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    /**
     * 获取菜单/按钮ID
     *
     * @return MENU_ID - 菜单/按钮ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单/按钮ID
     *
     * @param menuId 菜单/按钮ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取上级菜单ID
     *
     * @return PARENT_ID - 上级菜单ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置上级菜单ID
     *
     * @param parentId 上级菜单ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单/按钮名称
     *
     * @return MENU_NAME - 菜单/按钮名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单/按钮名称
     *
     * @param menuName 菜单/按钮名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取对应路由path
     *
     * @return PATH - 对应路由path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置对应路由path
     *
     * @param path 对应路由path
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取对应路由组件component
     *
     * @return COMPONENT - 对应路由组件component
     */
    public String getComponent() {
        return component;
    }

    /**
     * 设置对应路由组件component
     *
     * @param component 对应路由组件component
     */
    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    /**
     * 获取权限标识
     *
     * @return PERMS - 权限标识
     */
    public String getPerms() {
        return perms;
    }

    /**
     * 设置权限标识
     *
     * @param perms 权限标识
     */
    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    /**
     * 获取图标
     *
     * @return ICON - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取类型 0菜单 1按钮
     *
     * @return TYPE - 类型 0菜单 1按钮
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型 0菜单 1按钮
     *
     * @param type 类型 0菜单 1按钮
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取排序
     *
     * @return ORDER_NUM - 排序
     */
    public Double getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Double orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return MODIFY_TIME - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}