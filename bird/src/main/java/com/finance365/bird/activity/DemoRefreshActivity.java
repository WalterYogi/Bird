package com.finance365.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.finance365.bird.R;
import com.finance365.bird.model.RequestUrl;
import com.finance365.bird.presenter.PostPresenter;
import com.finance365.bird.view.IObjectView;

import java.util.HashMap;

import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Response;

/**
 * Created by admin on 2016/5/27.
 *
 */
public class DemoRefreshActivity extends BaseActivity implements View.OnClickListener{

    private PtrFrameLayout ptrFrameLayout;

    private PostPresenter presenter;
    private RelativeLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_list_view_layout);
        setBackMode(BACK, "");
        presenter = new PostPresenter(mContext, iObjectView);
        initView();
    }

    private void initView() {
        loading = (RelativeLayout) findViewById(R.id.relative_loading);
        ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_frame_list_view);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
//        if(id == R.id.btn_login){
//
//        } else if(id == R.id.tv_register){
//            intent = new Intent();
//        }

        if(intent != null){
            startActivity(intent);
        }
    }
    private void ptrFrame(){
        // header
//        final StoreHouseHeader header = new StoreHouseHeader(mContext);
//        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
///**
// * using a string, support: A-Z 0-9 - .
// * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
// */
//        header.initWithString('Alibaba');
    }


    /**
     * 请求数据
     */
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
}
