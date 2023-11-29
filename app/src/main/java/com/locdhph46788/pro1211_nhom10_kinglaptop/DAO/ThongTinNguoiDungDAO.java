package com.locdhph46788.pro1211_nhom10_kinglaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DbHelper.MyDbHelper;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.ThongTinNguoiDung;


import java.util.ArrayList;
import java.util.List;

public class ThongTinNguoiDungDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public ThongTinNguoiDungDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public boolean checkTTND(String tk) {
        Cursor c = db.rawQuery("SELECT * FROM ThongTinNguoiDung" + " WHERE taiKhoan" + " = '" + tk + "'", null);
        if (c.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public long addTTND(ThongTinNguoiDung objTTND) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", objTTND.getTaiKhoan());
        values.put("hoTen", objTTND.getHoTen());
        values.put("sDT", objTTND.getsDT());
        values.put("diaChi", objTTND.getDiaChi());

        return db.insert("ThongTinNguoiDung", null, values);
    }

    public int updateTTND(ThongTinNguoiDung objTTND) {
        ContentValues values = new ContentValues();
        values.put("hoTen", objTTND.getHoTen());
        values.put("sDT", objTTND.getsDT());
        values.put("diaChi", objTTND.getDiaChi());
        String[] dieukien = new String[]{String.valueOf(objTTND.getTaiKhoan())};
        return db.update("ThongTinNguoiDung", values, "taiKhoan=?", dieukien);
    }


    public List<ThongTinNguoiDung> getAllTTND() {
        List<ThongTinNguoiDung> listTTND = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM ThongTinNguoiDung", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int idNguoiDung = c.getInt(0);
                String taiKhoan = c.getString(1);
                String hoTen = c.getString(2);
                String sDT = c.getString(3);
                String diaChi = c.getString(4);

                ThongTinNguoiDung objTTND = new ThongTinNguoiDung(idNguoiDung, taiKhoan, hoTen, sDT, diaChi);
                listTTND.add(objTTND);
            } while (c.moveToNext());
        }
        return listTTND;
    }
}
