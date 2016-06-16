package com.finance365.bird.view;

/**
 * Created by admin on 2016/6/1.
 */
public interface ObjectView extends IObjectView {
    void onBefore();
    void onAfter();
    void inProgress(float progress);
}
