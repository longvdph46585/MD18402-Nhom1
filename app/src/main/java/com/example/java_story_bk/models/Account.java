package com.example.java_story_bk.models;

import java.io.Serializable;

public class Account implements Serializable {
    public void set_id(String _id) {
        this._id = _id;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpirit_stone(String spirit_stone) {
        this.spirit_stone = spirit_stone;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public void setDevice_uuid(String device_uuid) {
        this.device_uuid = device_uuid;
    }

    public void setIsBlock(String isBlock) {
        this.isBlock = isBlock;
    }

    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSpirit_stone() {
        return spirit_stone;
    }

    public Account(String _id, String email, String password, String spirit_stone, String expired_date, String device_uuid, String isBlock, String name, String level) {
        this._id = _id;
        this.email = email;
        this.password = password;
        this.spirit_stone = spirit_stone;
        this.expired_date = expired_date;
        this.device_uuid = device_uuid;
        this.isBlock = isBlock;
        this.name = name;
        this.level = level;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getDevice_uuid() {
        return device_uuid;
    }

    public String getIsBlock() {
        return isBlock;
    }

    private String _id, email, password, spirit_stone, expired_date, device_uuid, isBlock, name, level;
}
