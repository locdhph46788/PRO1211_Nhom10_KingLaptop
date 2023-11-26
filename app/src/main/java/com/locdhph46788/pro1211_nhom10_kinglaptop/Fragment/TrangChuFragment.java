package com.locdhph46788.pro1211_nhom10_kinglaptop.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.BannerAdsAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.LaptopAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Adapter.QuanLyLaptopAdapter;
import com.locdhph46788.pro1211_nhom10_kinglaptop.DAO.LaptopDAO;
import com.locdhph46788.pro1211_nhom10_kinglaptop.Model.Laptop;
import com.locdhph46788.pro1211_nhom10_kinglaptop.R;

import java.util.ArrayList;
import java.util.List;


public class TrangChuFragment extends Fragment {
    ViewPager viewPager;
    BannerAdsAdapter baAdapter;
    List<Integer> imageList;

    Handler handler;
    Runnable runnable;
    int delay = 2000;

    RecyclerView rcvLaptop;
    LaptopDAO laptopDAO;
    LaptopAdapter laptopAdapter;
    List<Laptop> listLaptop;

    public TrangChuFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        imageList = new ArrayList<>();
        imageList.add(R.drawable.banner1);
        imageList.add(R.drawable.banner2);

        baAdapter = new BannerAdsAdapter(getContext(), imageList);
        viewPager.setAdapter(baAdapter);

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = baAdapter.getCount();
                if (currentItem < totalItems - 1) {
                    currentItem++;
                } else {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem);
                handler.postDelayed(this, delay);
            }
        };

        handler.postDelayed(runnable, delay);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvLaptop = view.findViewById(R.id.rcv_laptop);
        laptopDAO = new LaptopDAO(getContext());
        listLaptop = laptopDAO.getAllLaptop();
        laptopAdapter = new LaptopAdapter(getContext(), listLaptop, laptopDAO);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcvLaptop.setLayoutManager(linearLayoutManager);
        rcvLaptop.setAdapter(laptopAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }
}
