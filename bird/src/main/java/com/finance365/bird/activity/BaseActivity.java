package com.finance365.bird.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.finance365.bird.App;
import com.finance365.bird.R;
import com.finance365.bird.model.RequestUrl;
import com.finance365.bird.ui.utils.NetUtils;
import com.finance365.bird.ui.utils.StringUtils;
import com.finance365.bird.ui.view.SegmentedGroup;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import okhttp3.Response;

/**
 * Created by admin on 2016/5/16.
 */
public class BaseActivity extends ActionBarActivity {

    public App app;
    public ActionBar mActionBar;
    protected BaseActivity mActivity;
    protected Context mContext;
    public Gson gson;

    private View actionBarTitle;
    private View actionBarTitleRight;
    private TextView tvActionBarTitle;
    private TextView tvActionBarRight;
    public String BACK = "返回";
    private View actionBarTitle2;
    public SegmentedGroup segmentedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        gson = new Gson();
        initActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(mContext);//友盟统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(mContext);//友盟统计
    }

    /**
     * 初始化
     */
    private void initActivity() {
        app = (App) getApplication();
        mActionBar = getSupportActionBar();
        setProgressBarIndeterminateVisibility(false);
        app.mActivity = mActivity;
        app.mContext = mContext;
    }

    /**
     * 隐藏actionbar
     */
    public void hideActionBar() {
        mActionBar.hide();
    }

    /**
     * 显示actionbar
     */
    public void showActionBar() {
        mActionBar.show();
    }

    /**
     * 设置actionbar标题
     *
     * @param title
     */
    public void setActionBarTitle(String title) {
        tvActionBarTitle.setText(title == null ? "" : title);
    }

    public void setActionBarTitle(int ids) {
        tvActionBarTitle.setText(ids);
    }

    /**
     * 设置actionBar样式
     *
     * @param backTitle
     * @param title
     */
    public void setBackMode(String backTitle, String title) {
        actionBarTitle = getLayoutInflater().inflate(R.layout.base_actionbar_layout, null);
        tvActionBarTitle = (TextView) actionBarTitle.findViewById(R.id.action_bar_title);
        tvActionBarTitle.setText(title);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        mActionBar.setCustomView(actionBarTitle, layoutParams);
        if (backTitle != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setBackMode(String backTitle, String title, String right) {
        actionBarTitleRight = getLayoutInflater().inflate(R.layout.base_actionbar_right_layout, null);
        tvActionBarTitle = (TextView) actionBarTitleRight.findViewById(R.id.action_bar_title);
        tvActionBarTitle.setText(title);
        tvActionBarRight = (TextView) actionBarTitleRight.findViewById(R.id.action_bar_right);
        if (!StringUtils.isBlank(right)) {
            tvActionBarRight.setText(right);
            tvActionBarRight.setVisibility(View.VISIBLE);
        }
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        mActionBar.setCustomView(actionBarTitle, layoutParams);
        if (backTitle != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setBackMode(String backTitle, String title, int icon) {
        setBackIcon(icon);
        setBackMode(backTitle, title);
    }

    /**
     * 设置actionbar返回图标
     *
     * @param icon
     */
    public void setBackIcon(int icon) {
        mActionBar.setHomeAsUpIndicator(icon);
    }

    /**
     * 右边点击事件
     * @param listener
     */
    public void setOnclickListener(View.OnClickListener listener){
        tvActionBarRight.setOnClickListener(listener);
    }

    /**
     * 设置actionBar样式2
     *
     * @param backTitle
     * @param id
     */
    public void setSegmentedMode(String backTitle, int id) {
        actionBarTitle2 = getLayoutInflater().inflate(R.layout.actionbar_segmented_layout, null);
        segmentedGroup = (SegmentedGroup) actionBarTitle2.findViewById(R.id.segmented_group);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        segmentedGroup.check(id);
        mActionBar.setCustomView(actionBarTitle2, layoutParams);
        if (backTitle != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 分段选择
     *
     * @param listener
     */
    public void setSegmentedGroupClickListener(RadioGroup.OnCheckedChangeListener listener) {
        segmentedGroup.setOnCheckedChangeListener(listener);
    }

    /**
     * actionbar返回键
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 得到网络请求RequestUrl
     *
     * @param url
     * @param addToken
     * @return
     */
    public RequestUrl bindUrl(String url, boolean addToken) {
        StringBuffer sb = new StringBuffer(app.host);
        sb.append(url);
        RequestUrl requestUrl = new RequestUrl(sb.toString());

        if (addToken) {
            requestUrl.heads.put("token", app.token);
        }
        return requestUrl;
    }

    /**
     * Toast信息
     *
     * @param content
     */
    public void lenghtShort(String content) {
        Toast.makeText(mActivity, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 网络请求判断
     *
     * @param response
     * @return
     */
    public boolean handerRequest(Response response) {
        if (!NetUtils.isNetworkAvailable(mContext)) {
            lenghtShort("无网络,请检查网络和手机设置!");
            return false;
        }

        if (response.code() != 200) {
            lenghtShort("服务器访问异常!");
            return false;
        }
        return true;
    }
}
