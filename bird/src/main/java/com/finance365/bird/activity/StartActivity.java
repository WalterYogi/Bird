package com.finance365.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.finance365.bird.R;
import com.finance365.bird.ui.utils.SPUtils;

/**
 * Created by admin on 2016/5/16.
 */
public class StartActivity extends BaseActivity {
    private ImageView imgStart;
    private boolean isExits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        setContentView(R.layout.activity_start_layout);
        isExits = SPUtils.getBoolean(mContext, "first");
        imgStart = (ImageView) findViewById(R.id.img_start);
        myTimer();
    }

    public void setImageView(int resId){
        imgStart.setImageResource(resId);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.activity_start_enter);
        imgStart.setAnimation(animation);
    }

    private void myTimer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        }, 2000);
    }

    private void startActivity() {
        if(isExits){
            startActivity(new Intent(mContext, MainActivity.class));
        } else {
            startActivity(new Intent(mContext, GuideActivity.class));
            SPUtils.putBoolean(mContext, "first", true);
        }
        finish();
    }
}
