package com.aquarius.moviemate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by byfieldj on 10/17/17.
 */

public class Link implements Serializable{

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("type")
    String type;

    @SerializedName("url")
    String url;


}
