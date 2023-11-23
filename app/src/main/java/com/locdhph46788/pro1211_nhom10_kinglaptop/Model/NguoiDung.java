package com.locdhph46788.pro1211_nhom10_kinglaptop.Model;

public class NguoiDung {
    String taiKhoan;

    String matKhau;

    String email;

    int loai;

    public NguoiDung() {
    }

    public NguoiDung(String taiKhoan, String matKhau, String email, int loai) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.loai = loai;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }
}


