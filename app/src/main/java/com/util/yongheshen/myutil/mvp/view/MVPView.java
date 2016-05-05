package com.util.yongheshen.myutil.mvp.view;

import com.util.yongheshen.myutil.mvp.model.MvpBean;

/**
 * Created by Diablo on 16/4/28.
 * 处理业务需要view哪些变化
 */
public interface MVPView {
    void showData(MvpBean mvpBean);

    void showProgress();

    void hideProgress();
}
