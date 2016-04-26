package com.util.yongheshen.myutil.annotation;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Diablo on 16/4/26.
 */
public class AnnotationAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Apple().getName();
    }
}
