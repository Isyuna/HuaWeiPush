package com.mcp.zhyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mcp.zhyproject.R;
import com.mcp.zhyproject.ZYRecyclerView.ZYRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewActivity extends AppCompatActivity {

    ZYRecyclerView zyRecyclerView;

    RecyclerView recyclerView;

    ZYAdapter zyAdapter;

    List<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        zyRecyclerView = findViewById(R.id.zy_recycler);
        recyclerView = findViewById(R.id.recycler_view);

        zyAdapter = new ZYAdapter(getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(zyAdapter);

    }

    private List<String> getData() {
        for(int i = 0 ; i< 100;i++){
            data.add("第"+i+"个");
        }
        return data;
    }
}