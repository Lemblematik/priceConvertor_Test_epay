package com.example.priceconvertorapp.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.priceconvertorapp.R;
import com.example.priceconvertorapp.data.model.CurrencyPeriodResponse;
import com.example.priceconvertorapp.data.model.CurrencyResponse;
import com.example.priceconvertorapp.data.model.CurrencyTimePeriod;
import com.example.priceconvertorapp.data.net.CurrencyApi;
import com.example.priceconvertorapp.data.net.RetrofitClient;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrenyPeriodChanges extends Fragment {

    CurrencyTimePeriod periodTime;
    BarChart barChart;
    CurrencyPeriodResponse data;
    String base;


    public CurrenyPeriodChanges(CurrencyTimePeriod periodTime, String base){
        this.periodTime = periodTime;
        this.base = base;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currency_period_time, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        barChart = getView().findViewById(R.id.barchart_layout);

        getAllPeriodCurrencyData(base);
        BarDataSet barDataSet = new BarDataSet()
    }

    private void getAllPeriodCurrencyData(String base) {

        if (periodTime.equals(CurrencyTimePeriod.DAY)){
            handleRequest("2018-01-01","2018-01-01",base);
        }
        else if (periodTime.equals(CurrencyTimePeriod.MONTH)){
            handleRequest("2018-01-01","2018-01-31",base);
        }
        else if (periodTime.equals(CurrencyTimePeriod.YEAR)){
            handleRequest("2018-01-01","2018-12-31",base);
        }

    }

    private void handleRequest(String start, String end, String base) {

        CurrencyApi apiService = RetrofitClient.getRetrofitInstance().create(CurrencyApi.class);
        apiService.getAllCurrencyPeriods(start,end,base).enqueue(new Callback<CurrencyPeriodResponse>() {
            @Override
            public void onResponse(Call<CurrencyPeriodResponse> call, Response<CurrencyPeriodResponse> response) {
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e("Good", "requestHolidays response.size="+response.body() );
                    Log.d("pass","hier");
                    CurrencyPeriodResponse result = response.body();
                    data = new CurrencyPeriodResponse();
                    data = result;
                    Log.d("resssuuulllt", data.toString());
                    //handleEditTextWhenValueInserted(valueInserted,dataCurrency);
                }
            }
            @Override
            public void onFailure(Call<CurrencyPeriodResponse> call, Throwable t) {
                Log.e("Failed", "onFailure" + call.toString());
            }
        });
    }
}
