package com.locdhph46788.pro1211_nhom10_kinglaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.locdhph46788.pro1211_nhom10_kinglaptop.DbHelper.MyDbHelper;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public NguoiDungDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long addND(NguoiDung objND) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", objND.getTaiKhoan());
        values.put("matKhau", objND.getMatKhau());
        values.put("email", objND.getEmail());
        values.put("loai", objND.getLoai());

        return db.insert("NguoiDung", null, values);
    }

    public boolean checkLogin(String username, String password) {
        Cursor c = db.rawQuery("SELECT taiKhoan,matKhau FROM NguoiDung " + "WHERE taiKhoan" + " = '" + username + "' AND matKhau" + " = '" + password + "'", null);
        if (c.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkForgot(String usernameForgot, String emailForgot) {
        Cursor c = db.rawQuery("SELECT taiKhoan,email FROM NguoiDung" + " WHERE taiKhoan" + " = '" + usernameForgot + "' AND email" + " = '" + emailForgot + "'", null);
        if (c.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int changePassword(NguoiDung objND) {
        ContentValues values = new ContentValues();
        values.put("matKhau", objND.getMatKhau());
        String[] dieukien = new String[]{String.valueOf(objND.getTaiKhoan())};
        return db.update("NguoiDung", values, "taiKhoan=?", dieukien);
    }

    public List<NguoiDung> getAllND() {
        List<NguoiDung> listND = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM NguoiDung", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do { // thứ tự cột: 0-id, 1- name
                String taiKhoan = c.getString(0);
                String matKhau = c.getString(1);
                String email = c.getString(2);
                int loai = c.getInt(3);

                NguoiDung objND = new NguoiDung(taiKhoan, matKhau, email, loai);
                listND.add(objND);
            } while (c.moveToNext());
        }
        return listND;
    }
}
