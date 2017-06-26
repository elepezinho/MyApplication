package com.concreteexemplo;

import android.widget.AbsListView;

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener
{
    private int currPage = 0;
    private int visibleThreshold = 10;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    public EndlessScrollListener(int visibleThreshold)
    {
        this.visibleThreshold = visibleThreshold;
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {

    }

    public abstract void onLoadMore(int page, int totalItemsCount);

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (totalItemCount < previousTotalItemCount)
        {
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0)
            {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount))
        {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currPage++;
        }


        if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount )
        {
            onLoadMore(currPage + 1, totalItemCount);
            loading = true;
        }
    }

}
