package com.example.projectfinal.model;

import java.io.Serializable;
import java.util.Date;

public class ImageUser implements Serializable {
    private int id;
    private String username;
    private String img;
    private String date;
    private String name;
    public ImageUser(){

    }
    public ImageUser(int id,String username,String password,String img){

        this.id = id;
        this.username = username;
        this.img = img;
    }
    public ImageUser(String username,String password,String img){

        this.username = username;
        this.img = img;
    }





    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public String getImg() {
        return img;
    }

    public void setDate(String date) {
        this.date = date;
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
}
