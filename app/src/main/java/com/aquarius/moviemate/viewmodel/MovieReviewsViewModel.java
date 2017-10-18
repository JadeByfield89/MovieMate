package com.aquarius.moviemate.viewmodel;


import android.content.Context;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.aquarius.moviemate.application.MovieMateApplication;
import com.aquarius.moviemate.data.MovieResponse;
import com.aquarius.moviemate.data.MovieService;
import com.aquarius.moviemate.model.MovieReview;
import com.aquarius.moviemate.view.IMovieReviewsListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieReviewsViewModel implements IMovieReviewsViewModel {

    public ObservableInt mProgress;
    public ObservableInt mRecycler;

    private List<MovieReview> mReviewsList;

    private IMovieReviewsListView mView;

    private Context mContext;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MovieReviewsViewModel(@NonNull Context context){
        this.mContext = context;
        this.mReviewsList = new ArrayList<>();

        mProgress = new ObservableInt(View.VISIBLE);
        mRecycler = new ObservableInt(View.GONE);

        mView =  (IMovieReviewsListView) context;


    }

    public void fetchReviewsList(){

        MovieMateApplication application = MovieMateApplication.create(mContext);
        MovieService movieService = application.getMovieService();


        Disposable disposable = movieService.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(@NonNull MovieResponse movieResponse) throws Exception {

                        changeMovieDataSet(movieResponse.getReviews());
                        mProgress.set(View.GONE);
                        mRecycler.set(View.VISIBLE);
                        Log.d("MovieReviewsViewModel", "Movie Response -> " + movieResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mRecycler.set(View.GONE);
                        mProgress.set(View.VISIBLE);
                        Log.d("MovieReviewsViewModel", "Something went wrong -> " + throwable.getLocalizedMessage());

                    }
                });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void update(List<MovieReview> reviews) {
        mView.displayResults(reviews);
    }

    private void changeMovieDataSet(List<MovieReview> movieReviews){
        mReviewsList.addAll(movieReviews);
        update(movieReviews);

    }

    public List<MovieReview> getMovieReviewsList(){

        return mReviewsList;
    }

    private void unsubscribeFromObservable(){
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }

    public void reset(){
        unsubscribeFromObservable();
        mCompositeDisposable = null;
        mContext = null;
    }

}
