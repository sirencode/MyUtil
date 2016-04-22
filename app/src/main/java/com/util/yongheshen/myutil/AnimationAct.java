package com.util.yongheshen.myutil;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Diablo on 16/4/21.
 */
public class AnimationAct extends Activity implements View.OnClickListener {

    private ImageView mImageView;
    MyAnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        initViews();
    }

    private void initViews() {
        mImageView = (ImageView) findViewById(R.id.iv_animation);
        Button alpha = (Button) findViewById(R.id.btn_loadxml);
        alpha.setOnClickListener(this);
        Button rotate = (Button) findViewById(R.id.btn_anim);
        rotate.setOnClickListener(this);
        Button flame = (Button) findViewById(R.id.btn_flame);
        flame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loadxml:
                Animation anim = AnimationUtils.loadAnimation(AnimationAct.this, R.anim.base);
                mImageView.startAnimation(anim);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        System.out.println("()===>Animation Start!");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        System.out.println("()===>Animation End!");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        System.out.println("()===>Animation Repeat!");
                    }
                });
                break;

            case R.id.btn_anim:
                //true表示用animation的interpolator
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.5f);
                alphaAnimation.setDuration(1000);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(1000);
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 320.0f, 0.0f, 480.0f);
                translateAnimation.setDuration(1000);
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(1000);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(rotateAnimation);
                mImageView.startAnimation(animationSet);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        System.out.println("animationSet ===> start");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        System.out.println("animationSet ===> end");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        System.out.println("animationSet ===> repeat");
                    }
                });
                break;

            case R.id.btn_flame:
                //如果需要播放到最后消失掉，其实只需要在animation-list中最后增加一个item
                //<item android:drawable="@android:id/empty" android:duration="100" />
                animationDrawable = new MyAnimationDrawable((AnimationDrawable) getResources().getDrawable(R.drawable.animation)) {
                    @Override
                    void onAnimationEnd() {
                        System.out.println("MyAnimationDrawable=====>end");
                    }
                };
                mImageView.setImageDrawable(animationDrawable);
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.start();
                break;
            default:
                break;
        }
    }


    /**
     * 自定义AnimationDrawable实现对帧动画结束事件的监听
     */
    abstract class MyAnimationDrawable extends AnimationDrawable {
        Handler finishHandler;      // 判断结束的Handler

        public MyAnimationDrawable(AnimationDrawable ad) {
            // 这里得自己把每一帧加进去
            for (int i = 0; i < ad.getNumberOfFrames(); i++) {
                this.addFrame(ad.getFrame(i), ad.getDuration(i));
            }
        }

        @Override
        public void start() {
            super.start();
            /**
             * 首先用父类的start()
             * 然后启动线程，来调用onAnimationEnd()
             */
            finishHandler = new Handler();
            finishHandler.postDelayed(
                    new Runnable() {
                        public void run() {
                            onAnimationEnd();
                        }
                    }, getTotalDuration());
        }

        /**
         * 这个方法获得动画的持续时间（之后调用onAnimationEnd()）
         */
        public int getTotalDuration() {
            int durationTime = 0;
            for (int i = 0; i < this.getNumberOfFrames(); i++) {
                durationTime += this.getDuration(i);
            }
            return durationTime;
        }

        /**
         * 结束时调用的方法，一定要实现
         */
        abstract void onAnimationEnd();
    }

}
