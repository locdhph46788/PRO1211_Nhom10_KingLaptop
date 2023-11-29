package com.locdhph46788.pro1211_nhom10_kinglaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DbHelper.MyDbHelper;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.DonHang;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;

import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public DonHangDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long addDonHang(DonHang objDonHang) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", objDonHang.getTaiKhoan());
        values.put("idLaptop", objDonHang.getIdLaptop());
        values.put("hoTen", objDonHang.getHoTen());
        values.put("sDT", objDonHang.getsDT());
        values.put("diaChi", objDonHang.getDiaChi());
        values.put("soLuong", objDonHang.getSoLuong());
        values.put("thanhTien", objDonHang.getThanhTien());
        values.put("trangThai", objDonHang.getTrangThai());
        return db.insert("DonHang", null, values);
    }


    public boolean deleteGioHang(int idDonHang) {
        int row = db.delete("DonHang", "idDonHang= ?", new String[]{String.valueOf(idDonHang)});
        return row != -1;
    }

    public int updateDonHang(DonHang objDonHang) {
        ContentValues values = new ContentValues();
        values.put("taiKhoan", objDonHang.getTaiKhoan());
        values.put("idLaptop", objDonHang.getIdLaptop());
        values.put("hoTen", objDonHang.getHoTen());
        values.put("sDT", objDonHang.getsDT());
        values.put("diaChi", objDonHang.getDiaChi());
        values.put("soLuong", objDonHang.getSoLuong());
        values.put("thanhTien", objDonHang.getThanhTien());
        values.put("trangThai", objDonHang.getTrangThai());
        String[] dieukien = new String[]{String.valueOf(objDonHang.getIdDonHang())};
        return db.update("DonHang", values, "idDonHang=?", dieukien);
    }


    public List<DonHang> getAllDonHangMua(String taikhoan) {
        List<DonHang> listDonHang = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM DonHang" + " WHERE taiKhoan" + " = '" + taikhoan + "'", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int idDonHang = c.getInt(0);
                String taiKhoan = c.getString(1);
                int idLaptop = c.getInt(2);
                String hoTen = c.getString(3);
                String sDT = c.getString(4);
                String diaChi = c.getString(5);
                int soLuong = c.getInt(6);
                int thanhTien = c.getInt(7);
                int trangThai = c.getInt(8);

                DonHang objDonHang = new DonHang(idDonHang, taiKhoan, idLaptop, hoTen, sDT, diaChi, soLuong, thanhTien, trangThai);
                listDonHang.add(objDonHang);
            } while (c.moveToNext());
        }
        return listDonHang;
    }

    public List<DonHang> getAllDonHangBan() {
        List<DonHang> listDonHang = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM DonHang", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int idDonHang = c.getInt(0);
                String taiKhoan = c.getString(1);
                int idLaptop = c.getInt(2);
                String hoTen = c.getString(3);
                String sDT = c.getString(4);
                String diaChi = c.getString(5);
                int soLuong = c.getInt(6);
                int thanhTien = c.getInt(7);
                int trangThai = c.getInt(8);

                DonHang objDonHang = new DonHang(idDonHang, taiKhoan, idLaptop, hoTen, sDT, diaChi, soLuong, thanhTien, trangThai);
                listDonHang.add(objDonHang);
            } while (c.moveToNext());
        }
        return listDonHang;
    }
}
