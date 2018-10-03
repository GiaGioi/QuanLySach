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

import com.example.dell.qunlsch.adapter.SachAdapter;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnEdit;
import com.example.dell.qunlsch.model.Sach;

import com.example.dell.qunlsch.R;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity implements OnEdit,OnDelete{
    Toolbar toolbarSach;
    FloatingActionButton floatingActionButton;
    RecyclerView rvSach;
    private List<Sach> sachList;
    private SachAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);

        toolbarSach = findViewById(R.id.toolbarSach);
        setSupportActionBar(toolbarSach);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarSach.setTitleTextColor(Color.WHITE);
        toolbarSach.setTitle("Sách");
        toolbarSach.setNavigationIcon(R.drawable.undo);

        toolbarSach.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        floatingActionButton = findViewById(R.id.fbtn_Sach);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThemSach();
            }
        });

        rvSach = findViewById(R.id.RecyclerView_Sach);
        sachList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            sachList.add(new Sach("Tin Học Cơ Sở" , "Số lượng:10","Mã sách: THCS"));

        }
        adapter = new SachAdapter(sachList,this,this);
        rvSach.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvSach.setLayoutManager(manager);
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
                showDialogSearchSach();
                break;
        }
        return false;
    }
    public void showDialogThemSach(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_themsach, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button them = dialogView.findViewById(R.id.btnThem_ThemSach);
        Button huy = dialogView.findViewById(R.id.btnHuy_ThemSach);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }
    public void showDialogSearchSach(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_searchsach, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();

        Button tim = dialogView.findViewById(R.id.btnTimSach);
        Button huy = dialogView.findViewById(R.id.btnHuyTimSach);
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

        builder.setMessage("Bạn có muốn xóa sách này không?");
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
        View dialogView = inflater.inflate(R.layout.dialog_editsach, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button sua = dialogView.findViewById(R.id.btnSua_EditSach);
        Button huy = dialogView.findViewById(R.id.btnHuy_EditSach);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }
}
