package com.finance365.bird.model;

import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by admin on 2016/5/4.
 */
public class InterfaceModel {

    public interface IObjectModel{
        void getData(RequestUrl requestUrl, StringCallback callback);
    }

    public interface OnResultListener {
        void onSuccess(String response);
        void onFailed();
    }
}
