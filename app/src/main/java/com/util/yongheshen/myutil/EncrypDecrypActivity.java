package com.util.yongheshen.myutil;

import android.app.Activity;
import android.os.Bundle;

/**
 * 作者： shenyonghe689 on 16/4/12.
 */
public class EncrypDecrypActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        aesDemo();
        desDemo();
    }

    private void aesDemo(){
        // 使用该方法
        String masterPassword = "test";
        String originalText = "0123456789";
        try
        {
            String encryptingCode = AESUtil.encrypt(masterPassword, originalText);
            System.out.println("AES加密结果为 " + encryptingCode);
            String decryptingCode = AESUtil.decrypt(masterPassword, encryptingCode);
            System.out.println("AES解密结果"+decryptingCode);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void desDemo(){
        try
        {
            String md5Password = "syhhhhhh";
            /** 加密、解密key. */
            String PASSWORD_CRYPT_KEY = "shenyong";
            String str = DESUtil.encrypt(md5Password,PASSWORD_CRYPT_KEY);
            System.out.println("加密后: " + str);
            str = DESUtil.decrypt(str,PASSWORD_CRYPT_KEY);
            System.out.println("解密数据: " + str);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
