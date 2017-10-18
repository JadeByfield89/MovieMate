package com.aquarius.moviemate.data;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by byfieldj on 10/17/17.
 */

public interface MovieService {

    @GET("all.json?api-key=71728d7ddc95427eb33d310286b68572")
    Observable<MovieResponse> fetchMovies();
}
