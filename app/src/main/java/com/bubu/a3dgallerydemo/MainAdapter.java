    package com.bubu.a3dgallerydemo;

    import android.content.Context;
    import android.content.Intent;
    import android.support.v4.view.ViewPager;
    import android.support.v7.widget.RecyclerView;
    import android.util.DisplayMetrics;
    import android.view.LayoutInflater;
    import android.view.MotionEvent;
    import android.view.View;
    import android.view.ViewGroup;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * Created by Administrator on 2018/5/10.
     * Description : MainAdapter
     */

    public class MainAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private float downX ;    //按下时 的X坐标
        private float downY ;    //按下时 的Y坐标
        private int currentPosition;
        private int mOriginSize;
        private List<String> dataList=new ArrayList<>();

        public MainAdapter(Context context,List<String> list) {
            this.mContext=context;
            dataList.addAll(list);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_main_star1, parent, false);
            return new MainStarViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //  所以在空白的左右两侧各填充了透明的View(StarView自定义View拦截左右方向滑动事件),用于交互,左侧View向右滑动和点击ViewPager切换
            // ,右侧View向左滑动和点击切换ViewPager,其它不做处理
            final MainStarViewHolder mainStarHolder = (MainStarViewHolder) holder;
            mainStarHolder.mViewPager.setPageTransformer(true, new RotationPageTransformer());
            mainStarHolder.mViewPager.setOffscreenPageLimit(2);
            DisplayMetrics dm = mContext.getApplicationContext().getResources().getDisplayMetrics();
            //不同分辨率适配
            int width = dm.widthPixels;
            if (width > 800 && width <= 1080) {
                mainStarHolder.mViewPager.setPageMargin(-200);
            } else if (width > 1080) {
                mainStarHolder.mViewPager.setPageMargin(-280);
            } else {
                mainStarHolder.mViewPager.setPageMargin(-180);
            }

            mainStarHolder.viewLeft.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) { //左侧透明View仅在向右滑时切换ViewPager
                    float x= event.getX();
                    float y = event.getY();
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            //将按下时的坐标存储
                            downX = x;
                            downY = y;
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            //获取到距离差
                            float dx= x-downX;
                            float dy = y-downY;
                            //防止是按下也判断
                            //通过距离差判断方向
                            int orientation = getOrientation(dx, dy);
                            switch (orientation) {
                                case 'r':       //向右滑动
                                    mainStarHolder.mViewPager.setCurrentItem(mainStarHolder.mViewPager.getCurrentItem()-1,true);
                                    return true;
                                case '0':   //点击
                                    mainStarHolder.mViewPager.setCurrentItem(mainStarHolder.mViewPager.getCurrentItem()-1,true);
                                    return true;
                                case 'l':       //向左滑动
                                    return true;
                            }
                            break;
                    }
                    return true;
                }
            });
            mainStarHolder.viewRight.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {     //右侧透明View仅在向右滑时切换ViewPager
                    float x= event.getX();
                    float y = event.getY();
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            //将按下时的坐标存储
                            downX = x;
                            downY = y;
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            //获取到距离差
                            float dx= x-downX;
                            float dy = y-downY;
                            //通过距离差判断方向
                            int orientation = getOrientation(dx, dy);
                            switch (orientation) {
                                case 'r':       //向右滑动
                                    return true;
                                case '0':       //点击
                                    mainStarHolder.mViewPager.setCurrentItem(mainStarHolder.mViewPager.getCurrentItem()+1,true);
                                    return true;
                                case 'l':       //向左滑动
                                    if(event.getAction()== MotionEvent.ACTION_UP || event.getAction()== MotionEvent.ACTION_CANCEL ){
                                        mainStarHolder.mViewPager.setCurrentItem(mainStarHolder.mViewPager.getCurrentItem()+1,true);
                                    }
                                    return true;
                            }
                            break;
                    }
                    return true;
                }
            });
            mOriginSize = dataList.size();
            initStarAdapter(mainStarHolder, dataList);
            mainStarHolder.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPosition = position;
                    mainStarHolder.mViewPager.setTranslationX(1);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        private void initStarAdapter(MainStarViewHolder mainStarHolder, List<String> dataList) {
            final MainStarAdapter mainStarAdapter = new MainStarAdapter(dataList, mContext, mainStarHolder.mViewPager, mOriginSize);
            mainStarHolder.mViewPager.setAdapter(mainStarAdapter);
            mainStarHolder.mViewPager.setCurrentItem( 20*mOriginSize + currentPosition);
        }
            //获取滑动方向
        private int getOrientation(float dx, float dy) {
            if (Math.abs(dx)>Math.abs(dy)){
                //X轴移动
                if(dx==0){//点击
                    return '0';
                }
                return dx>0?'r':'l';
            }else{
                //Y轴移动
                if(dy==0){//点击
                    return '0';
                }
                return dy>0?'b':'t';
            }
        }
    }
