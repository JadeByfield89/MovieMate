package com.aquarius.moviemate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieReview implements Serializable{

    @SerializedName("multimedia")
    public Multimedia multimedia;

    @SerializedName("display_title")
    public String displayTitle;

    @SerializedName("mpaa_rating")
    public String rating;

    @SerializedName("opening_date")
    public String openingDate;

    @SerializedName("link")
    public  Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }



    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }




}
