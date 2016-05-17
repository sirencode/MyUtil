package com.util.yongheshen.myutil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.util.yongheshen.myutil.mvp.ui.MVPAct;

/**
 * Created by Diablo on 16/5/13.
 */
public class ThreadAct extends Activity{

    private TextView mTextView;
    private Button   mStopBtn;
    private MyThread mMyThread;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        mMyThread = new MyThread();
        mMyThread.start();
        initViews();
    }

    private void initViews(){
        mTextView = (TextView) findViewById(R.id.tv_Th);
        mStopBtn = (Button) findViewById(R.id.btn_stopTh);
        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadAct.this, MVPAct.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class MyThread extends Thread{
        boolean flag = false;

        @Override
        public void run() {
            while (!flag){
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程执行中。。。");
                    mHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher(this).watch(this);
    }
}
