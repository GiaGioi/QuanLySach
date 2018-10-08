package com.example.dell.qunlsch.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.model.BillDetail;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO implements Constant {

    DatabaseHelper databaseHelper;

    public BillDetailDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public List<BillDetail> getAllBillDetailByBillID(String billID) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        List<BillDetail> billDetails = new ArrayList<>();

        String SELECT_ALL_BILL_DETAIL_BY_BILL_ID = "SELECT * FROM " + TABLE_BILL_DETAIL +
                " WHERE " + DETAIL_BILL_ID + " = " + "'" + billID + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BILL_DETAIL_BY_BILL_ID, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String id = cursor.getString(cursor.getColumnIndex(DETAIL_ID));
                String book_id = cursor.getString(cursor.getColumnIndex(DETAIL_BOOK_ID));
                String bill_id = cursor.getString(cursor.getColumnIndex(DETAIL_BILL_ID));
                int quality = cursor.getInt(cursor.getColumnIndex(DETAIL_QUALITY));

                BillDetail billDetail = new BillDetail();
                billDetail.id = id;
                billDetail.billID = bill_id;
                billDetail.bookID = book_id;
                billDetail.quality = quality;
                billDetails.add(billDetail);

            } while (cursor.moveToNext());

        }
        sqLiteDatabase.close();
        return billDetails;
    }


    public long insertBillDetail(BillDetail billDetail) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAIL_ID, billDetail.id);
        contentValues.put(DETAIL_BILL_ID, billDetail.billID);
        contentValues.put(DETAIL_BOOK_ID, billDetail.bookID);
        contentValues.put(DETAIL_QUALITY, billDetail.quality);

        long result = sqLiteDatabase.insert(TABLE_BILL_DETAIL, null, contentValues);
        return result;
    }
}
