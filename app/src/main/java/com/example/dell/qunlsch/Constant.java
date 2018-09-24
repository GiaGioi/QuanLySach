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

    String CREATE_BOOK_TABLE = "";
    String CREATE_TYPE_TABLE = "";

}
