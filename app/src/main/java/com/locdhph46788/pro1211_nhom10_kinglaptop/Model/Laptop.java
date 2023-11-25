package com.locdhph46788.pro1211_nhom10_kinglaptop.Model;

public class Laptop {
    int idLaptop;
    String tenLaptop;
    String thuongHieu;
    int namSanXuat;
    int giaBan;
    String moTa;

    public Laptop() {
    }
    public Laptop( String tenLaptop, String thuongHieu, int namSanXuat, int giaBan, String moTa) {
        this.tenLaptop = tenLaptop;
        this.thuongHieu = thuongHieu;
        this.namSanXuat = namSanXuat;
        this.giaBan = giaBan;
        this.moTa = moTa;
    }
    public Laptop(int idLaptop, String tenLaptop, String thuongHieu, int namSanXuat, int giaBan, String moTa) {
        this.idLaptop = idLaptop;
        this.tenLaptop = tenLaptop;
        this.thuongHieu = thuongHieu;
        this.namSanXuat = namSanXuat;
        this.giaBan = giaBan;
        this.moTa = moTa;
    }

    public int getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(int idLaptop) {
        this.idLaptop = idLaptop;
    }

    public String getTenLaptop() {
        return tenLaptop;
    }

    public void setTenLaptop(String tenLaptop) {
        this.tenLaptop = tenLaptop;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
