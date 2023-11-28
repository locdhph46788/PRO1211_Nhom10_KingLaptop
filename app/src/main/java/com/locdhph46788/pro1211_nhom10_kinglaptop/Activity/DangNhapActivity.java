package com.locdhph46788.pro1211_nhom10_kinglaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.NguoiDungDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin, btnRegister;
    TextView tvForgotPassword;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);

        nguoiDungDAO = new NguoiDungDAO(this);

        SharedPreferences autoFill = getSharedPreferences("autoFill", MODE_PRIVATE);
        SharedPreferences.Editor editorAutoFill = autoFill.edit();

        if (autoFill != null) {
            String username = autoFill.getString("taiKhoan", "");
            String password = autoFill.getString("matKhau", "");

            edtUsername.setText(username);
            edtPassword.setText(password);

        }
        editorAutoFill.clear();
        editorAutoFill.commit();

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            if (username.equals("")) {
                Toast.makeText(DangNhapActivity.this, "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(DangNhapActivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            } else if (!nguoiDungDAO.checkLogin(username, password)) {
                Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("NguoiDung", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("taiKhoan", username);
                editor.putString("matKhau", password);
                editor.commit();
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DangKyActivity.class);
            startActivity(intent);
            finish();
        });
        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), QuenMatKhauActivity.class);
            startActivity(intent);
            finish();
        });
    }


}