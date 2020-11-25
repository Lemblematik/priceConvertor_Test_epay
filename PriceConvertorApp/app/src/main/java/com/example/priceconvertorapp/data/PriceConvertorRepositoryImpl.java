package com.example.priceconvertorapp.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.priceconvertorapp.data.model.CurrencyResponse;
import com.example.priceconvertorapp.data.net.CurrencyApi;
import com.example.priceconvertorapp.data.net.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceConvertorRepositoryImpl implements PriceConvertorRepository {
    CurrencyResponse dataCurrency;
    List<String> currenciesName;

    public CurrencyResponse getAllCurrencyValues() {
        CurrencyApi apiService = RetrofitClient.getRetrofitInstance().create(CurrencyApi.class);
        apiService.getAllCurrency().enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e("Good", "response.size="+response.body() );
                    Log.d("pass","hier");
                    CurrencyResponse result = response.body();
                    dataCurrency = new CurrencyResponse();
                    dataCurrency = result;
                }
            }
            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Log.e("Failed", "onFailure" + call.toString());
            }
        });

        return dataCurrency;
    }

    @Override
    public List<String> getAllCurrenciesName() {
        CurrencyApi apiService = RetrofitClient.getRetrofitInstance().create(CurrencyApi.class);
        apiService.getAllCurrency().enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e("Good", "requestHolidays response.size="+response.body() );
                    Log.d("pass","hier");
                    CurrencyResponse result = response.body();
                    currenciesName = new ArrayList<>(result.getRates().keySet());
                }
            }
            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Log.e("Failed", "onFailure" + call.toString());
            }
        });

        return currenciesName;
    }


}
