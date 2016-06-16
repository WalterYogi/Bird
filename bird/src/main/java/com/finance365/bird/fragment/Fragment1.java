package com.finance365.bird.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.finance365.bird.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/6/6.
 */
public class Fragment1 extends BaseFragment {
    OptionsPickerView pvOptions;
    View vMasker;
    TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContainerView(R.layout.fragment_1);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        textView = (TextView) view.findViewById(R.id.tv_fragment1);
        vMasker = view.findViewById(R.id.vMasker);
        pvOptions = new OptionsPickerView(mActivity);
        final ArrayList<String> options2Items_01 = new ArrayList<String>();
        options2Items_01.add("男");
        options2Items_01.add("女");
        pvOptions.setPicker(options2Items_01);
        pvOptions.setSelectOptions(0);
        pvOptions.setCyclic(false);
        pvOptions.setTitle("性别");
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                textView.setText(options2Items_01.get(options1));
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }
}
