package com.example.dell.qunlsch.sqlitedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.model.Bill;
import com.example.dell.qunlsch.model.BillDetail;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public StatisticsDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long getStatisticsByDayCach1(long day) {
        long result = -1;
        List<Bill> bills = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_ = "SELECT * FROM " + TABLE_BILL;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(B_ID));
                    long date = cursor.getLong(cursor.getColumnIndex(B_DATE));

                    Bill bill = new Bill(id, date);

                    bills.add(bill);

                } while (cursor.moveToNext());

            }
        }

        // loai bo cac ngay
        for (int i = 0; i < bills.size(); i++) {

            long date = bills.get(i).date;
            if (date != day) {
                bills.remove(i);
            }

        }

        List<BillDetail> billDetails = new ArrayList<>();
        for (int i = 0; i < bills.size(); i++) {
            List<BillDetail> billDetails_ =
                    new BillDetailDAO(databaseHelper).getAllBillDetailByBillID(bills.get(i).id);

            // lay toan bo danh sach Bill Detail theo Bill ID
            billDetails.addAll(billDetails_);

        }

        for (int i = 0; i < billDetails.size(); i++) {

            int quality = billDetails.get(i).quality;
            long price = new BookDAO(databaseHelper).getBookByID(billDetails.get(i).bookID).price;

            long sum_ = quality * price;

            result = result + sum_;

        }

        return result;
    }

    // example day = 2018-10-09   YY-MM-DD

    public long getStatisticsByDayCach2(String day) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_STATISTICS_BY_DAY = "";

        return result;

    }


    // format month : %Y-%m

    public long getStatisticsByMonth(String month) {
        long result = -1;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        String SELECT_STATISTICS = "SELECT * FROM " + TABLE_BILL + " WHERE strftime('%Y-%m', " + B_DATE + "/ 1000, 'unixepoch')  = '" + month + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_STATISTICS, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Log.e("SIZE", cursor.getCount() + "");
                cursor.moveToFirst();
                do {
                    String text = cursor.getString(0);

                    Log.e("text",
                            text);

                } while (cursor.moveToNext());
            } else {
                Log.e("SIZE=0", "000");
            }
        }

        return result;
    }


    // example year = "2018"

    public long getStatisticsByYear(String year) {
        long result = -1;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        String SELECT_STATISTICS = "SELECT * FROM " + TABLE_BILL + " WHERE strftime('%Y', " + B_DATE + "/ 1000, 'unixepoch')  = '" + year + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_STATISTICS, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Log.e("SIZE", cursor.getCount() + "");
                cursor.moveToFirst();
                do {
                    String text = cursor.getString(0);

                    Log.e("text",
                            text);

                } while (cursor.moveToNext());
            } else {
                Log.e("SIZE=0", "000");
            }
        }


        return result;
    }

}
