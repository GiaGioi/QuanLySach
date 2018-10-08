package com.example.dell.qunlsch.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.dell.qunlsch.adapter.SachBanChayAdapter;
import com.example.dell.qunlsch.model.Book;
import com.example.dell.qunlsch.R;

import java.util.ArrayList;
import java.util.List;

public class SachBanChayActivity extends AppCompatActivity {
    Toolbar toolbarSachBanChay;
    RecyclerView rvSach;
    private List<Book> bookList;
    private SachBanChayAdapter adapter;
    Spinner spinnerThang;
    String thang[] = new String[]{"Tháng 1","Tháng 2","Tháng 3","Tháng 4","Tháng 5","Tháng 6","Tháng 7",
            "Tháng 8","Tháng 9","Tháng 10","Tháng 11","Tháng 12"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);

        toolbarSachBanChay = findViewById(R.id.toolbarSachBanChay);
        setSupportActionBar(toolbarSachBanChay);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarSachBanChay.setTitleTextColor(Color.WHITE);
        toolbarSachBanChay.setTitle("Top 10 Sách Bán Chạy");
        toolbarSachBanChay.setNavigationIcon(R.drawable.undo);
        spinnerThang = findViewById(R.id.spThang);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, thang);
        spinnerThang.setAdapter(adapter1);

        toolbarSachBanChay.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvSach = findViewById(R.id.RecyclerView_SachBanChay);
        bookList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

        }
        adapter = new SachBanChayAdapter(bookList);
        rvSach.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvSach.setLayoutManager(manager);
    }

}
