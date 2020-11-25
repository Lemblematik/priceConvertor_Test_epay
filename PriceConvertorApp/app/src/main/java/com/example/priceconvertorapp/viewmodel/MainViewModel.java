package com.example.priceconvertorapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.priceconvertorapp.data.PriceConvertorRepository;
import com.example.priceconvertorapp.data.PriceConvertorRepositoryImpl;
import com.example.priceconvertorapp.data.model.CurrencyResponse;
import com.example.priceconvertorapp.data.net.CurrencyApi;

import java.util.List;

public class MainViewModel  extends ViewModel {
    private PriceConvertorRepository priceConvertorRepository;
    private CurrencyResponse mutableLiveData;

    public MainViewModel(){
        priceConvertorRepository = new PriceConvertorRepositoryImpl();
    }

    public CurrencyResponse getAllCurrencies() {
        if(mutableLiveData==null){
            mutableLiveData = priceConvertorRepository.getAllCurrencyValues();
        }
        return mutableLiveData;
    }

    public List<String> getAllCurrenciesName(){
        return priceConvertorRepository.getAllCurrenciesName();
    }
}
