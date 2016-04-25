package com.util.yongheshen.myutil.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.util.yongheshen.myutil.R;

import java.util.Date;

/**
 * Created by Diablo on 16/4/22.
 * 设置exported="true" 才可以在其他APP中访问
 */
public class ProviderAct extends Activity implements View.OnClickListener{

    Uri uri;
    //uri组成content://+ AUTHORITY + /表名
    String AUTHORITY="content://com.util.yongheshen.myutil.provider/teacher";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider);
        uri=uri.parse(AUTHORITY);
        initViews();
    }

    private void initViews(){
        Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(this);
        Button delete = (Button) findViewById(R.id.btn_delete);
        delete.setOnClickListener(this);
        Button update = (Button) findViewById(R.id.btn_update);
        update.setOnClickListener(this);
        Button query = (Button) findViewById(R.id.btn_query);
        query.setOnClickListener(this);
        Button call = (Button) findViewById(R.id.btn_toact);
        call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentResolver cr = getContentResolver();
        ContentValues cv = new ContentValues();
        switch (v.getId()){
            case R.id.btn_add:
                cv.put("title", "jiaoshou");
                cv.put("name", "jiaoshi");
                cv.put("sex", true);
                Uri uri2 = cr.insert(uri, cv);
                System.out.println(uri2.toString());
                break;
            case R.id.btn_delete:
                cr.delete(uri, "_ID=?", new String[]{"2"});
                break;
            case R.id.btn_update:
                cv.put("name", "huangbiao");
                cv.put("date_added", (new Date()).toString());
                int uri3 = cr.update(uri, cv, "_ID=?", new String[]{"3"});
                System.out.println("updated"+":"+uri3);
                break;
            case R.id.btn_query:
                // 查找id为1的数据
                Cursor c = cr.query(uri, null, null, null, null);
                //这里必须要调用 c.moveToFirst将游标移动到第一条数据,不然会出现index -1 requested , with a size of 1错误；cr.query返回的是一个结果集。
                if (c.moveToFirst() == false) {
                    // 为空的Cursor
                    return;
                }
                while(c.moveToNext()){
                    Log.i("ContentTest", "id="+ c.getInt(0)+ ",name="+ c.getString(1)+ ",title="+ c.getString(2)+ ",sex="+ c.getInt(3));
                };
                c.close();
                break;
            case R.id.btn_toact:
                Intent intent = new Intent();
                intent.setClassName("com.app1.yongheshen.app1", "com.app1.yongheshen.app1.ActTwo");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
