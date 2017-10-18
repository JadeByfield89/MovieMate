package com.aquarius.moviemate.data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieFactory {

    public static final String BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/";


    public static MovieService create() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(MovieService.class);

    }
}
