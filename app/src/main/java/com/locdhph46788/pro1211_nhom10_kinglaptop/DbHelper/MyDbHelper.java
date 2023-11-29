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
        String sql_tb_thongtinnguoidung = "CREATE TABLE ThongTinNguoiDung (idNguoiDung INTEGER PRIMARY KEY AUTOINCREMENT, taiKhoan TEXT REFERENCES NguoiDung(taiKhoan),hoTen TEXT , sDT TEXT, diaChi TEXT);";
        db.execSQL(sql_tb_thongtinnguoidung);
        String sql_tb_laptop = "CREATE TABLE Laptop (idLaptop INTEGER PRIMARY KEY AUTOINCREMENT, tenLaptop TEXT NOT NULL, thuongHieu TEXT NOT NULL,namSanXuat INTEGER NOT NULL,giaBan INTEGER NOT NULL,moTa TEXT NOT NULL);";
        db.execSQL(sql_tb_laptop);
        String sql_tb_giohang = "CREATE TABLE GioHang (idHang INTEGER PRIMARY KEY AUTOINCREMENT, taiKhoan TEXT REFERENCES NguoiDung(taiKhoan),thuongHieu TEXT REFERENCES Laptop(thuongHieu),tenLaptop TEXT REFERENCES Laptop(tenLaptop),idLaptop int REFERENCES Laptop(idLaptop),namSanXuat INTEGER REFERENCES Laptop(namSanXuat),giaBan INTEGER REFERENCES Laptop(giaBan),soLuong INTEGER NOT NULL,tongGia INTEGER NOT NULL);";
        db.execSQL(sql_tb_giohang);
        String sql_tb_donhang = "CREATE TABLE DonHang (idDonHang INTEGER PRIMARY KEY AUTOINCREMENT, taiKhoan TEXT REFERENCES NguoiDung(taiKhoan), idLaptop INTEGER REFERENCES Laptop(idLaptop), hoTen TEXT REFERENCES ThongTinNguoiDung(hoTen), sDT TEXT REFERENCES ThongTinNguoiDung(sDT), diaChi TEXT REFERENCES ThongTinNguoiDung(diaChi),soLuong INTEGER REFERENCES GioHang(soLuong),thanhTien INTEGER NOT NULL,trangThai INTEGER NOT NULL);";
        db.execSQL(sql_tb_donhang);
        String data_tb_nguoidung = "INSERT INTO NguoiDung VALUES('admin', '1', 'admin',0)";
        db.execSQL(data_tb_nguoidung);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE NguoiDung");
            db.execSQL("DROP TABLE ThongTinNguoiDung");
            db.execSQL("DROP TABLE Laptop");
            db.execSQL("DROP TABLE GioHang");
            db.execSQL("DROP TABLE DonHang");
            onCreate(db);
        }

    }
}
