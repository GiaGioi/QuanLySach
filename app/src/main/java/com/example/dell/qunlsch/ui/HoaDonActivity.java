package com.example.dell.qunlsch.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.dell.qunlsch.adapter.HoaDonAdapter;

import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnClick;
import com.example.dell.qunlsch.model.HoaDon;
import com.example.dell.qunlsch.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity implements OnDelete,OnClick {
    Toolbar toolbarHoaDon;
    RecyclerView rvHoaDon;
    private List<HoaDon> hoaDonList;
    private HoaDonAdapter adapter;
    FloatingActionButton floatingActionButton;
    TextView ngay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        toolbarHoaDon = findViewById(R.id.toolbarHoaDon);
        setSupportActionBar(toolbarHoaDon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarHoaDon.setTitleTextColor(Color.WHITE);
        toolbarHoaDon.setTitle("Hóa Đơn");
        toolbarHoaDon.setNavigationIcon(R.drawable.undo);
        floatingActionButton = findViewById(R.id.fbtn_HoaDon);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHoaDon();
            }
        });

        toolbarHoaDon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvHoaDon = findViewById(R.id.RecyclerView_HoaDon);
        hoaDonList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            hoaDonList.add(new HoaDon("Mã: PT11111", "Ngày: 01-01-2020"));

        }
        adapter = new HoaDonAdapter(hoaDonList, this,this);
        rvHoaDon.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvHoaDon.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timkiem_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_item:
                showDialogSearchHoaDon();
                break;
        }
        return false;
    }

    public void showDialogSearchHoaDon() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_searchhoadon, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button tim = dialogView.findViewById(R.id.btnTimHoaDon);
        Button huy = dialogView.findViewById(R.id.btnHuyTimHoaDon);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }

    @Override
    public void OnDelete() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Bạn có muốn xóa hóa đơn này không?");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void showDialogHoaDon() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_hoadon, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button them = dialogView.findViewById(R.id.btnThem_ThemHoaDon);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                startActivity(new Intent(HoaDonActivity.this,HoaDonChiTietActivity.class));
            }
        });
        Button huy = dialogView.findViewById(R.id.btnHuy_ThemHoaDon);
        Button chon = dialogView.findViewById(R.id.btnChonNgay);
        ngay = dialogView.findViewById(R.id.tvChonNgay);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

    }

    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // thiet lap thong tin cho date picker

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Integer n = year;
                Integer t = month;
                Integer d = dayOfMonth;
                ngay.setText(d.toString()+"-"+t.toString()+"-"+n.toString());
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    @Override
    public void onClick() {
       startActivity(new Intent(this,HoaDonChiTietActivity.class));
    }
}
