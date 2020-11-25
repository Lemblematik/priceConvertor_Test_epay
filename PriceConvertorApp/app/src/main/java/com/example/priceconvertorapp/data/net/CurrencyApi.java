package com.example.priceconvertorapp.data.net;

import com.example.priceconvertorapp.data.model.CurrencyPeriodResponse;
import com.example.priceconvertorapp.data.model.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi {

    @GET("latest")
    Call<CurrencyResponse> getAllCurrency();

    @GET("history?")
    Call<CurrencyPeriodResponse> getAllCurrencyPeriods(@Query("start_at")String start_at,
                                                       @Query("end_at")String end_at,
                                                       @Query("base") String base);
}