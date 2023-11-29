package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.ThongTinNguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.ThongTinNguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;


public class ThongTinNguoiDungFragment extends Fragment {
    TextView tvTaiKhoan;
    EditText edtHoTen, edtSDT, edtDiaChi;
    Button btnLuu;

    ThongTinNguoiDungDAO tTNDDAO;
    List<ThongTinNguoiDung> listTTND;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongtinnguoidung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTaiKhoan = view.findViewById(R.id.tv_taikhoan);
        edtHoTen = view.findViewById(R.id.edt_ho_ten);
        edtSDT = view.findViewById(R.id.edt_sdt);
        edtDiaChi = view.findViewById(R.id.edt_dia_chi);
        btnLuu = view.findViewById(R.id.btn_save);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NguoiDung", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("taiKhoan", "");
        tvTaiKhoan.setText(taiKhoan);
        tTNDDAO = new ThongTinNguoiDungDAO(getContext());
        listTTND = tTNDDAO.getAllTTND();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtHoTen.getText().toString();
                String sDT = edtSDT.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                if (tTNDDAO.checkTTND(taiKhoan) == false){
                    if (hoTen.equals("")) {
                        Toast.makeText(getContext(), "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
                    } else if (sDT.equals("")) {
                        Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    } else if (diaChi.equals("")) {
                        Toast.makeText(getContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                    } else {
                        ThongTinNguoiDung objTTND = new ThongTinNguoiDung(taiKhoan, hoTen, sDT, diaChi);
                        long id = tTNDDAO.addTTND(objTTND);
                        if (id > 0) {
                            Toast.makeText(getContext(), "Lưu thành công ", Toast.LENGTH_SHORT).show();
                            listTTND.clear();
                            listTTND.addAll(tTNDDAO.getAllTTND());
                        } else {
                            Toast.makeText(getContext(), "Không lưu được", Toast.LENGTH_SHORT).show();
                        }
                        edtHoTen.setText(hoTen);
                        edtSDT.setText(sDT);
                        edtDiaChi.setText(diaChi);
                    }
                }else if (tTNDDAO.checkTTND(taiKhoan) == true){
                    if (hoTen.equals("")) {
                        Toast.makeText(getContext(), "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
                    } else if (sDT.equals("")) {
                        Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    } else if (diaChi.equals("")) {
                        Toast.makeText(getContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                    } else {
                        ThongTinNguoiDung objTTND = new ThongTinNguoiDung(taiKhoan, hoTen, sDT, diaChi);
                        int check = tTNDDAO.updateTTND(objTTND);
                        if (check > 0) {
                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            listTTND.clear();
                            listTTND.addAll(tTNDDAO.getAllTTND());
                        } else {
                            Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                edtHoTen.setText(hoTen);
                edtSDT.setText(sDT);
                edtDiaChi.setText(diaChi);
                SharedPreferences thongTinGiaoHang = getContext().getSharedPreferences("ThongTinGiaoHang", getContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = thongTinGiaoHang.edit();
                editor.putString("hoTen", hoTen);
                editor.putString("sDT", sDT);
                editor.putString("diaChi", diaChi);
                editor.clear();
                editor.commit();
            }

        });
//        if (tTNDDAO.checkTTND(taiKhoan) == false) {
//            String taiKhoan1 = taiKhoan;
//            btnLuu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String hoTen = edtHoTen.getText().toString();
//                    String sDT = edtSDT.getText().toString();
//                    String diaChi = edtDiaChi.getText().toString();
//                    if (hoTen.equals("")) {
//                        Toast.makeText(getContext(), "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
//                    } else if (sDT.equals("")) {
//                        Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
//                    } else if (diaChi.equals("")) {
//                        Toast.makeText(getContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
//                    } else {
//                        ThongTinNguoiDung objTTND = new ThongTinNguoiDung(taiKhoan1, hoTen, sDT, diaChi);
//                        long id = tTNDDAO.addTTND(objTTND);
//                        if (id > 0) {
//                            Toast.makeText(getContext(), "Lưu thành công ", Toast.LENGTH_SHORT).show();
//                            listTTND.clear();
//                            listTTND.addAll(tTNDDAO.getAllTTND());
//
//                        } else {
//                            Toast.makeText(getContext(), "Không lưu được", Toast.LENGTH_SHORT).show();
//                        }
//                        edtHoTen.setText(hoTen);
//                        edtSDT.setText(sDT);
//                        edtDiaChi.setText(diaChi);
//
//                    }
//                }
//            });
//        } else if (tTNDDAO.checkTTND(taiKhoan) == true) {
//            String taiKhoan2 = taiKhoan;
//            btnLuu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String hoTen = edtHoTen.getText().toString();
//                    String sDT = edtSDT.getText().toString();
//                    String diaChi = edtDiaChi.getText().toString();
//                    if (hoTen.equals("")) {
//                        Toast.makeText(getContext(), "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
//                    } else if (sDT.equals("")) {
//                        Toast.makeText(getContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
//                    } else if (diaChi.equals("")) {
//                        Toast.makeText(getContext(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
//                    } else {
//                        ThongTinNguoiDung objTTND = new ThongTinNguoiDung(taiKhoan2, hoTen, sDT, diaChi);
//                        int check = tTNDDAO.updateTTND(objTTND);
//                        if (check > 0) {
//                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//                            listTTND.clear();
//                            listTTND.addAll(tTNDDAO.getAllTTND());
//                        } else {
//                            Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    edtHoTen.setText(hoTen);
//                    edtSDT.setText(sDT);
//                    edtDiaChi.setText(diaChi);
//
//                }
//
//
//            });
//
//        }

        String hoTen = edtHoTen.getText().toString();
        String sDT = edtSDT.getText().toString();
        String diaChi = edtDiaChi.getText().toString();
        for (ThongTinNguoiDung ttnd : listTTND) {
            if (ttnd.getTaiKhoan().equals(taiKhoan)) {
                hoTen = ttnd.getHoTen();
                sDT = ttnd.getsDT();
                diaChi = ttnd.getDiaChi();
                break;
            }
        }
        edtHoTen.setText(hoTen);
        edtSDT.setText(sDT);
        edtDiaChi.setText(diaChi);


    }
}
