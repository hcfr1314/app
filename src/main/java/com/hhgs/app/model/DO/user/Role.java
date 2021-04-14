package com.hhgs.app.model.DO.user;

import java.util.List;

public class Role {

    /**
     * 主键
     */
    private int id;

    /**
     * 角色中文名称
     */
    private String name;

    /**
     * 角色英文名称
     */
    private String enName;


    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enName='" + enName + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
