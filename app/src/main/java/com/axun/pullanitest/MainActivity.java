package com.axun.pullanitest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TwinklingRefreshLayout mRefreshLay;
    private ListView mListView;
    private QQHeaderView headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setList();
    }

    private void setList(){
        List<String> data = new ArrayList<>();
        for (int i=0;i<20;i++){
            data.add("item"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(adapter);
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mRefreshLay.finishRefreshing();
        }
    };

    private void initView() {
        mRefreshLay = (TwinklingRefreshLayout) findViewById(R.id.refresh_lay);
        mListView = (ListView) findViewById(R.id.list_view);
        headerView = new QQHeaderView(this);
        mRefreshLay.setHeaderView(headerView);
        mRefreshLay.setOnRefreshListener(new RefreshListenerAdapter() {

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                addData();
            }
        });
    }

    private void addData(){
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
              handler.sendEmptyMessage(0);
           }
       },500);
    }
}
