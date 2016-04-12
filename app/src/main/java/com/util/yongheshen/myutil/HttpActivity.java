package com.util.yongheshen.myutil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Diablo
 */

public class HttpActivity extends Activity implements View.OnClickListener
{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_main);
        initViews();
    }

    private void initViews()
    {
        textView = (TextView) findViewById(R.id.tv_info);
        Button urlGet, urlPost, getImg;
        urlGet = (Button) findViewById(R.id.btn_urlGet);
        urlPost = (Button) findViewById(R.id.btn_urlPost);
        getImg = (Button) findViewById(R.id.btn_getImg);
        urlGet.setOnClickListener(this);
        urlPost.setOnClickListener(this);
        getImg.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v)
    {
        final List<BaseKeyValueDto> headers = new ArrayList<>();
        BaseKeyValueDto header = new BaseKeyValueDto("zoneCode", "0575");
        BaseKeyValueDto header1 = new BaseKeyValueDto("token",
                "58621268-04f3-4cc7-a57b-6471f41eb116");
        headers.add(header);
        headers.add(header1);
        RequestUtil requestUtil = new RequestUtil(getBaseContext()){

            @Override
            void OnHttpsGetAndPostSucceed(final String result)
            {

                runOnUiThread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        textView.setText(result);
                    }
                });
            }

            @Override
            void OnHttpsGetAndPostError(String errorInfo)
            {

            }
        };

        switch (v.getId())
        {
            case R.id.btn_urlGet:
                requestUtil.doHttpsGet(MyConfig.URL, headers, "zhengshu.bks", MyConfig.BKSFILEKEY);
                break;

            case R.id.btn_urlPost:
//                requestUtil.doHttpsPost(Config.POSTURL, Config.POSTPARAM, headers, "zhengshu.bks",
//                        Config.BKSFILEKEY);
                requestUtil.doHttpPost("http://www.baidu.com",null,null);
                break;

            case R.id.btn_getImg:

                break;

            default:
                break;
        }

    }

}

