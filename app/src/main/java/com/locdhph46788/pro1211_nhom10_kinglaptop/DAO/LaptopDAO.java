package com.locdhph46788.pro1211_nhom10_kinglaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DbHelper.MyDbHelper;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;

import java.util.ArrayList;
import java.util.List;

public class LaptopDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase db;

    public LaptopDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long addLaptop(Laptop objLaptop) {
        ContentValues values = new ContentValues();
        values.put("tenLaptop", objLaptop.getTenLaptop());
        values.put("thuongHieu", objLaptop.getThuongHieu());
        values.put("namSanXuat", objLaptop.getNamSanXuat());
        values.put("giaBan", objLaptop.getGiaBan());
        values.put("moTa", objLaptop.getMoTa());
        return db.insert("Laptop", null, values);
    }

    public boolean deleteLaptop(int idLaptop) {
        int row = db.delete("Laptop", "idLaptop= ?", new String[]{String.valueOf(idLaptop)});
        return row != -1;
    }

    public int updateLaptop(Laptop objLaptop) {
        ContentValues values = new ContentValues();
        values.put("tenLaptop", objLaptop.getTenLaptop());
        values.put("thuongHieu", objLaptop.getThuongHieu());
        values.put("namSanXuat", objLaptop.getNamSanXuat());
        values.put("giaBan", objLaptop.getGiaBan());
        values.put("moTa", objLaptop.getMoTa());
        String[] dieukien = new String[]{String.valueOf(objLaptop.getIdLaptop())};
        return db.update("Laptop", values, "idLaptop=?", dieukien);
    }

    public List<Laptop> getTop10Laptop() {
        List<Laptop> listTop10Laptop = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT PM.MaSach, SC.TenSach, COUNT(PM.MaSach) FROM PhieuMuon PM, Sach SC WHERE PM.MaSach = SC.MaSach GROUP BY PM.MaSach, SC.TenSach  ORDER BY COUNT(PM.MaSach) DESC LIMIT 10", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int idLaptop = c.getInt(0);
                String tenLaptop = c.getString(1);
                String thuongHieu = c.getString(2);
                int namSanXuat = c.getInt(3);
                int giaBan = c.getInt(4);
                String moTa = c.getString(5);

                Laptop objLaptop = new Laptop(idLaptop, tenLaptop, thuongHieu, namSanXuat, giaBan, moTa);
                listTop10Laptop.add(objLaptop);
            } while (c.moveToNext());
        }
        return listTop10Laptop;
    }

    public List<Laptop> getAllLaptop() {
        List<Laptop> listLaptop = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Laptop", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int idLaptop = c.getInt(0);
                String tenLaptop = c.getString(1);
                String thuongHieu = c.getString(2);
                int namSanXuat = c.getInt(3);
                int giaBan = c.getInt(4);
                String moTa = c.getString(5);

                Laptop objLaptop = new Laptop(idLaptop, tenLaptop, thuongHieu, namSanXuat, giaBan, moTa);
                listLaptop.add(objLaptop);
            } while (c.moveToNext());
        }
        return listLaptop;
    }
}
