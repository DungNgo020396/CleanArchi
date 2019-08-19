package com.example.cleanarchtest.entities;

import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("id")
    private   int userId;
    @SerializedName("full_name")
    private  String fullname;
    @SerializedName("followers")
    private  String followers;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

}
