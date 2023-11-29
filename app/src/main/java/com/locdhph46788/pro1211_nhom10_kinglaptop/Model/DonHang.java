package com.locdhph46788.pro1211_nhom10_kinglaptop.Model;

public class DonHang {
    int idDonHang;
    String taiKhoan;
    int idLaptop;
    String hoTen;
    String sDT;
    String diaChi;
    int soLuong;
    int thanhTien;
    int trangThai;


    public DonHang() {
    }

    public DonHang(int idDonHang, String taiKhoan, int idLaptop, String hoTen, String sDT, String diaChi, int soLuong, int thanhTien, int trangThai) {
        this.idDonHang = idDonHang;
        this.taiKhoan = taiKhoan;
        this.idLaptop = idLaptop;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public int getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(int idLaptop) {
        this.idLaptop = idLaptop;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
