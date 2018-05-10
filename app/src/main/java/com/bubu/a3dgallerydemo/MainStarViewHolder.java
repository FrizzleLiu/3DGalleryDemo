package com.bubu.a3dgallerydemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fixme
 * Author: LWJ
 * desc:
 * Date: 2017/09/07 11:40
 * Copyright (c) 2016 d2cmall. All rights reserved.
 */

public class MainStarViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.viewpager)
        public CustomViewPager mViewPager;
        @Bind(R.id.tv_star_desc)
        public TextView tvStarDesc;
        @Bind(R.id.view_left)
        public View viewLeft;
        @Bind(R.id.view_right)
        public View viewRight;

        public MainStarViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


}
