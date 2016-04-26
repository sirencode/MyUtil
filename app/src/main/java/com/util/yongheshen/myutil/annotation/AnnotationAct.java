package com.util.yongheshen.myutil.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.util.yongheshen.myutil.R;

/**
 * Created by Diablo on 16/4/26.
 * 注解的使用：
 * 1 自定义注解并添加到对应的位置。
 * 2 解析注解并处理，通过被添加类反射获取到所有的属性和方法，之后判断属性或者方法或者类是否添加了某个注解，要是添加了，
 * 通过注解名称获取到对应的注解属性，之后将注解中的值取出来，处理。
 *
 */
@ContentView(R.layout.annotation)
public class AnnotationAct extends Activity {

    @ContentWidget(R.id.tv_ann)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.injectObject(this, this);
        mTextView.setText("自定义注解使用");
        Apple apple = new Apple();
        apple.getName();
    }
}
