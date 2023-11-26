package com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.LaptopDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;

public class QuanLyLaptopAdapter extends RecyclerView.Adapter<QuanLyLaptopAdapter.ViewHolder> {
    Context context;
    List<Laptop> listLaptop;
    LaptopDAO laptopDAO;

    public QuanLyLaptopAdapter(Context context, List<Laptop> listLaptop, LaptopDAO laptopDAO) {
        this.context = context;
        this.listLaptop = listLaptop;
        this.laptopDAO = laptopDAO;
    }

    @NonNull
    @Override
    public QuanLyLaptopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rcv_ql_laptop, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyLaptopAdapter.ViewHolder holder, int position) {
        holder.tvThuongHieu.setText(listLaptop.get(position).getThuongHieu());
        holder.tvTenLaptop.setText(listLaptop.get(position).getTenLaptop());
        holder.tvNamSanXuat.setText(listLaptop.get(position).getNamSanXuat() + "");
        holder.tvGiaBan.setText(listLaptop.get(position).getGiaBan() + "");
        holder.tvMoTa.setText(listLaptop.get(position).getMoTa());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Laptop objUpdateLaptop = listLaptop.get(holder.getAdapterPosition());
                DialogUpdateLaptop(objUpdateLaptop);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                alert.setTitle("Xác nhận xóa laptop  !");

                alert.setMessage("Bạn có muốn xóa laptop này?");
                alert.setCancelable(false);

                alert.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        int id = listLaptop.get(holder.getAdapterPosition()).getIdLaptop();
                        boolean check = laptopDAO.deleteLaptop(id);
                        if (check) {
                            Toast.makeText(context, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                            listLaptop.clear();
                            listLaptop.addAll(laptopDAO.getAllLaptop());
                            notifyItemRemoved(holder.getAdapterPosition());
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Hủy", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLaptop.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvThuongHieu, tvTenLaptop, tvNamSanXuat, tvGiaBan, tvMoTa;
        Button btnUpdate, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThuongHieu = itemView.findViewById(R.id.tv_thuong_hieu);
            tvTenLaptop = itemView.findViewById(R.id.tv_ten_laptop);
            tvNamSanXuat = itemView.findViewById(R.id.tv_nam_san_xuat);
            tvGiaBan = itemView.findViewById(R.id.tv_gia_ban);
            tvMoTa = itemView.findViewById(R.id.tv_mo_ta);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public void DialogUpdateLaptop(Laptop objUpdateLaptop) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_laptop, null);
        alert.setView(view);

        EditText edtThuongHieu = view.findViewById(R.id.edt_thuong_hieu);
        EditText edtTenLaptop = view.findViewById(R.id.edt_ten_laptop);
        EditText edtNamSanXuat = view.findViewById(R.id.edt_nam_san_xuat);
        EditText edtGiaBan = view.findViewById(R.id.edt_gia_ban);
        EditText edtMoTa = view.findViewById(R.id.edt_mo_ta);

        edtThuongHieu.setText(objUpdateLaptop.getThuongHieu());
        edtTenLaptop.setText(objUpdateLaptop.getTenLaptop());
        edtNamSanXuat.setText(objUpdateLaptop.getNamSanXuat() + "");
        edtGiaBan.setText(objUpdateLaptop.getGiaBan() + "");
        edtMoTa.setText(objUpdateLaptop.getMoTa());

        alert.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String thuongHieu = edtThuongHieu.getText().toString();
                String tenLaptop = edtTenLaptop.getText().toString();
                String sNamSanXuat = edtNamSanXuat.getText().toString();
                int namSanXuat = Integer.parseInt(sNamSanXuat);
                String sGiaBan = edtGiaBan.getText().toString();
                int giaBan = Integer.parseInt(sGiaBan);
                String moTa = edtMoTa.getText().toString();

                Laptop objLaptop = new Laptop(objUpdateLaptop.getIdLaptop(), tenLaptop, thuongHieu, namSanXuat, giaBan, moTa);
                int check = laptopDAO.updateLaptop(objLaptop);
                if (check > 0) {
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    listLaptop.clear();
                    listLaptop.addAll(laptopDAO.getAllLaptop());
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Hủy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
}
