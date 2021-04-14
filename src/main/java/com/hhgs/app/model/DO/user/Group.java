package com.hhgs.app.model.DO.user;

import java.util.List;

public class Group {

    private int groupid;

    private String groupName;

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupid=" + groupid +
                ", groupName='" + groupName + '\'' +
                ", users=" + users +
                '}';
    }
}
