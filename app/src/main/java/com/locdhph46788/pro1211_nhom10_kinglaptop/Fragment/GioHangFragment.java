package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.locdhph46788.pro1211_nhom10_kinglaptop.Activity.MainActivity;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Activity.ThanhToanActivity;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.GioHangAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.GioHangDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;


public class GioHangFragment extends Fragment {

    RecyclerView rcvGioHang;
    GioHangDAO gioHangDAO;
    GioHangAdapter gioHangAdapter;
    List<GioHang> listGioHang;
    TextView tvGia;
    Button btnThanhToan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_giohang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NguoiDung", getContext().MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("taiKhoan", "");
        rcvGioHang = view.findViewById(R.id.rcv_giohang);
        gioHangDAO = new GioHangDAO(getContext());
        listGioHang = gioHangDAO.getAllGioHang(taiKhoan);
        gioHangAdapter = new GioHangAdapter(getContext(), listGioHang, gioHangDAO);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvGioHang.setLayoutManager(linearLayoutManager);
        rcvGioHang.setAdapter(gioHangAdapter);

        tvGia = view.findViewById(R.id.tv_gia_thanh_toan);
        double gia = gioHangAdapter.tinhGiaThanhToan();
        tvGia.setText((int) gia + "$");
        rcvGioHang.getAdapter().notifyDataSetChanged();

        btnThanhToan = view.findViewById(R.id.btn_thanh_toan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGioHang.size() == 0) {
                    Toast.makeText(getContext(), "Giỏ hàng trống,vui lòng thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), ThanhToanActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}
