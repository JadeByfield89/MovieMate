package com.aquarius.moviemate.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.aquarius.moviemate.model.MovieReview;
import com.squareup.picasso.Picasso;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieReviewItemViewModel {

    private MovieReview mReview;
    private Context mContext;

    public MovieReviewItemViewModel(MovieReview review, Context context){

        this.mReview = review;
        this.mContext = context;

    }

    public String getMovieTitle(){

        return "Title: " + mReview.getDisplayTitle();
    }

    public String getRating(){

        return  "Rating: " + mReview.getRating();
    }

    public String getOpeningDate(){
        return "Premiere: " + mReview.getOpeningDate();
    }

    public void setMovieReview(MovieReview review){
        this.mReview = review;
    }

    public String getMovieImageUrl(){
        return this.mReview.getMultimedia().getSrc();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Picasso.with(imageView.getContext()).load(url).fit().into(imageView);


    }
}
