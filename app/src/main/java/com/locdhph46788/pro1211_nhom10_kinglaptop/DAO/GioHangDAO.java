package com.locdhph46788.pro1211_nhom10_kinglaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DbHelper.MyDbHelper;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;


import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public GioHangDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long addGioHang(GioHang objGioHang) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", objGioHang.getTaiKhoan());
        values.put("thuongHieu", objGioHang.getThuongHieu());
        values.put("tenLaptop", objGioHang.getTenLaptop());
        values.put("idLaptop", objGioHang.getIdLaptop());
        values.put("namSanXuat", objGioHang.getNamSanXuat());
        values.put("giaBan", objGioHang.getGiaBan());
        values.put("soLuong", objGioHang.getSoLuong());
        values.put("tongGia", objGioHang.getTongGia());
        return db.insert("GioHang", null, values);
    }


    public boolean check(int idLaptop, String taiKhoan) {
        String query = "SELECT * FROM GioHang" + " WHERE idLaptop" + " = '" + idLaptop + "'AND taiKhoan" + " = '" + taiKhoan + "'";
        Cursor cursor = db.rawQuery(query, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean deleteGioHang(int idHang) {
        int row = db.delete("GioHang", "idHang= ?", new String[]{String.valueOf(idHang)});
        return row != -1;
    }

    public int updateGioHang(GioHang objGioHang) {
        ContentValues values = new ContentValues();
        values.put("soLuong", objGioHang.getSoLuong());
        String[] dieukien = new String[]{String.valueOf(objGioHang.getIdHang())};
        return db.update("GioHang", values, "idHang=?", dieukien);
    }


    public List<GioHang> getAllGioHang(String taikhoan) {
        List<GioHang> listGioHang = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM GioHang" + " WHERE taiKhoan" + " = '" + taikhoan + "'", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int idHang = c.getInt(0);
                String taiKhoan = c.getString(1);
                String thuongHieu = c.getString(2);
                String tenLaptop = c.getString(3);
                int idLaptop = c.getInt(4);
                int namSanXuat = c.getInt(5);
                int giaBan = c.getInt(6);
                int soLuong = c.getInt(7);
                int tongGia = c.getInt(8);

                GioHang objGioHang = new GioHang(idHang, taiKhoan, thuongHieu, tenLaptop, idLaptop, namSanXuat, giaBan, soLuong, tongGia);
                listGioHang.add(objGioHang);
            } while (c.moveToNext());
        }
        return listGioHang;
    }
}
