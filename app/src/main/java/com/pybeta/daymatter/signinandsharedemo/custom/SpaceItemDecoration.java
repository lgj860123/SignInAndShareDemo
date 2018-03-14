package com.pybeta.daymatter.signinandsharedemo.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 给RecyclerView的item设置间距
 * Created by luogj on 2018/2/27.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        outRect.top = mSpace;
        if (parent.getChildAdapterPosition(view) == 0) {
        }

    }

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }
}
