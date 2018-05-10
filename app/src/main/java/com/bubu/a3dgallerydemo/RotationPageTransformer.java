    package com.bubu.a3dgallerydemo;

    import android.content.Context;
    import android.support.v4.view.ViewPager;
    import android.view.View;
    import android.widget.ImageView;

    /**
     * Fixme
     * Author: LWJ
     * desc:
     * Date: 2017/09/19 10:13
     * Copyright (c) 2016 d2cmall. All rights reserved.
     */

    public class RotationPageTransformer implements ViewPager.PageTransformer{
        private static final float MIN_SCALE=0.85f;
        private static final float MIN_ALPHA=0.6f;

        public void setContext(Context context) {
            mContext = context;
        }

        private Context mContext;
        @Override
        public void transformPage(View page, float position) {
            float scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(position));
            float scaleAlpha = Math.max(MIN_ALPHA,1 - Math.abs(position));
            ImageView imageTag= (ImageView) page.findViewById(R.id.iv_cover);
            float rotate = 0;
            //position小于等于1的时候，代表page已经位于中心item的最左边，
            //此时设置为最小的缩放率以及最大的旋转度数
            if (position <= -1){
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
                imageTag.setAlpha((float) 1);
            }//position从0变化到-1，page逐渐向左滑动
            else if (position < 0){
                imageTag.setAlpha(Math.abs(position));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }//position从0变化到1，page逐渐向右滑动
            else if (position >=0 && position < 1){
                imageTag.setAlpha(position);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }//position大于等于1的时候，代表page已经位于中心item的最右边
            else if (position >= 1){
                imageTag.setAlpha((float)1);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }


        }
    }
