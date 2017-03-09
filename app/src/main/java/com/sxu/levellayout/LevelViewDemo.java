package com.sxu.levellayout;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LevelViewDemo extends AppCompatActivity {

    private ViewPager viewPager;
    private int currentPos;
    private boolean isPressed = false;
    private int[] icon = {R.drawable.star_0, R.drawable.star_3, R.drawable.star_4, R.drawable.star_5};
    private int[] clickedIcon = {R.drawable.star_0_click, R.drawable.star_3_click, R.drawable.star_4_click, R.drawable.star_5_click};
    private String[] title = {"沪较短驳", "人民优步+", "优选轿车", "高级轿车"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyViewPagerAdapter());
    }

    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemLayout = getLayoutInflater().inflate(R.layout.item_layout, null, false);
            final LevelView levelView = (LevelView) itemLayout.findViewById(R.id.level_view);
            final LinearLayout levelTitleLayout = (LinearLayout) itemLayout.findViewById(R.id.level_title_layout);

            if (levelView.getStepCount() != 0) {
                for (int i = 0; i <= levelView.getStepCount(); i++) {
                    TextView textView = new TextView(LevelViewDemo.this);
                    textView.setText(title[i]);
                    textView.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.weight = 1;
                    textView.setLayoutParams(params);
                    levelTitleLayout.addView(textView);
                }
            }

            levelView.setOnLevelChangeListener(new LevelView.OnLevelChangeListener() {
                @Override
                public void onLevelChanged(int progress) {
                    int currentPos = (int)(progress / levelView.getStepLength());
                    int offset = (int)(progress % levelView.getStepLength());
                    if (currentPos >=0 && currentPos < icon.length-1 && offset > levelView.getStepLength() / 2) {
                        currentPos++;
                    }
                    if (isPressed) {
                        levelView.setThumb(((BitmapDrawable)getResources().getDrawable(clickedIcon[currentPos])).getBitmap());
                    } else {
                        levelView.setThumb(((BitmapDrawable)getResources().getDrawable(icon[currentPos])).getBitmap());
                    }
                }

                @Override
                public void onLevelClick() {
                    isPressed = !isPressed;
                    if (isPressed) {
                        levelView.setThumb(((BitmapDrawable)getResources().getDrawable(clickedIcon[currentPos])).getBitmap());
                    } else {
                        levelView.setThumb(((BitmapDrawable)getResources().getDrawable(icon[currentPos])).getBitmap());
                    }
                }

                @Override
                public void onStartTrackingTouch() {

                }

                @Override
                public void onStopTrackingTouch() {
                    if (isPressed) {
                        levelView.setThumb(((BitmapDrawable)getResources().getDrawable(clickedIcon[currentPos])).getBitmap());
                    } else {
                        levelView.setThumb(((BitmapDrawable)getResources().getDrawable(icon[currentPos])).getBitmap());
                    }
                }
            });

            container.addView(itemLayout);
            return itemLayout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}
