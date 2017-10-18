package com.aquarius.moviemate;

import android.databinding.DataBindingUtil;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aquarius.moviemate.databinding.ActivityMainBinding;
import com.aquarius.moviemate.model.MovieReview;
import com.aquarius.moviemate.view.GridItemDecoration;
import com.aquarius.moviemate.view.IMovieReviewsListView;
import com.aquarius.moviemate.view.MovieReviewAdapter;
import com.aquarius.moviemate.view.MovieReviewFragment;
import com.aquarius.moviemate.viewmodel.MovieReviewsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMovieReviewsListView {


    private ActivityMainBinding mMainActivityBinding;
    private MovieReviewsViewModel mMovieReviewsViewModel;
    private MovieReviewAdapter mAdapter;
    private static final String TAG_DIALOG = "review_dialog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupMatchListView(mMainActivityBinding.rvRecylcer);

        mMovieReviewsViewModel.fetchReviewsList();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initDataBinding(){
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMovieReviewsViewModel = new MovieReviewsViewModel(this);
        mMainActivityBinding.setMovieReviewsViewModel(mMovieReviewsViewModel);
    }

    private void setupMatchListView(RecyclerView recyclerView){

        mAdapter = new MovieReviewAdapter(new MovieReviewAdapter.MovieReviewItemSelectedListener() {
            @Override
            public void onMovieReviewItemSelected(MovieReview review) {
                showReviewFragment(review);
            }
        });
        recyclerView.setAdapter(mAdapter);
        int spanCount = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new GridItemDecoration(12));
    }

    private void showReviewFragment(MovieReview review){
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(TAG_DIALOG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        MovieReviewFragment reviewFragment = MovieReviewFragment.newInstance(review);
        reviewFragment.show(ft, TAG_DIALOG);
    }

    @Override
    public void displayResults(List<MovieReview> reviews) {
         mAdapter.setMovieReviewsList(reviews);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieReviewsViewModel.reset();
    }
}
