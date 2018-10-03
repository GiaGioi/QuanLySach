package com.example.dell.qunlsch.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.model.Bill;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BillDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public BillDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public long insertBill(Bill bill) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(B_ID, bill.id);
        contentValues.put(B_DATE, bill.date);

        long result = sqLiteDatabase.insert(TABLE_BILL, null, contentValues);

        sqLiteDatabase.close();
        return result;


    }

    public long updateBill(Bill bill) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(B_DATE, bill.date);

        long result = sqLiteDatabase.update(TABLE_BILL, contentValues,
                B_ID + "=?", new String[]{bill.id});


        sqLiteDatabase.close();
        return result;
    }


    public long deleteBill(String id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long result = sqLiteDatabase.delete(TABLE_BILL, B_ID + "=?", new String[]{id});


        sqLiteDatabase.close();
        return result;

    }


    public List<Bill> getAllBills() {

        List<Bill> bills = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_BILL,
                null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                String id = cursor.getString(cursor.getColumnIndex(B_ID));
                long date = cursor.getLong(cursor.getColumnIndex(B_DATE));

                Bill bill = new Bill(id, date);

                bills.add(bill);


            } while (cursor.moveToNext());
        }

        return bills;

    }


    public Bill getBillByID(String billId) {

        Bill bill = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_BILL, new String[]{B_ID, B_DATE},
                B_ID + "=?",
                new String[]{billId}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            String id = cursor.getString(cursor.getColumnIndex(B_ID));
            long date = cursor.getLong(cursor.getColumnIndex(B_DATE));

            bill = new Bill(id, date);
        }

        return bill;


    }
}
