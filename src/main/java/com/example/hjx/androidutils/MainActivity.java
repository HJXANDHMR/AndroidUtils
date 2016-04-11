package com.example.hjx.androidutils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import utils.view.CheckBoxView;


public class MainActivity extends Activity {
    private CheckBoxView checkBoxView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private String[] str = {"a", "b"};
    private ArrayAdapter<String> adapter ;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        checkBoxView = (CheckBoxView) findViewById(R.id.check1);
        listView = (ListView) findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
    }

    private void initEvent() {
        adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        count++;
                        str[0]="a"+count;
                        str[1]="b"+count;
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
        checkBoxView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxView.toggle();
            }
        });
    }


}
