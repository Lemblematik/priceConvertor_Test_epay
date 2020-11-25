package com.example.priceconvertorapp.data.net;
import com.example.priceconvertorapp.data.model.CurrencyResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    /*
    private final String CURRENCY_BASE_URL = "https://api.exchangeratesapi.io/";
    private CurrencyApi currency;

    RetrofitClient(){
       OkHttpClient okHttpClient = new OkHttpClient
                .Builder().build();
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(CURRENCY_BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(okHttpClient)
               .build();
        currency = retrofit.create(CurrencyApi.class);
    }

    public Call<CurrencyResponse> getCurrency () {
        return currency.getAllCurrency();
    }

     */

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.exchangeratesapi.io/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
