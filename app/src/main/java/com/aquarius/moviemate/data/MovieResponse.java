package com.aquarius.moviemate.data;

import com.aquarius.moviemate.model.MovieReview;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieResponse {

    @SerializedName("results")
    List<MovieReview> mMovieReviewList;

    public List<MovieReview> getReviews(){

        return mMovieReviewList;
    }

    public void setMovieReviewList(List<MovieReview> list){
        this.mMovieReviewList = list;
    }
}
