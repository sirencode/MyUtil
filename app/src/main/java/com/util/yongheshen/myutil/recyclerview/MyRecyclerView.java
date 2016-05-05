package com.util.yongheshen.myutil.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.util.yongheshen.myutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diablo on 16/4/22.
 * 强化版的ListView
 * 可动态设置布局，分割线样式，item增加删除动画
 * RecyclerView.ViewHolder
 */
public class MyRecyclerView  extends Activity{

    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyler_view);
        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        initData();
        //设置布局管理器 负责把视图组织成多样的结构（竖直列表，网格，交错网格，等等）
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeAdapter(mDatas,MyRecyclerView.this);
        recyclerView.setAdapter(mAdapter);
        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(MyRecyclerView.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(MyRecyclerView.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }

        });
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }


}
