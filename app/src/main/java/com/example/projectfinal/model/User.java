package com.example.projectfinal.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username,password,name;
    private String img;

    public User(int id, String username, String password, String img,String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.img = img;
        this.name = name;
    }

    public User(String username, String password, String img,String name) {
        this.username = username;
        this.password = password;
        this.img = img;
        this.name = name;
    }
    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
