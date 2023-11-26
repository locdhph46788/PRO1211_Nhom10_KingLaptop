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

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {
    Context context;
    List<Laptop> listLaptop;
    LaptopDAO laptopDAO;

    public LaptopAdapter(Context context, List<Laptop> listLaptop, LaptopDAO laptopDAO) {
        this.context = context;
        this.listLaptop = listLaptop;
        this.laptopDAO = laptopDAO;
    }

    @NonNull
    @Override
    public LaptopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rcv_laptop, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopAdapter.ViewHolder holder, int position) {
        holder.tvThuongHieu.setText(listLaptop.get(position).getThuongHieu());
        holder.tvTenLaptop.setText(listLaptop.get(position).getTenLaptop());
        holder.tvNamSanXuat.setText(listLaptop.get(position).getNamSanXuat() + "");
        holder.tvGiaBan.setText(listLaptop.get(position).getGiaBan() + "VND");
        holder.tvMoTa.setText(listLaptop.get(position).getMoTa());
    }
    @Override
    public int getItemCount() {
        return listLaptop.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvThuongHieu, tvTenLaptop, tvNamSanXuat, tvGiaBan, tvMoTa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThuongHieu = itemView.findViewById(R.id.tv_thuong_hieu);
            tvTenLaptop = itemView.findViewById(R.id.tv_ten_laptop);
            tvNamSanXuat = itemView.findViewById(R.id.tv_nam_san_xuat);
            tvGiaBan = itemView.findViewById(R.id.tv_gia_ban);
            tvMoTa = itemView.findViewById(R.id.tv_mo_ta);
        }
    }

}
