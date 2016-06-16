package com.finance365.bird;

import android.os.Bundle;

import com.finance365.bird.activity.GuideActivity;

/**
 * Created by admin on 2016/5/18.
 */
public class FinanceGuideActivity extends GuideActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int[] getImages() {
        int imgs[] = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4};
        return imgs;
    }
}
