package com.finance365.bird.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.finance365.bird.R;
import com.finance365.bird.model.RequestUrl;
import com.finance365.bird.presenter.PostPresenter;
import com.finance365.bird.view.IObjectView;

import java.util.HashMap;

import okhttp3.Response;

/**
 * Created by admin on 2016/5/27.
 * 找回密码界面
 */
public class RetrieveActivity extends BaseActivity implements View.OnClickListener{

    private PostPresenter presenter;
    private RelativeLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_layout);
        setBackMode(BACK, "找回密码");
        presenter = new PostPresenter(mContext, iObjectView);
//        setOnclickListener(onClickListener);
        initView();
    }

    private void initView() {
        loading = (RelativeLayout) findViewById(R.id.relative_loading);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_login){

        } else if(id == R.id.tv_forget_pw){

        } else if(id == R.id.tv_register){

        }
    }

    private IObjectView iObjectView = new IObjectView() {
        @Override
        public void setObject(String response) {
//            Login login = gson.fromJson(response, Login.class);
//            List<Login> logins = gson.fromJson(response, new TypeToken<List<Login>>(){}.getType());
        }

        @Override
        public RequestUrl getRequestUrl() {
            RequestUrl requestUrl = bindUrl("", false);
            HashMap<String, String> params = requestUrl.getParams();
            return requestUrl;
        }

        @Override
        public boolean handlerRequest(Response response) {
            return handlerRequest(response);
        }

        @Override
        public void onSuccess() {
            loading.setVisibility(View.GONE);
        }

        @Override
        public void onFailed() {
            loading.setVisibility(View.GONE);
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            lenghtShort("下一步");
        }
    };
}
