package com.example.chatsapppart1;

public class User {

    private String name , profileImg ,  uid , phoneNumber;

    public User() {
    }

    public User(String name, String profileImg, String uid, String phoneNumber) {
        this.name = name;
        this.profileImg = profileImg;
        this.uid = uid;
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getUid() {
        return uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
