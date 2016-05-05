package com.util.yongheshen.myutil.mvp.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.util.yongheshen.myutil.mvp.presenter.IMvpPresenter;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Diablo on 16/4/28.
 * 业务具体处理，包括负责存储、检索、操纵数据等(数据相关的)
 * model->presenter 将数据传给表现者进行处理
 * p调用m的业务逻辑方法，m执行后将结果数据通过p的接口发给p
 */
public class MvpModel {
    private IMvpPresenter mIMvpPresenter;

    public MvpModel(IMvpPresenter iMvpPresenter) {
        this.mIMvpPresenter = iMvpPresenter;
    }

    public void loadData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("http://www.weather.com.cn/adat/sk/101010100.html", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    System.out.println("http success");
                    MvpBean mvpBean = new MvpBean();
                    JSONObject weatherinfo = response.getJSONObject("weatherinfo");
                    mvpBean.setCity(weatherinfo.getString("city"));
                    mvpBean.setWd(weatherinfo.getString("WD"));
                    mvpBean.setWs(weatherinfo.getString("WS"));
                    mvpBean.setTime(weatherinfo.getString("time"));
                    mIMvpPresenter.loadDataSuccess(mvpBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mIMvpPresenter.loadDataFailure();
            }
        });
    }

}
