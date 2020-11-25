package com.example.priceconvertorapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.priceconvertorapp.R;
import com.example.priceconvertorapp.data.model.CurrencyResponse;
import com.example.priceconvertorapp.view.adapter.CurrencyAdapter;
import com.example.priceconvertorapp.view.fragment.ChartPrice;
import com.example.priceconvertorapp.view.fragment.PriceCalculate;
import com.example.priceconvertorapp.viewmodel.MainViewModel;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTabs();
    }

    private void showTabs() {

        MainActivity.MyTabPagerAdapter tabPager = new MainActivity.MyTabPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tabPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("RATES");
        tabLayout.getTabAt(1).setText("CHARTS");

    }

    static class MyTabPagerAdapter extends FragmentPagerAdapter {
        MyTabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new PriceCalculate();
                case 1:
                    return new ChartPrice();
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}