package com.aquarius.moviemate.viewmodel;

import com.aquarius.moviemate.model.MovieReview;

import java.util.List;

/**
 * Created by byfieldj on 10/17/17.
 */

public interface IMovieReviewsViewModel {

    void update(List<MovieReview> reviews);
}
