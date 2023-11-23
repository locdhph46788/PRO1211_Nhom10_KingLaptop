package com.locdhph46788.pro1211_nhom10_kinglaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.NguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.NguoiDung;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;

public class DangKyActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtRepassword, edtName, edtEmail;
    Button btnRegister, btnBack;

    NguoiDungDAO nguoiDungDAO;
    List<NguoiDung> listND;
    NguoiDung objND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        nguoiDungDAO = new NguoiDungDAO(DangKyActivity.this);
        listND = nguoiDungDAO.getAllND();
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtRepassword = findViewById(R.id.edt_repassword);
        edtEmail = findViewById(R.id.edt_email);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String repassword = edtRepassword.getText().toString();
                String email = edtEmail.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập username", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (repassword.equals("")) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!repassword.equals(password)) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                } else {
                    objND = new NguoiDung(username, password, email, 1);
                    long id = nguoiDungDAO.addND(objND);
                    if (id > 0) {
                        Toast.makeText(DangKyActivity.this, "Đăng ký thành công ! ID:" + id, Toast.LENGTH_SHORT).show();
                        listND.clear();
                        listND.addAll(nguoiDungDAO.getAllND());
                        Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        bundle.putString("password", password);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DangKyActivity.this, "Đăng ký không thành công ", Toast.LENGTH_SHORT).show();
                    }
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