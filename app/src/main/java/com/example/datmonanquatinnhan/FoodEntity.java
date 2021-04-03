package com.example.datmonanquatinnhan;

import java.io.Serializable;

public class FoodEntity implements Serializable {
    private String img;
    private  String info;

    public FoodEntity(String img, String info) {
        this.img = img;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
