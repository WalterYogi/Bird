package com.finance365.bird.model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by admin on 2016/5/5.
 */
public class GetDataModel implements InterfaceModel.IObjectModel {
    @Override
    public void getData(RequestUrl requestUrl, final StringCallback callback) {
        OkHttpUtils.get().url(requestUrl.url).params(requestUrl.getParams()).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response) throws Exception {
                response.code();
                return null;
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(call, e);
            }

            @Override
            public void onResponse(Object response) {
                callback.onResponse((String)response);
            }
        });
    }

}
