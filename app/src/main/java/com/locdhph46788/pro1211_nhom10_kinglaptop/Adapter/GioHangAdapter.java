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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.GioHangDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.LaptopDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.GioHang;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    Context context;
    List<GioHang> listGioHang;
    GioHangDAO gioHangDAO;


    public GioHangAdapter(Context context, List<GioHang> listGioHang, GioHangDAO gioHangDAO) {
        this.context = context;
        this.listGioHang = listGioHang;
        this.gioHangDAO = gioHangDAO;
    }


    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.rcv_gio_hang, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvThuongHieu.setText(listGioHang.get(position).getThuongHieu());
        holder.tvTenLaptop.setText(listGioHang.get(position).getTenLaptop());
        holder.tvNamSanXuat.setText(listGioHang.get(position).getNamSanXuat() + "");
        holder.tvGiaBan.setText(listGioHang.get(position).getGiaBan() + "$");
        holder.tvSoLuong.setText(listGioHang.get(position).getSoLuong() + "");
        holder.tvTongGia.setText(listGioHang.get(position).getTongGia() + "$");
        holder.btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGioHang.get(position).getSoLuong() == 1) {
                    Toast.makeText(context, "Số lượng đã đạt tối thiểu", Toast.LENGTH_SHORT).show();
                } else {
                    int id = listGioHang.get(position).getIdHang();
                    int soLuong = listGioHang.get(position).getSoLuong();
                    GioHang objUpdateGH = new GioHang(id, soLuong - 1);
                    gioHangDAO.updateGioHang(objUpdateGH);
                    listGioHang.get(position).setSoLuong(soLuong - 1);
                    tinhGiaThanhToan();
                    notifyItemChanged(position);
                    notifyDataSetChanged();
                }

            }
        });
        holder.btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGioHang.get(position).getSoLuong() == 99) {
                    Toast.makeText(context, "Số lượng đã đạt tối đa", Toast.LENGTH_SHORT).show();
                } else {
                    int id = listGioHang.get(position).getIdHang();
                    int soLuong = listGioHang.get(position).getSoLuong();
                    GioHang objUpdateGH = new GioHang(id, soLuong + 1);
                    gioHangDAO.updateGioHang(objUpdateGH);
                    listGioHang.get(position).setSoLuong(soLuong + 1);
                    tinhGiaThanhToan();
                    notifyItemChanged(position);
                    notifyDataSetChanged();
                }

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                alert.setTitle("Xác nhận xóa sản phẩm !");

                alert.setMessage("Bạn có muốn xóa sản phẩm này?");
                alert.setCancelable(false);

                alert.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        int id = listGioHang.get(holder.getAdapterPosition()).getIdHang();
                        boolean check = gioHangDAO.deleteGioHang(id);
                        if (check) {
                            Toast.makeText(context, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = context.getSharedPreferences("NguoiDung", context.MODE_PRIVATE);
                            String taiKhoan = sharedPreferences.getString("taiKhoan", "");
                            listGioHang.clear();
                            listGioHang.addAll(gioHangDAO.getAllGioHang(taiKhoan));
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
        return listGioHang.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvThuongHieu, tvTenLaptop, tvNamSanXuat, tvGiaBan, tvSoLuong, tvTongGia;
        ImageView btnGiam, btnTang;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThuongHieu = itemView.findViewById(R.id.tv_thuong_hieu);
            tvTenLaptop = itemView.findViewById(R.id.tv_ten_laptop);
            tvNamSanXuat = itemView.findViewById(R.id.tv_nam_san_xuat);
            tvGiaBan = itemView.findViewById(R.id.tv_gia_ban);
            tvSoLuong = itemView.findViewById(R.id.tv_so_luong);
            tvTongGia = itemView.findViewById(R.id.tv_tong_gia);
            btnGiam = itemView.findViewById(R.id.iv_minus);
            btnTang = itemView.findViewById(R.id.iv_plus);
            btnDelete = itemView.findViewById(R.id.btn_delete);

        }
    }

    public double tinhGiaThanhToan() {
        double tongGia = 0.0;

        for (GioHang gioHang : listGioHang) {
            tongGia += gioHang.getTongGia();
            notifyDataSetChanged();
        }
        return tongGia;
    }


}
