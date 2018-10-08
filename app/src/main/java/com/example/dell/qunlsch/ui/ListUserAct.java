package com.example.dell.qunlsch.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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

import com.example.dell.qunlsch.adapter.UserAdapter;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnEdit;
import com.example.dell.qunlsch.model.User;
import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;
import com.example.dell.qunlsch.sqlitedao.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class ListUserAct extends AppCompatActivity implements OnEdit<User>, OnDelete<User> {
    Toolbar toolbarNguoiDung;
    RecyclerView rvNguoiDung;
    private List<User> userList;
    private UserAdapter adapter;

    private DatabaseHelper databaseHelper;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidung);

        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);

        toolbarNguoiDung = findViewById(R.id.toolbarNguoiDung);
        setSupportActionBar(toolbarNguoiDung);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarNguoiDung.setTitleTextColor(Color.WHITE);
        toolbarNguoiDung.setTitle(getString(R.string.title_list_user_act));
        toolbarNguoiDung.setNavigationIcon(R.drawable.undo);

        toolbarNguoiDung.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvNguoiDung = findViewById(R.id.RecyclerView_NguoiDung);

        userList = userDAO.getAllUsers();

        adapter = new UserAdapter(userList, this, this);
        rvNguoiDung.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvNguoiDung.setLayoutManager(manager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNguoiDung:
                showDialogThemNguoiDung();
                break;
            case R.id.doiMatKhau:
                showDialogDoiMatKhau();
                break;

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nguoidung_menu, menu);
        return true;
    }

    public void showDialogThemNguoiDung() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_themnguoidung, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button them = dialogView.findViewById(R.id.btnThem_ThemNguoiDung);
        Button huy = dialogView.findViewById(R.id.btnHuy_ThemNguoiDung);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

    }

    public void showDialogDoiMatKhau() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_doimatkhau, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button doi = dialogView.findViewById(R.id.btnDoi_Doimatkhau);
        Button huy = dialogView.findViewById(R.id.btnHuy_Doimatkhau);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }

    @Override
    public void OnDelete(User user) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Bạn có muốn xóa người dùng này không?");
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
    public void OnEdit(User user) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_editnguoidung, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button sua = dialogView.findViewById(R.id.btnSua_EditNguoiDung);
        Button huy = dialogView.findViewById(R.id.btnHuy_EditNguoiDung);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }
}
