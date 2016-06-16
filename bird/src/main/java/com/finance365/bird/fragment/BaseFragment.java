package com.finance365.bird.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance365.bird.App;
import com.finance365.bird.activity.BaseActivity;

/**
 * Created by admin on 2016/5/24.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected Context mContext;
    public App app;
    protected  View mContainerView;
    protected int mViewId;
    protected String mTitle;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
        mContext = activity.getBaseContext();
        app = mActivity.app;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(app == null){
            app = App.app;
        }
    }

    protected void setTitle(String title){
        this.mTitle = title;
        mActivity.setActionBarTitle(mTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mContainerView == null){
            mContainerView = inflater.inflate(mViewId, null);
            initView(mContainerView);
        }
        ViewGroup parent = (ViewGroup) mContainerView.getParent();
        if(parent != null){
            parent.removeView(mContainerView);
        }
        return mContainerView;
    }

    protected void setContainerView(int viewId) {
        mViewId = viewId;
    }

    protected void initView(View view){
    }

    /**
     * 在actionbar中提示加载
     * @param isShow
     */
    protected void showProgress(boolean isShow) {
        mActivity.setProgressBarIndeterminateVisibility(isShow);
    }

}
