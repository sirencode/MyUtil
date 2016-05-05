package com.util.yongheshen.myutil.mvp.presenter;

/**
 * Created by Diablo on 16/4/28.
 */
public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
