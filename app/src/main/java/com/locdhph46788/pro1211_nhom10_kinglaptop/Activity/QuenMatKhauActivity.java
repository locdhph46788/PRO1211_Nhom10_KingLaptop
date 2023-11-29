package com.locdhph46788.pro1211_nhom10_kinglaptop.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.NguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.NguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;

public class QuenMatKhauActivity extends AppCompatActivity {
    EditText edtUsername, edtEmail;
    Button btnConfirm, btnBack;
    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listND;
    NguoiDung objND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau);
        nguoiDungDAO = new NguoiDungDAO(this);
        listND = nguoiDungDAO.getAllND();
        edtUsername = findViewById(R.id.edt_username_forgot);
        edtEmail = findViewById(R.id.edt_email_forgot);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                if (username.equals("")) {
                    Toast.makeText(QuenMatKhauActivity.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(QuenMatKhauActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                } else if (!nguoiDungDAO.checkForgot(username, email)) {
                    Toast.makeText(QuenMatKhauActivity.this, "Tài khoản hoặc email không chính xác!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuenMatKhauActivity.this, "Thông tin hợp lệ!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alert = new AlertDialog.Builder(QuenMatKhauActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
                    alert.setView(view);
                    alert.setCancelable(false);
                    TextView tvUsername = view.findViewById(R.id.tv_username);
                    tvUsername.setText(username);
                    LinearLayout layout = view.findViewById(R.id.ll_old_password);
                    layout.setVisibility(View.GONE);
                    EditText edtPassword = view.findViewById(R.id.edt_password);
                    EditText edtRePassword = view.findViewById(R.id.edt_repassword);
                    Button btnUpdate = view.findViewById(R.id.btn_update);

                    alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(QuenMatKhauActivity.this, "Hủy", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String password = edtPassword.getText().toString().trim();
                            String rePassword = edtRePassword.getText().toString().trim();
                            if (password.equals("")) {
                                Toast.makeText(QuenMatKhauActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                            } else if (rePassword.equals("")) {
                                Toast.makeText(QuenMatKhauActivity.this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                            } else if (!password.equals(rePassword)) {
                                Toast.makeText(QuenMatKhauActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                            } else {
                                NguoiDung objND = new NguoiDung();
                                objND.setTaiKhoan(username);
                                objND.setMatKhau(password);
                                int id = nguoiDungDAO.changePassword(objND);
                                if (id > 0) {
                                    Toast.makeText(QuenMatKhauActivity.this, "Cập nhật mật khẩu thành công ", Toast.LENGTH_SHORT).show();
                                    listND.clear();
                                    listND.addAll(nguoiDungDAO.getAllND());
                                    alertDialog.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(QuenMatKhauActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                }
                            }
                        }
                    });
                    alertDialog.show();

                }
            }
        });
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}