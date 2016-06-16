package com.finance365.bird.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.finance365.bird.R;

/**
 * Created by admin on 2016/5/27.
 * 多个分段选择
 */
public class DemoSegmentedActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);
        setSegmentedMode(BACK, R.id.radio_1);
        setSegmentedGroupClickListener(mRadioGroupOnCheckedChangeListener);
    }

    RadioGroup.OnCheckedChangeListener mRadioGroupOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.radio_1){
                lenghtShort("radio1");
            } else if(checkedId == R.id.radio_2){
                lenghtShort("radio2");
            }
        }
    };
}
