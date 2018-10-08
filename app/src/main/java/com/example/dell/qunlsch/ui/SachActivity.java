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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.qunlsch.adapter.SachAdapter;
import com.example.dell.qunlsch.adapter.TypeBookSpinnerAdapter;
import com.example.dell.qunlsch.listener.OnDelete;
import com.example.dell.qunlsch.listener.OnEdit;
import com.example.dell.qunlsch.model.Book;

import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.model.TypeBook;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;
import com.example.dell.qunlsch.sqlitedao.BookDAO;
import com.example.dell.qunlsch.sqlitedao.TypeBookDAO;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity implements OnEdit<Book>, OnDelete<Book> {
    Toolbar toolbarSach;
    FloatingActionButton floatingActionButton;
    RecyclerView rvSach;
    private List<Book> bookList;
    private SachAdapter adapter;

    private DatabaseHelper databaseHelper;
    private BookDAO bookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);

        databaseHelper = new DatabaseHelper(this);
        bookDAO = new BookDAO(databaseHelper);

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
        bookList = new ArrayList<>();

        bookList = bookDAO.getAllBooks();

        adapter = new SachAdapter(bookList, this, this);
        rvSach.setAdapter(adapter);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvSach.setLayoutManager(manager);


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
                showDialogSearchSach();
                break;
        }
        return false;
    }

    public void showDialogThemSach() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_themsach, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();


        Button them = dialogView.findViewById(R.id.btnThem_ThemSach);
        Button huy = dialogView.findViewById(R.id.btnHuy_ThemSach);

        final EditText edMaSachThemSach;
        EditText edTenSachThemSach;
        final Spinner spTheLoaiThemSach;
        final EditText edTacGiaThemSach;
        final EditText edNXBThemSach;
        final EditText edGiaThemSach;
        final EditText edSoluongThemSach;
        Button btnThemThemSach;
        Button btnHuyThemSach;

        edMaSachThemSach = dialogView.findViewById(R.id.edMaSach_ThemSach);
        edTenSachThemSach = dialogView.findViewById(R.id.edTenSach_ThemSach);
        spTheLoaiThemSach = dialogView.findViewById(R.id.spTheLoai_ThemSach);
        edTacGiaThemSach = dialogView.findViewById(R.id.edTacGia_ThemSach);
        edNXBThemSach = dialogView.findViewById(R.id.edNXB_ThemSach);
        edGiaThemSach = dialogView.findViewById(R.id.edGia_ThemSach);
        edSoluongThemSach = dialogView.findViewById(R.id.edSoluong_ThemSach);
        btnThemThemSach = dialogView.findViewById(R.id.btnThem_ThemSach);
        btnHuyThemSach = dialogView.findViewById(R.id.btnHuy_ThemSach);

        List<TypeBook> typeBooks = new TypeBookDAO(databaseHelper).getAllTypeBooks();
        Log.e("SIZE", typeBooks.size() + "");
        spTheLoaiThemSach.setAdapter(new TypeBookSpinnerAdapter(this, typeBooks));


        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Book book = new Book();
                book.id = edMaSachThemSach.getText().toString().trim();
                book.author = edTacGiaThemSach.getText().toString().trim();
                book.producer = edNXBThemSach.getText().toString().trim();
                book.price = Long.parseLong(edGiaThemSach.getText().toString().trim());
                book.quality = Integer.parseInt(edSoluongThemSach.getText().toString().trim());
                book.typeID = ((TypeBook) spTheLoaiThemSach.getSelectedItem()).id;

                long result = bookDAO.insertBook(book);

                if (result > 0) {
                    bookList.add(book);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(SachActivity.this, "Them Sach Thanh Cong!!!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SachActivity.this, "Them Sach KHONG Thanh Cong!!!!", Toast.LENGTH_LONG).show();

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

    public void showDialogSearchSach() {
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
    public void OnDelete(Book book) {
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
    public void OnEdit(Book book) {
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
