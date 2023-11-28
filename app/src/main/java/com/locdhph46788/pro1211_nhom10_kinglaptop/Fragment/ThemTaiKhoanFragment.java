package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.locdhph46788.pro1211_nhom10_kinglaptop.Activity.DangKyActivity;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Activity.DangNhapActivity;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.NguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.NguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;


public class ThemTaiKhoanFragment extends Fragment {
    EditText edtUsername, edtPassword, edtRepassword, edtEmail;
    Button btnAdd;

    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listND;
    NguoiDung objND;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_themtaikhoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = view.findViewById(R.id.edt_taikhoan);
        edtPassword = view.findViewById(R.id.edt_matkhau);
        edtRepassword = view.findViewById(R.id.edt_matkhau2);
        edtEmail = view.findViewById(R.id.edt_email);

        nguoiDungDAO = new NguoiDungDAO(getContext());
        listND = nguoiDungDAO.getAllND();

        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String repassword = edtRepassword.getText().toString();
            String email = edtEmail.getText().toString();

            if (username.equals("")) {
                Toast.makeText(getContext(), "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(getContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (repassword.equals("")) {
                Toast.makeText(getContext(), "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (!repassword.equals(password)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            } else if (email.equals("")) {
                Toast.makeText(getContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            } else {
                objND = new NguoiDung(username, password, email, 0);
                long id = nguoiDungDAO.addND(objND);
                if (id > 0) {
                    Toast.makeText(getContext(), "Đăng ký thành công ! Tài khoản:" + username, Toast.LENGTH_SHORT).show();
                    listND.clear();
                    listND.addAll(nguoiDungDAO.getAllND());
                } else {
                    Toast.makeText(getContext(), "Đăng ký không thành công ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
