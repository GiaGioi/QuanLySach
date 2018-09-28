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
            ")" ;

}
