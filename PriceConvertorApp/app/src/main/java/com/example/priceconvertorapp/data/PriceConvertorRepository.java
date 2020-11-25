package com.example.priceconvertorapp.data;


import androidx.lifecycle.MutableLiveData;

import com.example.priceconvertorapp.data.model.CurrencyResponse;

import java.util.List;

public interface PriceConvertorRepository {
    CurrencyResponse getAllCurrencyValues();

    List<String> getAllCurrenciesName();
}
