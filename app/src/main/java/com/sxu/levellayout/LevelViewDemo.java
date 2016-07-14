package com.sxu.levellayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LevelViewDemo extends AppCompatActivity {

    private LevelView levelView;
    private LinearLayout levelTitleLayout;

    private boolean isPressed = false;
    private int[] icon = {R.drawable.star_0, R.drawable.star_3, R.drawable.star_4, R.drawable.star_5};
    private int[] clickedIcon = {R.drawable.star_0_click, R.drawable.star_3_click, R.drawable.star_4_click, R.drawable.star_5_click};
    private String[] title = {"沪较短驳", "人民优步+", "优选轿车", "高级轿车"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levelView = (LevelView) findViewById(R.id.level_view);
        levelTitleLayout = (LinearLayout) findViewById(R.id.level_title_layout);

        if (levelView.getStepCount() != 0) {
            for (int i = 0; i <= levelView.getStepCount(); i++) {
                TextView textView = new TextView(this);
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
                    levelView.setThumb(getResources().getDrawable(clickedIcon[currentPos]));
                } else {
                    levelView.setThumb(getResources().getDrawable(icon[currentPos]));
                }
            }

            @Override
            public void onLevelClick() {
                isPressed = !isPressed;
                levelView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isPressed) {
                            levelView.setThumb(getResources().getDrawable(clickedIcon[levelView.getDefaultPos()]));
                        } else {
                            levelView.setThumb(getResources().getDrawable(icon[levelView.getDefaultPos()]));
                        }
                    }
                });
            }

            @Override
            public void onStartTrackingTouch() {

            }

            @Override
            public void onStopTrackingTouch() {
                if (isPressed) {
                    levelView.setThumb(getResources().getDrawable(clickedIcon[levelView.getDefaultPos()]));
                } else {
                    levelView.setThumb(getResources().getDrawable(icon[levelView.getDefaultPos()]));
                }
            }
        });

    }
}
