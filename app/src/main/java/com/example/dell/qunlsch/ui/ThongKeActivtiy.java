package com.example.dell.qunlsch.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;
import com.example.dell.qunlsch.sqlitedao.StatisticsDAO;

public class ThongKeActivtiy extends AppCompatActivity {
    Toolbar toolbarThongKe;
    private TextView tvDoanhThuNgay;
    private TextView tvDoanhThuThang;
    private TextView tvDoanhThuNam;


    private DatabaseHelper databaseHelper;
    private StatisticsDAO statisticsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_activtiy);
        initView();
        databaseHelper = new DatabaseHelper(this);
        statisticsDAO = new StatisticsDAO(databaseHelper);



    }


    private void initView() {
        toolbarThongKe = findViewById(R.id.toolbarThongKe);
        setSupportActionBar(toolbarThongKe);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarThongKe.setTitleTextColor(Color.WHITE);
        toolbarThongKe.setTitle("Thống kê");
        toolbarThongKe.setNavigationIcon(R.drawable.undo);

        toolbarThongKe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvDoanhThuNgay = findViewById(R.id.tvDoanhThuNgay);
        tvDoanhThuThang = findViewById(R.id.tvDoanhThuThang);
        tvDoanhThuNam = findViewById(R.id.tvDoanhThuNam);

    }
}
