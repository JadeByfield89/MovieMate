package com.aquarius.moviemate.application;

import android.app.Application;
import android.content.Context;

import com.aquarius.moviemate.data.MovieFactory;
import com.aquarius.moviemate.data.MovieService;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieMateApplication extends Application{

    private MovieService mMovieService;

    private static MovieMateApplication get(Context context){

        return (MovieMateApplication) context.getApplicationContext();
    }

    public static MovieMateApplication create(Context context){
        return MovieMateApplication.get(context);
    }

    public MovieService getMovieService(){

        if(mMovieService == null){

            mMovieService = MovieFactory.create();
        }

        return mMovieService;
    }








}
