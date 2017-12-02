package com.boombone7.orange.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Ting
 * @date 2017/12/2.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long userId = 0;
    private String name = null;
    private String avatar = null;
    private String gendar = null;
    private String address = null;
    @Generated(hash = 1947322722)
    public UserProfile(long userId, String name, String avatar, String gendar,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
        this.gendar = gendar;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getGendar() {
        return this.gendar;
    }
    public void setGendar(String gendar) {
        this.gendar = gendar;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
