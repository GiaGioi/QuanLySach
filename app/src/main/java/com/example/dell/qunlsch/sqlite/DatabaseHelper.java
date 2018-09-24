package com.example.dell.qunlsch.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant {



    public DatabaseHelper(Context context) {
        super(context, "BookManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create User Table
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
        sqLiteDatabase.execSQL(CREATE_TYPE_TABLE);


        if (Constant.isDEBUG) Log.e("CREATE_USER_TABLE", CREATE_USER_TABLE);

    }


    public String getName() {


        return "Hello";
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        onCreate(sqLiteDatabase);
    }
}
