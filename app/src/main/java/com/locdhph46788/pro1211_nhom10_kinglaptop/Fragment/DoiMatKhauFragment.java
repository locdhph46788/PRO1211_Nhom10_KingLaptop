package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.NguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.NguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;


public class DoiMatKhauFragment extends Fragment {

    TextView tvUsername, tvPassword, tvChange;

    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listND;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doimatkhau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUsername = view.findViewById(R.id.tv_username);
        tvPassword = view.findViewById(R.id.tv_password);

        nguoiDungDAO = new NguoiDungDAO(getContext());
        listND = nguoiDungDAO.getAllND();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NguoiDung", Context.MODE_PRIVATE);
        String taiKhoan = sharedPreferences.getString("taiKhoan", "");
        String matKhau = sharedPreferences.getString("matKhau", "");

        tvUsername.setText(taiKhoan);
        tvPassword.setText(matKhau);

        tvChange = view.findViewById(R.id.tv_change);
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
                alert.setView(view);
                alert.setCancelable(false);
                TextView tvCheck = view.findViewById(R.id.tv_check);
                TextView tvUsername = view.findViewById(R.id.tv_username);
                tvUsername.setText(taiKhoan);
                EditText edtOldPassword = view.findViewById(R.id.edt_old_password);
                EditText edtNewPassword = view.findViewById(R.id.edt_password);
                EditText edtNewRePassword = view.findViewById(R.id.edt_repassword);
                Button btnUpdate = view.findViewById(R.id.btn_update);

                edtNewPassword.setVisibility(View.GONE);
                edtNewRePassword.setVisibility(View.GONE);
                btnUpdate.setVisibility(View.GONE);

                tvCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String oldPassword = edtOldPassword.getText().toString().trim();
                        if (oldPassword.equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
                        } else if (!nguoiDungDAO.checkLogin(taiKhoan, oldPassword)) {
                            Toast.makeText(getContext(), "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Mật khẩu cũ chính xác ! Mời bạn nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
                            edtOldPassword.setKeyListener(null);
                            tvCheck.setVisibility(View.GONE);
                            edtNewPassword.setVisibility(View.VISIBLE);
                            edtNewRePassword.setVisibility(View.VISIBLE);
                            btnUpdate.setVisibility(View.VISIBLE);
                        }

                    }
                });

                alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Hủy", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alert.create();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = edtNewPassword.getText().toString().trim();
                        String rePassword = edtNewRePassword.getText().toString().trim();
                        if (password.equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                        } else if (rePassword.equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                        } else if (!password.equals(rePassword)) {
                            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        } else {
                            NguoiDung objND = new NguoiDung();
                            objND.setTaiKhoan(taiKhoan);
                            objND.setMatKhau(password);
                            int id = nguoiDungDAO.changePassword(objND);
                            if (id > 0) {
                                Toast.makeText(getContext(), "Cập nhật mật khẩu thành công ", Toast.LENGTH_SHORT).show();
                                listND.clear();
                                listND.addAll(nguoiDungDAO.getAllND());
                                alertDialog.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        }
                    }

                });
                alertDialog.show();
            }
        });
    }

}
