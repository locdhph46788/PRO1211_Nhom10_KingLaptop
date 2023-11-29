package com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.LaptopDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment.ChiTietFragment;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.ArrayList;
import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> implements Filterable {
    Context context;
    List<Laptop> listLaptop;
    List<Laptop> listLaptopOld;
    LaptopDAO laptopDAO;

    public LaptopAdapter(Context context, List<Laptop> listLaptop, LaptopDAO laptopDAO) {
        this.context = context;
        this.listLaptop = listLaptop;
        this.listLaptopOld = listLaptop;
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
        Laptop lineLaptop = listLaptop.get(position);
        int idLaptop = listLaptop.get(position).getIdLaptop();
        holder.tvThuongHieu.setText(listLaptop.get(position).getThuongHieu());
        holder.tvTenLaptop.setText(listLaptop.get(position).getTenLaptop());
        holder.tvNamSanXuat.setText(listLaptop.get(position).getNamSanXuat() + "");
        holder.tvGiaBan.setText(listLaptop.get(position).getGiaBan() + " $");
        holder.tvMoTa.setText(listLaptop.get(position).getMoTa());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragChiTiet = new ChiTietFragment();
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_view, fragChiTiet, "A");
                transaction.addToBackStack(null);
                transaction.commit();
                SharedPreferences sharedPreferences = context.getSharedPreferences("ChiTietLaptop", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("idLaptop", lineLaptop.getIdLaptop());
                editor.putString("thuongHieu", lineLaptop.getThuongHieu());
                editor.putString("tenLaptop", lineLaptop.getTenLaptop());
                editor.putInt("namSanXuat", lineLaptop.getNamSanXuat());
                editor.putInt("giaBan", lineLaptop.getGiaBan());
                editor.putString("moTa", lineLaptop.getMoTa());
                editor.clear();
                editor.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listLaptop.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String sSearch = constraint.toString();
                if (sSearch.isEmpty()) {
                    listLaptop = listLaptopOld;
                } else {
                    List<Laptop> list = new ArrayList<>();
                    for (Laptop laptop : listLaptopOld) {
                        if (laptop.getTenLaptop().toLowerCase().contains(sSearch.toLowerCase()) || laptop.getThuongHieu().toLowerCase().contains(sSearch.toLowerCase()) || String.valueOf(laptop.getNamSanXuat()).toLowerCase().contains(sSearch.toLowerCase())) {
                            list.add(laptop);
                        }
                    }
                    listLaptop = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listLaptop;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listLaptop = (List<Laptop>) results.values;
                notifyDataSetChanged();
            }
        };
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
