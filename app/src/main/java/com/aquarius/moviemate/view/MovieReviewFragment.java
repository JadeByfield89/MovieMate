package com.aquarius.moviemate.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aquarius.moviemate.R;
import com.aquarius.moviemate.databinding.FragmentMovieReviewBinding;
import com.aquarius.moviemate.model.MovieReview;
import com.aquarius.moviemate.viewmodel.IMovieReviewDialogViewModel;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieReviewFragment extends DialogFragment implements IMovieReviewDialogViewModel {

    private static final String EXTRA_MOVIE_REVIEW = "movie_review";
    private static final String SAVE_REVIEW_URL = "review_url";
    private String mReviewUrl;
    FragmentMovieReviewBinding mMovieReviewBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();


        final View decorView = getDialog()
                .getWindow()
                .getDecorView();

        // Let's give this fragment some personality with a Wave animation once it is started
        YoYo.with(Techniques.Wave).duration(600).playOn(decorView);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getArguments() != null){

            MovieReview review = (MovieReview) getArguments().getSerializable(EXTRA_MOVIE_REVIEW);
            mReviewUrl = review.getLink().getUrl();

            if(mReviewUrl != null && !mReviewUrl.isEmpty()){

            }

            if(savedInstanceState == null){
                // load webview url
                loadMovieReview(mReviewUrl);
            }
            else{

            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void loadMovieReview(String reviewUrl){
        WebView webView = mMovieReviewBinding.wvReview;
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(reviewUrl);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_REVIEW_URL, mReviewUrl);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        mMovieReviewBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.fragment_movie_review, null, false);
        View view = mMovieReviewBinding.content;




        builder.setView(view)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


        return builder.create();
    }

    public static MovieReviewFragment newInstance(MovieReview review) {

        Bundle args = new Bundle();

        MovieReviewFragment fragment = new MovieReviewFragment();
        args.putSerializable(EXTRA_MOVIE_REVIEW, review);
        fragment.setArguments(args);
        return fragment;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
