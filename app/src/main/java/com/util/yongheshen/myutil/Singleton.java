package com.util.yongheshen.myutil;

/**
 * 作者： shenyonghe689 on 16/4/12.
 */
public class Singleton
{
    private static Singleton singleton = null;

     public Singleton getInstance(){
         if (singleton == null){
             singleton = new Singleton();
         }
         return singleton;
     }

    /**
     * 线程安全问题,懒汉模式无并发问题
     */
    private static Singleton msingleton = new Singleton();

    public Singleton getmInstance(){
        return msingleton;
    }
}
