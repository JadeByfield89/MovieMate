package com.aquarius.moviemate.view;

import com.aquarius.moviemate.model.MovieReview;

import java.util.List;

/**
 * Created by byfieldj on 10/17/17.
 */

public interface IMovieReviewsListView {

    void displayResults(List<MovieReview> reviews);
}
