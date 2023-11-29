package com.locdhph46788.pro1211_nhom10_kinglaptop.Model;

public class ThongTinNguoiDung {

    int idNguoiDung;

    String taiKhoan;

    String hoTen;

    String sDT;

    String diaChi;

    public ThongTinNguoiDung() {
    }

    public ThongTinNguoiDung( String taiKhoan, String hoTen, String sDT, String diaChi) {
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.diaChi = diaChi;
    }
    public ThongTinNguoiDung(int idNguoiDung, String taiKhoan, String hoTen, String sDT, String diaChi) {
        this.idNguoiDung = idNguoiDung;
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.diaChi = diaChi;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
