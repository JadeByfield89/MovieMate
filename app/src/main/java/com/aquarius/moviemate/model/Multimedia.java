package com.aquarius.moviemate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by byfieldj on 10/17/17.
 */

public class Multimedia implements Serializable {


    @SerializedName("type")
    String type;


    @SerializedName("src")
    String src;

    @SerializedName("width")
    String width;

    @SerializedName("height")
    String height;


    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }



}
