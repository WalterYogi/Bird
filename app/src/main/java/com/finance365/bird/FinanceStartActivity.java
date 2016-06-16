package com.finance365.bird;

import android.os.Bundle;

import com.finance365.bird.activity.StartActivity;
import com.finance365.R;

/**
 * Created by admin on 2016/5/16.
 */
public class FinanceStartActivity extends StartActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImageView(R.drawable.start);
    }
}
