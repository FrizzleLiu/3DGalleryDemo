package com.bubu.a3dgallerydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("http://img.d2c.cn/2018/04/13/055719886e0e44e7e6bf3f95eb80dc888e71ce.jpg");
        strings.add("http://img.d2c.cn/2018/04/04/090731adf471f7cede663d446082a721606777.jpg");
        strings.add("http://img.d2c.cn/2017/09/23/044820323632cb6f87d05242dc6a79a1206faa.jpg");
        strings.add("http://img.d2c.cn/2018/03/21/0832286789d5df26025be2544401ac72b9daef.png");
        strings.add("http://img.d2c.cn/2018/03/21/08312989dbb198ca64d3b3bd7e01ebe580e647.png");
        strings.add("http://img.d2c.cn/2018/03/19/1101364f13d1f42fada5588a8cd0109f589710.jpg");
        MainAdapter mainAdapter = new MainAdapter(this,strings);
        recyclerView.setAdapter(mainAdapter);
    }
}
