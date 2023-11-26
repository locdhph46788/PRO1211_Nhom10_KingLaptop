package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.QuanLyLaptopAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.LaptopDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;


public class QuanLyLaptopFragment extends Fragment {
    FloatingActionButton fAB;
    RecyclerView rcvQLLaptop;
    LaptopDAO laptopDAO;
    QuanLyLaptopAdapter quanLyLaptopAdapter;
    List<Laptop> listLaptop;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quanlylaptop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvQLLaptop = view.findViewById(R.id.rcv_quan_ly_laptop);
        laptopDAO = new LaptopDAO(getContext());
        listLaptop = laptopDAO.getAllLaptop();
        quanLyLaptopAdapter = new QuanLyLaptopAdapter(getContext(), listLaptop, laptopDAO);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvQLLaptop.setLayoutManager(linearLayoutManager);
        rcvQLLaptop.setAdapter(quanLyLaptopAdapter);
        fAB = view.findViewById(R.id.fab_add);
        fAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });

    }

    public void DialogAdd() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_laptop, null);
        alert.setView(view);
        AlertDialog alertDialog = alert.create();
        EditText edtThuongHieu = view.findViewById(R.id.edt_thuong_hieu);
        EditText edtTenLaptop = view.findViewById(R.id.edt_ten_laptop);
        EditText edtNamSanXuat = view.findViewById(R.id.edt_nam_san_xuat);
        EditText edtGiaBan = view.findViewById(R.id.edt_gia_ban);
        EditText edtMoTa = view.findViewById(R.id.edt_mo_ta);


        Button btnAdd = view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thuongHieu = edtThuongHieu.getText().toString();
                String tenLaptop = edtTenLaptop.getText().toString();
                String sNamSanXuat = edtNamSanXuat.getText().toString();
                int namSanXuat = Integer.parseInt(sNamSanXuat);
                String sGiaBan = edtGiaBan.getText().toString();
                int giaBan = Integer.parseInt(sGiaBan);
                String moTa = edtMoTa.getText().toString();
                if (thuongHieu.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập thương hiệu", Toast.LENGTH_SHORT).show();
                } else if (tenLaptop.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập tên laptop", Toast.LENGTH_SHORT).show();
                } else if (sNamSanXuat.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập năm sản xuất", Toast.LENGTH_SHORT).show();
                } else if (sGiaBan.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập giá bán", Toast.LENGTH_SHORT).show();
                } else if (moTa.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập mô tả", Toast.LENGTH_SHORT).show();
                } else {
                    Laptop objLaptop = new Laptop(tenLaptop, thuongHieu, namSanXuat, giaBan, moTa);
                    long id = laptopDAO.addLaptop(objLaptop);
                    if (id > 0) {
                        Toast.makeText(getContext(), "Thêm thành công " + id, Toast.LENGTH_SHORT).show();
                        listLaptop.clear();
                        listLaptop.addAll(laptopDAO.getAllLaptop());
                        quanLyLaptopAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Không thêm được", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        alertDialog.show();

    }

}
