package com.finance365.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance365.bird.Api;
import com.finance365.bird.R;
import com.finance365.bird.model.RequestUrl;
import com.finance365.bird.presenter.PostPresenter;
import com.finance365.bird.ui.utils.StringUtils;
import com.finance365.bird.view.IObjectView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Response;

/**
 * Created by admin on 2016/5/27.
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone;
    private EditText etCode;
    private TextView tvCode;
    private EditText etPassWord;
    private EditText etRepeatPassWord;
    private Button btnRegister;
    private TextView tvLogin;
    private PostPresenter presenter;
    private PostPresenter codePresenter;
    private RelativeLayout loading;

    private int timeLength = 120;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);
        setBackMode(BACK, "注册");
        presenter = new PostPresenter(mContext, iObjectView);
        codePresenter = new PostPresenter(mContext, codeCObjectView);
        initView();
    }

    private void initView() {
        loading = (RelativeLayout) findViewById(R.id.relative_loading);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvCode = (TextView) findViewById(R.id.tv_code);
        etCode = (EditText) findViewById(R.id.et_code);
        etPassWord = (EditText) findViewById(R.id.et_pass_word);
        etRepeatPassWord = (EditText) findViewById(R.id.et_repeat_pass_word);
        btnRegister = (Button) findViewById(R.id.btn_register);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        if (id == R.id.tv_code) {
            String phone = etPhone.getText().toString().trim();
            if(StringUtils.isBlank(phone)) {
                lenghtShort("请输入手机号");
            } else {
                tvCode.setClickable(false);
                timer = new Timer();
                timer.schedule(timerTask, 1000, 1000);
            }

        } else if (id == R.id.btn_register) {

        } else if (id == R.id.tv_login) {
//            intent = new Intent(this, LoginActivity.class);
            finish();
        }

        if(intent != null){
            startActivity(intent);
        }
    }

    /**
     * 注册请求
     */
    public IObjectView iObjectView = new IObjectView() {
        @Override
        public void setObject(String response) {
        }

        @Override
        public RequestUrl getRequestUrl() {
            RequestUrl requestUrl = bindUrl(Api.LOGIN, false);
            HashMap<String, String> params = requestUrl.getParams();
            params.put("ac", etPhone.getText().toString().toString());
            params.put("pwd", etPassWord.getText().toString().toString());
            return requestUrl;
        }

        @Override
        public boolean handlerRequest(Response response) {
            return handlerRequest(response);
        }


        @Override
        public void onSuccess() {
            lenghtShort("注册成功");
            mActivity.finish();
            loading.setVisibility(View.GONE);
        }

        @Override
        public void onFailed() {
            lenghtShort("注册失败");
            loading.setVisibility(View.GONE);
        }
    };
    /**
     * 获取验证码
     */
    public IObjectView codeCObjectView = new IObjectView() {
        @Override
        public void setObject(String response) {
        }

        @Override
        public RequestUrl getRequestUrl() {
            RequestUrl requestUrl = bindUrl(Api.LOGIN, false);
            HashMap<String, String> params = requestUrl.getParams();
            return requestUrl;
        }

        @Override
        public boolean handlerRequest(Response response) {
            return handlerRequest(response);
        }


        @Override
        public void onSuccess() {
            timer = new Timer();
            timer.schedule(timerTask, 1000, 1000);
        }

        @Override
        public void onFailed() {
            lenghtShort("发送失败");
            tvCode.setClickable(true);
        }
    };
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            timeLength--;
            tvCode.setText(String.valueOf(timeLength) + "s");
            if (timeLength <= 0) {
                tvCode.setText("获取验证码");
                timeLength = 120;
                tvCode.setClickable(true);
                timer.cancel();
            }
        }
    };


}
