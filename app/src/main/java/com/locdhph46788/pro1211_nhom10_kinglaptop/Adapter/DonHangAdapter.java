package com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.GioHangDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    Context context;
    List<GioHang> listGioHang;
    GioHangDAO gioHangDAO;


    public DonHangAdapter(Context context, List<GioHang> listGioHang, GioHangDAO gioHangDAO) {
        this.context = context;
        this.listGioHang = listGioHang;
        this.gioHangDAO = gioHangDAO;
    }


    @NonNull
    @Override
    public DonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rcv_don_hang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.ViewHolder holder, int position) {
        holder.tvThuongHieu.setText(listGioHang.get(position).getThuongHieu());
        holder.tvTenLaptop.setText(listGioHang.get(position).getTenLaptop());
        holder.tvNamSanXuat.setText(listGioHang.get(position).getNamSanXuat() + "");
        holder.tvSoLuong.setText(listGioHang.get(position).getSoLuong() + "");
        holder.tvTongGia.setText(listGioHang.get(position).getTongGia() + "$");
    }

    @Override
    public int getItemCount() {
        return listGioHang.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvThuongHieu, tvTenLaptop, tvNamSanXuat, tvSoLuong, tvTongGia;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThuongHieu = itemView.findViewById(R.id.tv_thuong_hieu);
            tvTenLaptop = itemView.findViewById(R.id.tv_ten_laptop);
            tvNamSanXuat = itemView.findViewById(R.id.tv_nam_san_xuat);
            tvSoLuong = itemView.findViewById(R.id.tv_so_luong);
            tvTongGia = itemView.findViewById(R.id.tv_tong_gia);

        }
    }
}
