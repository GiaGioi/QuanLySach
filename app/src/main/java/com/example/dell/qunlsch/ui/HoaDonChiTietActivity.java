package com.example.dell.qunlsch.ui;

import android.app.AlertDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.adapter.BookSpinnerAdapter;
import com.example.dell.qunlsch.adapter.HoaDonChiTietAdapter;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.model.Bill;
import com.example.dell.qunlsch.model.BillDetail;
import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.model.Book;
import com.example.dell.qunlsch.model.HoaDon;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;
import com.example.dell.qunlsch.sqlitedao.BillDetailDAO;
import com.example.dell.qunlsch.sqlitedao.BookDAO;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity implements OnDelete<BillDetail>, Constant {
    Toolbar toolbarHoaDonChiTiet;
    FloatingActionButton floatingActionButton;
    RecyclerView rvHoaDonChiTiet;
    private List<BillDetail> hoaDonList;
    private HoaDonChiTietAdapter adapter;

    private String billID;


    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        databaseHelper = new DatabaseHelper(this);

        BillDetailDAO billDetailDAO = new BillDetailDAO(databaseHelper);

        // lay bill id tu man hinh Bill
        billID = getIntent().getStringExtra(B_ID);

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

        hoaDonList = billDetailDAO.getAllBillDetailByBillID(billID);

        adapter = new HoaDonChiTietAdapter(hoaDonList, this);
        rvHoaDonChiTiet.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvHoaDonChiTiet.setLayoutManager(manager);
    }

    public void showDialogHoaDonChitiet() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_hoadonchitiet, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();

        final Spinner spBookID;
        final EditText edtQuality;

        spBookID = dialogView.findViewById(R.id.spBookID);
        edtQuality = dialogView.findViewById(R.id.edtQuality);

        List<Book> books = new BookDAO(databaseHelper).getAllBooks();
        spBookID.setAdapter(new BookSpinnerAdapter(this, books));

        Button them = dialogView.findViewById(R.id.btnThem_HoaDonChiTiet);
        Button huy = dialogView.findViewById(R.id.btnHuy_HoaDonChiTiet);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BillDetail billDetail = new BillDetail();
                billDetail.billID = HoaDonChiTietActivity.this.billID;
                billDetail.bookID = ((Book) spBookID.getSelectedItem()).id;
                billDetail.quality = Integer.parseInt(edtQuality.getText().toString().trim());

                BillDetailDAO billDetailDAO = new BillDetailDAO(databaseHelper);
                long result = billDetailDAO.insertBillDetail(billDetail);

                if (result > 0) {
                    hoaDonList.add(billDetail);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Them chi tiet hoa don thanh cong!!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Them chi tiet hoa don KHONG thanh cong!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
    }

    @Override
    public void OnDelete(BillDetail billDetail) {
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
