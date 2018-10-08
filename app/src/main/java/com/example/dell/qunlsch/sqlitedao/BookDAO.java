package com.example.dell.qunlsch.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.model.Bill;
import com.example.dell.qunlsch.model.Book;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BookDAO implements Constant {


    private DatabaseHelper databaseHelper;

    public BookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;

    }

    public long insertBook(Book book) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID, book.id);
        contentValues.put(BOOK_TYPE_BOOK_ID, book.typeID);
        contentValues.put(BOOK_AUTHOR, book.author);
        contentValues.put(BOOK_PRICE, book.price);
        contentValues.put(BOOK_PRODUCER, book.producer);
        contentValues.put(BOOK_QUALITY, book.quality);
        long result = sqLiteDatabase.insert(TABLE_BOOK, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateBook(Book book) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_TYPE_BOOK_ID, book.typeID);
        contentValues.put(BOOK_AUTHOR, book.author);
        contentValues.put(BOOK_PRICE, book.price);
        contentValues.put(BOOK_PRODUCER, book.producer);
        contentValues.put(BOOK_QUALITY, book.quality);

        long result = sqLiteDatabase.update(TABLE_BOOK, contentValues,
                BOOK_ID + "=?", new String[]{book.id});
        sqLiteDatabase.close();
        return result;

    }

    public long delBook(String id) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_BILL, BOOK_ID + "=?", new String[]{id});
        sqLiteDatabase.close();
        return result;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_BOOK,
                null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {

                String id = cursor.getString(cursor.getColumnIndex(BOOK_ID));
                long price = cursor.getLong(cursor.getColumnIndex(BOOK_PRICE));
                String typeID = cursor.getString(cursor.getColumnIndex(BOOK_TYPE_BOOK_ID));
                String author = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR));
                String producer = cursor.getString(cursor.getColumnIndex(BOOK_PRODUCER));
                int quality = cursor.getInt(cursor.getColumnIndex(BOOK_QUALITY));
                Book book = new Book();

                book.id = id;
                book.price = price;
                book.typeID = typeID;
                book.author = author;
                book.producer = producer;
                book.quality = quality;
                books.add(book);

            } while (cursor.moveToNext());
        }

        return books;
    }

    public Book getBookByID(String bookId) {
        Book book = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK, new String[]{BOOK_ID, BOOK_TYPE_BOOK_ID, BOOK_AUTHOR, BOOK_PRICE, BOOK_PRODUCER, BOOK_QUALITY},
                BOOK_ID + "=?",
                new String[]{bookId}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            String id = cursor.getString(cursor.getColumnIndex(BOOK_ID));
            long price = cursor.getLong(cursor.getColumnIndex(BOOK_PRICE));
            String typeID = cursor.getString(cursor.getColumnIndex(BOOK_TYPE_BOOK_ID));
            String author = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR));
            String producer = cursor.getString(cursor.getColumnIndex(BOOK_PRODUCER));
            int quality = cursor.getInt(cursor.getColumnIndex(BOOK_QUALITY));

            book = new Book();
            book.id = id;
            book.price = price;
            book.typeID = typeID;
            book.author = author;
            book.producer = producer;
            book.quality = quality;
        }

        return book;
    }
}
