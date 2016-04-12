package com.util.yongheshen.myutil;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者： Diablo on 16/4/12.
 */
public class ToastUtil
{
    private static String oldMsg;
    protected static Toast toast   = null;
    private static long oneTime=0;
    private static long twoTime=0;

    public static void showToast(Context context, String s){
        if(toast==null){
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }

    public static void showToast(Context context, int resId){
        showToast(context, context.getString(resId));
    }

}

