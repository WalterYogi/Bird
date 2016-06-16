package com.finance365.bird.view;

import com.finance365.bird.model.RequestUrl;

import okhttp3.Response;

/**
 * Created by admin on 2016/5/27.
 */
public interface IObjectView {
    void setObject(String response);
    RequestUrl getRequestUrl();

    boolean handlerRequest(Response response);

    void onSuccess();
    void onFailed();
}
