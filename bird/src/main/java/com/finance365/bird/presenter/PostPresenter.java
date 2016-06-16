package com.finance365.bird.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.finance365.bird.model.RequestUrl;
import com.finance365.bird.view.IObjectView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by admin on 2016/5/26.
 */
public class PostPresenter {
    private Context mContext;
    private IObjectView iObjectView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            iObjectView.setObject((String)msg.obj);
        }
    };
    public PostPresenter(Context mContext, IObjectView iObjectView){
        this.mContext = mContext;
        this.iObjectView = iObjectView;
    }

    public void post(){
        final RequestUrl requestUrl = iObjectView.getRequestUrl();
        OkHttpUtils.post().url(requestUrl.url).params(requestUrl.getParams()).build().execute(new StringCallback() {

            @Override
            public String parseNetworkResponse(Response response) throws IOException {
                return super.parseNetworkResponse(response);
            }

            @Override
            public boolean validateReponse(Response response) {
                return iObjectView.handlerRequest(response);
            }

            @Override
            public void onError(Call call, Exception e) {
                iObjectView.onFailed();
            }

            @Override
            public void onResponse(String response) {
                Message message = handler.obtainMessage();
                message.obj = response;
                handler.sendMessage(message);
                iObjectView.onSuccess();
            }
        });
    }


}
