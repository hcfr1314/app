package com.hhgs.app.common;

public enum CustomCode {

    BAD_CRENTIAL("用户名或密码不正确",1000),LOCKED("账户被锁",1001),CREDENTIALS_EXPIRED("密码过期",1003),
    ACCOUNT_EXPIRED("账户过期",1004),DISABLED("账户被禁用",1005);


    private String desc;
    private int code;

    CustomCode(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
