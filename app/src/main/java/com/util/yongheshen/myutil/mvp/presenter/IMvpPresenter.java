package com.util.yongheshen.myutil.mvp.presenter;

import com.util.yongheshen.myutil.mvp.model.MvpBean;

/**
 * Created by Diablo on 16/4/28.
 */
public interface IMvpPresenter {
    void loadDataSuccess(MvpBean mvpBean);

    void loadDataFailure();
}
