package com.aquarius.moviemate.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquarius.moviemate.R;
import com.aquarius.moviemate.databinding.ItemMovieReviewBinding;
import com.aquarius.moviemate.model.MovieReview;
import com.aquarius.moviemate.viewmodel.MovieReviewItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byfieldj on 10/17/17.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ViewHolder> {

    private List<MovieReview> mMovieReviewsList;

    private MovieReviewItemSelectedListener reviewSelectionListener;


    public interface MovieReviewItemSelectedListener{

        void onMovieReviewItemSelected(MovieReview review);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieReviewBinding itemMovieReviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie_review, parent, false);
        return new ViewHolder(itemMovieReviewBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bindMovieReview(mMovieReviewsList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewSelectionListener.onMovieReviewItemSelected(mMovieReviewsList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovieReviewsList.size();
    }

    public void setMovieReviewsList(List<MovieReview> matches) {
        this.mMovieReviewsList = matches;
        notifyDataSetChanged();
    }

    public MovieReviewAdapter(MovieReviewItemSelectedListener listener) {
        this.mMovieReviewsList = new ArrayList<>();
        this.reviewSelectionListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        ItemMovieReviewBinding itemMovieReviewBinding;

        public ViewHolder(ItemMovieReviewBinding binding) {
            super(binding.itemMovieReview);
            this.itemMovieReviewBinding = binding;

        }


        void bindMovieReview(MovieReview movieReview) {
            if (itemMovieReviewBinding.getMovieReviewItemViewModel() == null) {
                itemMovieReviewBinding.setMovieReviewItemViewModel(new MovieReviewItemViewModel(movieReview, itemView.getContext()));

            } else {
                itemMovieReviewBinding.getMovieReviewItemViewModel().setMovieReview(movieReview);
            }
        }


    }
}
