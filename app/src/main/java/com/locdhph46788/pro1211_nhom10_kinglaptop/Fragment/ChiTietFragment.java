package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.GioHangAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.GioHangDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.ArrayList;
import java.util.List;


public class ChiTietFragment extends Fragment {
    ImageView iVLaptop;
    TextView tvThuongHieu, tvTenLaptop, tvNamSanXuat, tvGiaBan, tvMoTa;
    Button btnThemGioHang;
    ImageButton btnBack;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iVLaptop = view.findViewById(R.id.iv_laptop);
        tvThuongHieu = view.findViewById(R.id.tv_thuong_hieu);
        tvTenLaptop = view.findViewById(R.id.tv_ten_laptop);
        tvNamSanXuat = view.findViewById(R.id.tv_nam_san_xuat);
        tvGiaBan = view.findViewById(R.id.tv_gia_ban);
        tvMoTa = view.findViewById(R.id.tv_mo_ta);
        btnBack = view.findViewById(R.id.btn_back);
        btnThemGioHang = view.findViewById(R.id.btn_them_gio_hang);

        SharedPreferences chiTietLaptop = getContext().getSharedPreferences("ChiTietLaptop", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editorchiTietLaptop = chiTietLaptop.edit();
        int idLaptop = chiTietLaptop.getInt("idLaptop", 0);


        if (chiTietLaptop != null) {
            String thuongHieu = chiTietLaptop.getString("thuongHieu", "");
            String tenLaptop = chiTietLaptop.getString("tenLaptop", "");
            int namSanXuat = chiTietLaptop.getInt("namSanXuat", 0);
            int giaBan = chiTietLaptop.getInt("giaBan", 0);
            String moTa = chiTietLaptop.getString("moTa", "");

            tvThuongHieu.setText(thuongHieu);
            tvTenLaptop.setText(tenLaptop);
            tvNamSanXuat.setText(namSanXuat + "");
            tvGiaBan.setText(giaBan + "");
            tvMoTa.setText(moTa);
        }
        editorchiTietLaptop.clear();
        editorchiTietLaptop.commit();

        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("NguoiDung", getContext().MODE_PRIVATE);
                String taiKhoan = sharedPreferences.getString("taiKhoan", "");
                String thuongHieu = tvThuongHieu.getText().toString();
                String tenLaptop = tvTenLaptop.getText().toString();
                String namSanXuat = tvNamSanXuat.getText().toString();
                String giaBan = tvGiaBan.getText().toString();
                int soLuong = 1;
                GioHang objGioHang = new GioHang(taiKhoan, thuongHieu, tenLaptop, idLaptop, Integer.parseInt(namSanXuat), Integer.parseInt(giaBan), soLuong, Integer.parseInt(giaBan));
                GioHangDAO gioHangDAO = new GioHangDAO(getContext());
                List<GioHang> listGioHang = gioHangDAO.getAllGioHang(taiKhoan);
                listGioHang.clear();
                listGioHang.addAll(gioHangDAO.getAllGioHang(taiKhoan));
                if (gioHangDAO.check(idLaptop, taiKhoan) == true) {
                    Toast.makeText(getContext(), "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    long a = gioHangDAO.addGioHang(objGioHang);
                    if (a > 0) {
                        Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        listGioHang.clear();
                        listGioHang.addAll(gioHangDAO.getAllGioHang(taiKhoan));
                        GioHangAdapter gioHangAdapter = new GioHangAdapter(getContext(), listGioHang, gioHangDAO);
                        gioHangAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Không thêm được", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack();
            }
        });

    }

    public void navigateBack() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }


}
