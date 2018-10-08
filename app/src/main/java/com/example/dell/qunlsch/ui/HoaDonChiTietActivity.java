package com.example.dell.qunlsch.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.dell.qunlsch.adapter.HoaDonChiTietAdapter;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.model.BillDetail;
import com.example.dell.qunlsch.R;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity implements OnDelete{
    Toolbar toolbarHoaDonChiTiet;
    FloatingActionButton floatingActionButton;
    RecyclerView rvHoaDonChiTiet;
    private List<BillDetail> hoaDonList;
    private HoaDonChiTietAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        toolbarHoaDonChiTiet = findViewById(R.id.toolbarHoaDonChiTiet);
        setSupportActionBar(toolbarHoaDonChiTiet);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarHoaDonChiTiet.setTitleTextColor(Color.WHITE);
        toolbarHoaDonChiTiet.setTitle("Hóa Đơn Chi Tiết");
        toolbarHoaDonChiTiet.setNavigationIcon(R.drawable.undo);
        floatingActionButton = findViewById(R.id.fbtn_HoaDonChiTiet);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHoaDonChitiet();
            }
        });
        toolbarHoaDonChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvHoaDonChiTiet = findViewById(R.id.RecyclerView_HoaDonChiTiet);
        hoaDonList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            hoaDonList.add(new BillDetail("Tên: CNTT", "Giá: 20.000 VNĐ","SL: 2","Tổng: 40.000 VNĐ"));

        }
        adapter = new HoaDonChiTietAdapter(hoaDonList, this);
        rvHoaDonChiTiet.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvHoaDonChiTiet.setLayoutManager(manager);
    }
    public void showDialogHoaDonChitiet(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_hoadonchitiet, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button them = dialogView.findViewById(R.id.btnThem_HoaDonChiTiet);
        Button huy = dialogView.findViewById(R.id.btnHuy_HoaDonChiTiet);
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
}
