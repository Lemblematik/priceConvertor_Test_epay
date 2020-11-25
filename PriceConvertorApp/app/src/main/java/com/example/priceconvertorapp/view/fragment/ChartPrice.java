package com.example.priceconvertorapp.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.priceconvertorapp.R;
import com.example.priceconvertorapp.data.model.CurrencyResponse;
import com.example.priceconvertorapp.data.model.CurrencyTimePeriod;
import com.example.priceconvertorapp.data.net.CurrencyApi;
import com.example.priceconvertorapp.data.net.RetrofitClient;
import com.example.priceconvertorapp.view.activity.MainActivity;
import com.example.priceconvertorapp.viewmodel.MainViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartPrice extends Fragment {
    Spinner spinner;
    EditText valueInserted;
    CurrencyResponse dataCurrency;
    private static String selectedCurrency = "";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chart_price, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        valueInserted = getView().findViewById(R.id.insert_value_chart);
        spinner= getView().findViewById(R.id.selecItem_dropdown);




        handleeEditTextAndDropdownAndTimeChanged();



        showTabsForPeriod(selectedCurrency);

    }

    private String getCurrencyChoosed() {
        String currencyChoosed = spinner.getSelectedItem().toString();
        if (currencyChoosed.length()==0){
            currencyChoosed = "CAD";
        }
        return currencyChoosed;
    }

    private void showTabsForPeriod(String base) {
        ChartPrice.ChartTabPagerAdapter tabPager = new ChartPrice.ChartTabPagerAdapter(getChildFragmentManager(), base);
        ViewPager viewPager = getView().findViewById(R.id.viewPager_chart);
        viewPager.setAdapter(tabPager);
        TabLayout tabLayout = getView().findViewById(R.id.tabLayout_chart);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("1D");
        tabLayout.getTabAt(1).setText("1M");
        tabLayout.getTabAt(2).setText("1Y");
    }
    static class ChartTabPagerAdapter extends FragmentPagerAdapter {
        String base;
        ChartTabPagerAdapter(FragmentManager fm, String base) {

            super(fm);
            this.base = base;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new CurrenyPeriodChanges(CurrencyTimePeriod.DAY,base);
                case 1:
                    return new CurrenyPeriodChanges(CurrencyTimePeriod.MONTH,base);
                case 2:
                    return new CurrenyPeriodChanges(CurrencyTimePeriod.YEAR,base);
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private void handleeEditTextAndDropdownAndTimeChanged() {
        CurrencyApi apiService = RetrofitClient.getRetrofitInstance().create(CurrencyApi.class);
        apiService.getAllCurrency().enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e("Good", "requestHolidays response.size="+response.body() );
                    Log.d("pass","hier");
                    CurrencyResponse result = response.body();
                    dataCurrency = new CurrencyResponse();
                    dataCurrency = result;
                    fillDropdown(dataCurrency);
                    handleEditTextWhenValueInserted(valueInserted,dataCurrency);
                }
            }
            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Log.e("Failed", "onFailure" + call.toString());
            }
        });
    }

    private void fillDropdown(CurrencyResponse dataCurrency) {
        List<String> results = new ArrayList<>(dataCurrency.getRates().keySet());
        ArrayAdapter<String> adapterDropdown = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,results);
        spinner.setSelected(true);
        spinner.setAdapter(adapterDropdown);
    }

    private void handleEditTextWhenValueInserted(EditText valueInserted, CurrencyResponse dataCurrency) {
        valueInserted.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
               //
            }
        });
    }

    private void fillAllCurrencyName(List<String> values) {
        ArrayAdapter<String> adapterDropdown = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,values);
        spinner.setAdapter(adapterDropdown);
        spinner.setSelected(true);
        selectedCurrency = spinner.getSelectedItem().toString();
    }


}
