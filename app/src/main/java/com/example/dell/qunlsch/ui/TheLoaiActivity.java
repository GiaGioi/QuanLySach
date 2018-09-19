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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.dell.qunlsch.adapter.TheLoaiAdapter;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnEdit;
import com.example.dell.qunlsch.model.TheLoai;
import com.example.dell.qunlsch.R;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiActivity extends AppCompatActivity implements OnDelete,OnEdit {

    Toolbar toolbarTheLoai;
    FloatingActionButton floatingActionButton;
    RecyclerView rvTheLoai;
    private List<TheLoai> TheLoaiList;
    private TheLoaiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

        toolbarTheLoai = findViewById(R.id.toolbarTheLoai);
        setSupportActionBar(toolbarTheLoai);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTheLoai.setTitleTextColor(Color.WHITE);
        toolbarTheLoai.setTitle("Thể Loại");
        toolbarTheLoai.setNavigationIcon(R.drawable.undo);
        floatingActionButton = findViewById(R.id.fbtn_TheLoai);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThemTheLoai();
            }
        });

        toolbarTheLoai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvTheLoai = findViewById(R.id.RecyclerView_TheLoai);
        TheLoaiList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            TheLoaiList.add(new TheLoai("Công nghệ thông tin" , "Mã thể loại: CNTT"));

        }
        adapter = new TheLoaiAdapter(TheLoaiList,this,this);
        rvTheLoai.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvTheLoai.setLayoutManager(manager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timkiem_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_item:
                showDialogSearchTheLoai();
                break;
        }
        return false;
    }
    public void showDialogThemTheLoai(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_themtheloai, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button them = dialogView.findViewById(R.id.btnThem_ThemTheLoai);
        Button huy = dialogView.findViewById(R.id.btnHuy_ThemTheLoai);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

    }
    public void showDialogSearchTheLoai(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_searchtheloai, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();

        Button tim = dialogView.findViewById(R.id.btnTimTheLoai);
        Button huy = dialogView.findViewById(R.id.btnHuyTimTheLoai);
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

        builder.setMessage("Bạn có muốn xóa thể loại này không?");
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

    @Override
    public void OnEdit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_edittheloai, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button sua = dialogView.findViewById(R.id.btnSua_EditTheLoai);
        Button huy = dialogView.findViewById(R.id.btnHuy_EditTheLoai);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }
}
