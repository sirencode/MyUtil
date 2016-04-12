package com.util.yongheshen.myutil;

import android.util.Log;

/**
 * 作者： shenyonghe689 on 16/4/12.
 */
public class LogUtil
{
        //日志打印阀值设置
        public static boolean LOGOPEN = true;

        public static void logV(String tag, String msg)
        {
            if (LogUtil.LOGOPEN)
            {
                Log.v(tag, msg);
            }
        }

        public static void logD(String tag, String msg)
        {
            if (LogUtil.LOGOPEN)
            {
                Log.d(tag, msg);
            }
        }

        public static void logI(String tag, String msg)
        {
            if (LogUtil.LOGOPEN)
            {
                Log.i(tag, msg);
            }
        }

        public static void logW(String tag, String msg)
        {
            if (LogUtil.LOGOPEN)
            {
                Log.w(tag, msg);
            }
        }

        public static void logE(String tag, String msg)
        {
            if (LogUtil.LOGOPEN)
            {
                Log.e(tag, msg);
            }
        }

        public static void systemOut(String msg)
        {
            if (LogUtil.LOGOPEN)
            {
                System.out.println(msg);
            }
        }
}
