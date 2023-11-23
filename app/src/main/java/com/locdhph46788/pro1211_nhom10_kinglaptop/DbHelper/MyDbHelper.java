package com.locdhph46788.pro1211_nhom10_kinglaptop.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    static String DB_NAME = "king_laptop";
    static int DB_VERSION = 1;

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_tb_nguoidung = "CREATE TABLE NguoiDung (taiKhoan TEXT PRIMARY KEY, matKhau TEXT NOT NULL, email TEXT NOT NULL, loai INTEGER NOT NULL);";
        db.execSQL(sql_tb_nguoidung);


        String data_tb_nguoidung = "INSERT INTO NguoiDung VALUES('admin', '1', 'admin@gmail.com',0)";
        db.execSQL(data_tb_nguoidung);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE NguoiDung");
            onCreate(db);
        }

    }
}
