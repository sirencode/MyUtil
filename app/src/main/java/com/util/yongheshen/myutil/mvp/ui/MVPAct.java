package com.util.yongheshen.myutil.mvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.util.yongheshen.myutil.R;
import com.util.yongheshen.myutil.mvp.model.MvpBean;
import com.util.yongheshen.myutil.mvp.presenter.MvpPresenter;
import com.util.yongheshen.myutil.mvp.view.MVPView;

/**
 * Created by Diablo on 16/4/27.
 */
public class MVPAct extends Activity implements MVPView{
    private ProgressBar mProgressBar;
    private TextView text;
    private MvpPresenter mMvpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp);
        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mMvpPresenter = new MvpPresenter(this);
        //制造延迟效果
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMvpPresenter.loadData();
            }
        }, 1000);

    }

    @Override
    protected void onDestroy() {
        mMvpPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showData(MvpBean mainModelBean) {
        String showData = getResources().getString(R.string.city) + mainModelBean.getCity()
                + getResources().getString(R.string.wd) + mainModelBean.getWd()
                + getResources().getString(R.string.ws) + mainModelBean.getWs()
                + getResources().getString(R.string.time) + mainModelBean.getTime();
        text.setText(showData);
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
