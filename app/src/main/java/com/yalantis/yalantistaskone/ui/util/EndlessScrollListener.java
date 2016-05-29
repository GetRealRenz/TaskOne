package com.yalantis.yalantistaskone.ui.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Антон on 22.05.2016.
 */

/**
 * Endless scroll listener for recycler view
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private int mPrevious = 0;
    private boolean isLoading = true;
    private int mCurrent = 0;
    private static final int OFFSET = 20;
    private final LinearLayoutManager mLayoutManager;

    public EndlessScrollListener(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleCount = recyclerView.getChildCount();
        int totalItems = mLayoutManager.getItemCount();
        int firstItem = mLayoutManager.findFirstVisibleItemPosition();
        if (isLoading) {
            if (totalItems > mPrevious) {
                isLoading = false;
                mPrevious = totalItems;
            }
        }
        if (!isLoading && (totalItems - visibleCount) <= firstItem) {
            mCurrent += OFFSET;
            onLoadMore(mCurrent);
            isLoading = true;
        }
    }

    public abstract void onLoadMore(int page);
}
