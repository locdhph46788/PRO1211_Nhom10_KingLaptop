package com.locdhph46788.pro1211_nhom10_kinglaptop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.NguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.DoanhThuFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.DoiMatKhauFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.GiaiThuongFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.QuanLyDonBanFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.QuanLyDonMuaFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.QuanLyLaptopFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.ThemTaiKhoanFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.ThongTinCaNhanFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.Top10Fragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.TrangChuFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.NguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navView;

    TrangChuFragment fragTC;
    QuanLyDonMuaFragment fragDM;
    GiaiThuongFragment fragGT;
    ThongTinCaNhanFragment fragTTCN;
    QuanLyLaptopFragment fragQLL;
    QuanLyDonBanFragment fragDB;
    ThemTaiKhoanFragment fragTTK;

    Top10Fragment fragT10;
    DoanhThuFragment fragDT;
    DoiMatKhauFragment fragDMK;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.main_nav_view);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle barDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        barDrawerToggle.setDrawerIndicatorEnabled(true);
        barDrawerToggle.syncState();
        drawerLayout.addDrawerListener(barDrawerToggle);
        View customView = LayoutInflater.from(this).inflate(R.layout.giohang_button, null);
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                dpToPx(25),
                dpToPx(25),
                Gravity.END
                );
        params.setMarginEnd(dpToPx(10));
        customView.setLayoutParams(params);
        Drawable buttonBackground = getResources().getDrawable(R.drawable.ic_giohang);
        customView.setBackground(buttonBackground);
        toolbar.addView(customView);
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        fragTC = new TrangChuFragment();
        fragDM = new QuanLyDonMuaFragment();
        fragGT = new GiaiThuongFragment();
        fragTTCN = new ThongTinCaNhanFragment();
        fragQLL = new QuanLyLaptopFragment();
        fragDB = new QuanLyDonBanFragment();
        fragTTK = new ThemTaiKhoanFragment();
        fragT10 = new Top10Fragment();
        fragDT = new DoanhThuFragment();
        fragDMK = new DoiMatKhauFragment();
        fm = getSupportFragmentManager();

        SharedPreferences sharedPreferences = this.getSharedPreferences("NguoiDung", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("taiKhoan", "");

        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        List<NguoiDung> listND = new ArrayList<>();
        listND.addAll(nguoiDungDAO.getAllND());
        int loai = -1;
        for (NguoiDung nd : listND) {
            if (nd.getTaiKhoan().equals(taiKhoan)) {
                loai = nd.getLoai();
                break;
            }
        }
        View headerView = navView.getHeaderView(0);
        TextView navName = (TextView) headerView.findViewById(R.id.tv_name);
        navName.setText(taiKhoan);
        TextView navRole = (TextView) headerView.findViewById(R.id.tv_vaitro);
        navView.getMenu().findItem(R.id.gio_hang).setVisible(false);
        if (loai == 0) {
            fm.beginTransaction().add(R.id.fragment_container_view, fragQLL).commit();
            setTitle("Quản lý laptop");
            navRole.setText("Admin");
            navView.getMenu().findItem(R.id.gio_hang).setVisible(false);
            navView.getMenu().findItem(R.id.trang_chu).setVisible(false);
            navView.getMenu().findItem(R.id.quan_ly_don_mua).setVisible(false);
            navView.getMenu().findItem(R.id.giai_thuong).setVisible(false);
            navView.getMenu().findItem(R.id.thong_tin_ca_nhan).setVisible(false);

        } else if (loai == 1) {
            fm.beginTransaction().add(R.id.fragment_container_view, fragTC).commit();
            setTitle("Trang chủ");
            navRole.setText("Khách hàng");
            navView.getMenu().findItem(R.id.quan_ly_laptop).setVisible(false);
            navView.getMenu().findItem(R.id.quan_ly_don_ban).setVisible(false);
            navView.getMenu().findItem(R.id.them_tai_khoan).setVisible(false);
            navView.getMenu().findItem(R.id.top10).setVisible(false);
            navView.getMenu().findItem(R.id.doanhthu).setVisible(false);

        }


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.trang_chu) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragTC).commit();
                    setTitle("Trang chủ");
                } else if (item.getItemId() == R.id.quan_ly_don_mua) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragDM).commit();
                    setTitle("Quản lý đơn hàng mua");
                } else if (item.getItemId() == R.id.giai_thuong) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragGT).commit();
                    setTitle("Giải thưởng");
                } else if (item.getItemId() == R.id.thong_tin_ca_nhan) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragTTCN).commit();
                    setTitle("Thông tin cá nhân");
                } else if (item.getItemId() == R.id.quan_ly_laptop) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragQLL).commit();
                    setTitle("Quản lý laptop");
                } else if (item.getItemId() == R.id.quan_ly_don_ban) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragDB).commit();
                    setTitle("Quản lý đơn hàng bán");
                } else if (item.getItemId() == R.id.them_tai_khoan) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragTTK).commit();
                    setTitle("Thêm tài khoản");
                } else if (item.getItemId() == R.id.top10) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragT10).commit();
                    setTitle("Thống kê top 10");
                } else if (item.getItemId() == R.id.doanhthu) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragDT).commit();
                    setTitle("Thống kê doanh thu");
                } else if (item.getItemId() == R.id.change_password) {
                    fm.beginTransaction().replace(R.id.fragment_container_view, fragDMK).commit();
                    setTitle("Đổi mật khẩu");
                } else if (item.getItemId() == R.id.logout) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Xác nhận đăng xuất !");
                    alert.setMessage("Bạn có muốn đăng xuất ?");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alert.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Hủy", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }
                drawerLayout.close();
                return true;
            }
        });
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}