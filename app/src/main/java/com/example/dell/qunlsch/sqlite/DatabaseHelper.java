package com.example.dell.qunlsch.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.qunlsch.Constant;


public class DatabaseHelper extends SQLiteOpenHelper implements Constant {


    public DatabaseHelper(Context context) {
        super(context, "BookManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create User Table
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_TYPE_TABLE);
        sqLiteDatabase.execSQL(CREATE_BILL_TABLE);
        sqLiteDatabase.execSQL(CREATE_BILL_DETAIL_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);


        if (Constant.isDEBUG) Log.e("CREATE_USER_TABLE", CREATE_USER_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_TYPE_TABLE", CREATE_TYPE_TABLE);
        if (Constant.isDEBUG) Log.e("CREATE_BILL_TABLE", CREATE_BILL_TABLE);

        if (Constant.isDEBUG) Log.e("CREATE_BILL_TABLE", CREATE_BOOK_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TYPE_BOOK_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL_DETAIL);

        onCreate(sqLiteDatabase);
    }
}
