package com.aquarius.moviemate.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by byfieldj on 8/1/17.
 * <p>
 * <p>
 * <p>
 * Custom ItemDecoration that instructs the RecyclerView how to lay itself out
 * In this case we want to have the same padding between the cells horizontally and vertically
 */


public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public GridItemDecoration(final int spacing) {
        mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = mSpacing;
        outRect.right = mSpacing;
        outRect.bottom = mSpacing;
        outRect.top = mSpacing;

    }


}
