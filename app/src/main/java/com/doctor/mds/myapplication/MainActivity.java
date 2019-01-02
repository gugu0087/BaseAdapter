package com.doctor.mds.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerEmptyView recycleView;
    private List<DataModel> dataList = new ArrayList<>();
    private DataAdapter itemAdapter;
    private View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = (RecyclerEmptyView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        itemAdapter = new DataAdapter(this, dataList);
        recycleView.setAdapter(itemAdapter);
        initData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataList.clear();
                itemAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            DataModel dataModel = new DataModel();
            dataModel.setName("hah" + i);
            dataList.add(dataModel);
        }
        itemAdapter.notifyDataSetChanged();
    }
}
