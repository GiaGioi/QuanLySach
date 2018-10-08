package com.example.dell.qunlsch;

public interface Constant {


    boolean isDEBUG = true;


    // USER TABLE

    String USER_TABLE = "users";
    String COLUMN_USERNAME = "Username";
    String COLUMN_PASSWORD = "Password";
    String COLUMN_NAME = "Name";
    String COLUMN_PHONE_NUMBER = "Phone_number";

    String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" +
            COLUMN_USERNAME + " VARCHAR PRIMARY KEY," +
            COLUMN_PASSWORD + " VARCHAR," +
            COLUMN_NAME + " VARCHAR," +
            COLUMN_PHONE_NUMBER + " VARCHAR" +
            ")";

    // ================


    // BOOK TABLE

    // CREATE TABLE Books (MaSach CHAR(5) PRIMARY KEY NOT NULL,
    // MaTheLoai NCHAR(50),
    // TacGia NVARCHAR(50),
    // NXB NVARCHAR(50),
    // giaBia FLOAT NOT NULL,
    // soLuong INT NOT NULL
    //)

    String CREATE_BOOK_TABLE = "";


    // TYPE BOOK TABLE


    // CREATE TABLE typeBooks (MaTheLoai CHAR(5) PRIMARY KEY NOT NULL,
    // TypeName NVARCHAR(50) NOT NULL,
    // Description NVARCHAR(255),
    // Position INT
    // )

    String TYPE_BOOK_TABLE = "TypeBooks";
    String TB_COLUMN_TYPE_BOOK_ID = "MaTheLoai";
    String TB_COLUMN_TYPE_NAME = "TypeName";
    String TB_COLUMN_DESCRIPTION = "Description";
    String TB_COLUMN_POSITION = "Position";


    String CREATE_TYPE_TABLE = "CREATE TABLE " + TYPE_BOOK_TABLE + "(" +
            "" + TB_COLUMN_TYPE_BOOK_ID + " CHAR(5) PRIMARY KEY NOT NULL," +
            "" + TB_COLUMN_TYPE_NAME + " NVARCHAR(50) NOT NULL," +
            "" + TB_COLUMN_DESCRIPTION + " NVARCHAR(255)," +
            "" + TB_COLUMN_POSITION + " INT" +
            ")";


    // Bill DB


    String TABLE_BILL = "Bill";
    String B_ID = "MaHoaDon";
    String B_DATE = "NgayMua";

    // CREATE TABLE Bill (MaHoaDon NCHAR(7) PRIMARY KEY, NgayMua LONG NOT NULL)

    String CREATE_BILL_TABLE = "CREATE TABLE " + TABLE_BILL + "(" +
            "" + B_ID + " NCHAR(7) PRIMARY KEY," +
            "" + B_DATE + " LONG NOT NULL" +
            ")";


    /// Bill Detail

    /// CREATE TABLE BillDetail (MaHDCT INT PRIMARY KEY AUTOINCREMENT,
    /// MaHoaDon NCHAR(7) FOREIGN KEY NOT NULL,
    // MaSach NCHAR(5) FOREIGN KEY NOT NULL,
    // SoLuongMua INT NOT NULL
    //
    /// )

    String TABLE_BILL_DETAIL = "BillDetail";
    String DETAIL_ID = "MaHDCT";
    String DETAIL_BILL_ID = "MaHoaDon";
    String DETAIL_BOOK_ID = "MaSach";
    String DETAIL_QUALITY = "SoLuongMua";

    String CREATE_BILL_DETAIL_TABLE = "CREATE TABLE " + TABLE_BILL_DETAIL + "(" +
            "" + DETAIL_ID + "  INT PRIMARY KEY AUTOINCREMENT," +
            "" + DETAIL_BILL_ID + " MaHoaDon NCHAR(7) FOREIGN KEY NOT NULL," +
            "" + DETAIL_BOOK_ID + " NCHAR(5) FOREIGN KEY NOT NULL," +
            "" + DETAIL_QUALITY + "  INT NOT NULL" +
            ")";

}
