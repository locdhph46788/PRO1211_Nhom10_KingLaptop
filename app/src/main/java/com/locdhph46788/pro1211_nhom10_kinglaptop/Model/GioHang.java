package com.locdhph46788.pro1211_nhom10_kinglaptop.Model;

public class GioHang {
    int idHang;
    String taiKhoan;
    String thuongHieu;
    String tenLaptop;
    int idLaptop;
    int namSanXuat;
    int giaBan;
    int soLuong;
    double tongGia;

    public GioHang() {
    }
    public GioHang( int idHang, int soLuong) {
        this.idHang = idHang;
        this.soLuong = soLuong;
    }
    public GioHang(String taiKhoan, String thuongHieu, String tenLaptop, int idLaptop, int namSanXuat, int giaBan, int soLuong, double tongGia) {
        this.taiKhoan = taiKhoan;
        this.thuongHieu = thuongHieu;
        this.tenLaptop = tenLaptop;
        this.idLaptop = idLaptop;
        this.namSanXuat = namSanXuat;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.tongGia = tongGia;
    }

    public GioHang(int idHang, String taiKhoan, String thuongHieu, String tenLaptop, int idLaptop, int namSanXuat, int giaBan, int soLuong, double tongGia) {
        this.idHang = idHang;
        this.taiKhoan = taiKhoan;
        this.thuongHieu = thuongHieu;
        this.tenLaptop = tenLaptop;
        this.idLaptop = idLaptop;
        this.namSanXuat = namSanXuat;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.tongGia = tongGia;
    }

    public int getIdHang() {
        return idHang;
    }

    public void setIdHang(int idHang) {
        this.idHang = idHang;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getTenLaptop() {
        return tenLaptop;
    }

    public void setTenLaptop(String tenLaptop) {
        this.tenLaptop = tenLaptop;
    }

    public int getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(int idLaptop) {
        this.idLaptop = idLaptop;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongGia() {
        tongGia = giaBan * soLuong;
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }
}
