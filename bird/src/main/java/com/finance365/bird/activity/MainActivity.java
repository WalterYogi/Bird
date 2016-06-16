package com.finance365.bird.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance365.bird.R;
import com.finance365.bird.fragment.Fragment1;
import com.finance365.bird.fragment.Fragment2;
import com.finance365.bird.fragment.Fragment3;
import com.finance365.bird.fragment.Fragment4;

/**
 * Created by admin on 2016/5/17.
 * 主页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgfragment1;
    private TextView tvfragment1;
    private LinearLayout linearfragment1;
    private ImageView imgfragment2;
    private TextView tvfragment2;
    private LinearLayout linearfragment2;
    private ImageView imgfragment3;
    private TextView tvfragment3;
    private LinearLayout linearfragment3;
    private ImageView imgfragment4;
    private TextView tvfragment4;
    private LinearLayout linearfragment4;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    private FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        setBackMode(null, "首页");
        initView();
        initData();
    }

    //初始化控件
    private void initView() {
        imgfragment1 = (ImageView) findViewById(R.id.img_fragment1);
        tvfragment1 = (TextView) findViewById(R.id.tv_fragment1);
        linearfragment1 = (LinearLayout) findViewById(R.id.linear_fragment1);
        imgfragment2 = (ImageView) findViewById(R.id.img_fragment2);
        tvfragment2 = (TextView) findViewById(R.id.tv_fragment2);
        linearfragment2 = (LinearLayout) findViewById(R.id.linear_fragment2);
        imgfragment3 = (ImageView) findViewById(R.id.img_fragment3);
        tvfragment3 = (TextView) findViewById(R.id.tv_fragment3);
        linearfragment3 = (LinearLayout) findViewById(R.id.linear_fragment3);
        imgfragment4 = (ImageView) findViewById(R.id.img_fragment4);
        tvfragment4 = (TextView) findViewById(R.id.tv_fragment4);
        linearfragment4 = (LinearLayout) findViewById(R.id.linear_fragment4);
        linearfragment1.setOnClickListener(this);
        linearfragment2.setOnClickListener(this);
        linearfragment3.setOnClickListener(this);
        linearfragment4.setOnClickListener(this);
    }

    //初始化数据
    private void initData() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        setTabSelection(1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.linear_fragment1) {
            setTabSelection(1);
        } else if (id == R.id.linear_fragment2) {
            setTabSelection(2);
        } else if (id == R.id.linear_fragment3) {
            setTabSelection(3);
        } else if (id == R.id.linear_fragment4) {
            setTabSelection(4);
        }
    }

    //选中项
    private void setTabSelection(int indexPostion) {
        clearSelection();
        transaction = fragmentManager.beginTransaction();
        hideFragmentAll();
        switch (indexPostion) {
            case 1:
                setActionBarTitle(R.string.main_1);
                imgfragment1.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);
                tvfragment1.setTextColor(getResources().getColor(R.color.app_main_bottom_pressed));
                if(fragment1 == null){
                    fragment1 = new Fragment1();
                    transaction.add(R.id.main_fragment, fragment1);
                } else {
                    transaction.show(fragment1);
                }
                break;
            case 2:
                setActionBarTitle(R.string.main_2);
                imgfragment2.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);
                tvfragment2.setTextColor(getResources().getColor(R.color.app_main_bottom_pressed));
                if(fragment2 == null){
                    fragment2 = new Fragment2();
                    transaction.add(R.id.main_fragment, fragment2);
                } else {
                    transaction.show(fragment2);
                }
                break;
            case 3:
                setActionBarTitle(R.string.main_3);
                imgfragment3.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);
                tvfragment3.setTextColor(getResources().getColor(R.color.app_main_bottom_pressed));
                if(fragment3 == null){
                    fragment3 = new Fragment3();
                    transaction.add(R.id.main_fragment, fragment3);
                } else {
                    transaction.show(fragment3);
                }
                break;
            case 4:
                setActionBarTitle(R.string.main_4);
                imgfragment4.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);
                tvfragment4.setTextColor(getResources().getColor(R.color.app_main_bottom_pressed));
                if(fragment4 == null){
                    fragment4 = new Fragment4();
                    transaction.add(R.id.main_fragment, fragment4);
                } else {
                    transaction.show(fragment4);
                }
                break;
        }
        transaction.commit();

    }

    //隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
    private void hideFragmentAll() {
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
        if (fragment4 != null) {
            transaction.hide(fragment4);
        }
    }

    //清除所有选项
    private void clearSelection() {
        imgfragment1.setImageResource(R.drawable.ic_launcher);
        imgfragment2.setImageResource(R.drawable.ic_launcher);
        imgfragment3.setImageResource(R.drawable.ic_launcher);
        imgfragment4.setImageResource(R.drawable.ic_launcher);
        tvfragment1.setTextColor(getResources().getColor(R.color.app_main_bottom_normal));
        tvfragment2.setTextColor(getResources().getColor(R.color.app_main_bottom_normal));
        tvfragment3.setTextColor(getResources().getColor(R.color.app_main_bottom_normal));
        tvfragment4.setTextColor(getResources().getColor(R.color.app_main_bottom_normal));
    }
}
