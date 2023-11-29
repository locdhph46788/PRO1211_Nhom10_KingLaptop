package com.locdhph46788.pro1211_nhom10_kinglaptop.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.DonHangAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.GioHangAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.GioHangDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.ThongTinNguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.ChiTietFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.ThongTinNguoiDungFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.ThongTinNguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;

public class ThanhToanActivity extends AppCompatActivity {
    RecyclerView rcvDonHang;
    GioHangDAO gioHangDAO;
    DonHangAdapter donHangAdapter;
    GioHangAdapter gioHangAdapter;
    List<GioHang> listGioHang;

    TextView tvHoTen, tvSDT, tvDiaChi, tvGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        SharedPreferences nguoiDung = this.getSharedPreferences("NguoiDung", this.MODE_PRIVATE);
        String taiKhoan = nguoiDung.getString("taiKhoan", "");
        rcvDonHang = findViewById(R.id.rcv_donhang);
        gioHangDAO = new GioHangDAO(this);
        listGioHang = gioHangDAO.getAllGioHang(taiKhoan);
        donHangAdapter = new DonHangAdapter(this, listGioHang, gioHangDAO);
        gioHangAdapter = new GioHangAdapter(this, listGioHang, gioHangDAO);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThanhToanActivity.this, LinearLayoutManager.VERTICAL, false);
        rcvDonHang.setLayoutManager(linearLayoutManager);
        rcvDonHang.setAdapter(donHangAdapter);

        tvHoTen = findViewById(R.id.tv_ho_ten);
        tvSDT = findViewById(R.id.tv_sdt);
        tvDiaChi = findViewById(R.id.tv_dia_chi);

        tvGia = findViewById(R.id.tv_gia_thanh_toan);
        double gia = gioHangAdapter.tinhGiaThanhToan();
        tvGia.setText((int) gia + "$");

        SharedPreferences thongTinGiaoHang = getSharedPreferences("ThongTinGiaoHang", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = thongTinGiaoHang.edit();
        String hoTen = thongTinGiaoHang.getString("hoTen", "");
        String sDT = thongTinGiaoHang.getString("sDT", "");
        String diaChi = thongTinGiaoHang.getString("diaChi", "");
        if (hoTen.equals("") || sDT.equals("") || diaChi.equals("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Bạn chưa có thông tin giao hàng !");
            alert.setMessage("Vui lòng cập nhật thông tin cá nhân !");
            alert.setCancelable(false);
            alert.setPositiveButton("Trở lại", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    onBackPressed();
                }
            });

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        } else {
            tvHoTen.setText(hoTen);
            tvSDT.setText(sDT);
            tvDiaChi.setText(diaChi);
        }
        editor.commit();
    }
}