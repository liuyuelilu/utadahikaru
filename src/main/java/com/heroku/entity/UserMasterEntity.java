package com.heroku.entity;

import java.sql.Timestamp;

public class UserMasterEntity {
    private String username;
    private String name;
    private String passwd;
    private Timestamp tick;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Timestamp getTick() {
        return tick;
    }

    public void setTick(Timestamp tick) {
        this.tick = tick;
    }
}
