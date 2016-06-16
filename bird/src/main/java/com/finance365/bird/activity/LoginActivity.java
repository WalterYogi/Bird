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
import com.finance365.bird.entity.Login;
import com.finance365.bird.model.RequestUrl;
import com.finance365.bird.presenter.PostPresenter;
import com.finance365.bird.ui.utils.StringUtils;
import com.finance365.bird.view.IObjectView;

import java.util.HashMap;

import okhttp3.Response;

/**
 * Created by admin on 2016/5/27.
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText etName;
    private EditText etPassWord;
    private Button btnLogin;
    private TextView tvForgetPW;
    private TextView tvRegister;
    private PostPresenter presenter;
    private RelativeLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        setBackMode(BACK, "登录");
        presenter = new PostPresenter(mContext, iObjectView);
        initView();
    }

    private void initView() {
        loading = (RelativeLayout) findViewById(R.id.relative_loading);
        etName = (EditText) findViewById(R.id.et_name);
        etPassWord = (EditText) findViewById(R.id.et_pass_word);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgetPW = (TextView) findViewById(R.id.tv_forget_pw);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        btnLogin.setOnClickListener(this);
        tvForgetPW.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        if(id == R.id.btn_login){
            String name = etName.getText().toString().trim();
            String pw = etPassWord.getText().toString().trim();
           if(StringUtils.isBlank(name)&&StringUtils.isBlank(pw)){
               lenghtShort("请输入用户名和密码");
           } else if(StringUtils.isBlank(name)){
               lenghtShort("请输入用户名");
           } else if(StringUtils.isBlank(pw)){
               lenghtShort("请输入密码");
           } else {
               loading.setVisibility(View.VISIBLE);
               presenter.post();
           }
        } else if(id == R.id.tv_forget_pw){
            intent = new Intent(this, DemoRefreshActivity.class);
        } else if(id == R.id.tv_register){
            intent = new Intent(this, RegisterActivity.class);
        }
        if(intent != null){
            startActivity(intent);
        }

    }

    public IObjectView iObjectView = new IObjectView() {
        @Override
        public void setObject(String response) {
            Login login = gson.fromJson(response, Login.class);
            lenghtShort(login.getStatus()+"");
        }

        @Override
        public RequestUrl getRequestUrl() {
            RequestUrl requestUrl = bindUrl(Api.LOGIN, false);
            HashMap<String, String> params = requestUrl.getParams();
            params.put("ac", etName.getText().toString().toString());
            params.put("pwd", etPassWord.getText().toString().toString());
            return requestUrl;
        }

        @Override
        public boolean handlerRequest(Response response) {
            return handlerRequest(response);
        }


        @Override
        public void onSuccess() {
            lenghtShort("登录成功");
            mActivity.finish();
            loading.setVisibility(View.GONE);
        }

        @Override
        public void onFailed() {
            lenghtShort("登录失败");
            loading.setVisibility(View.GONE);
        }
    };
}
