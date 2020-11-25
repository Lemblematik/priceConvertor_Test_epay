package com.example.priceconvertorapp.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.priceconvertorapp.R;
import com.example.priceconvertorapp.data.model.CurrencyResponse;
import com.example.priceconvertorapp.data.net.CurrencyApi;
import com.example.priceconvertorapp.data.net.RetrofitClient;
import com.example.priceconvertorapp.view.adapter.CurrencyAdapter;
import com.example.priceconvertorapp.viewmodel.MainViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceCalculate  extends Fragment {
    MainViewModel currencyViewModel = new MainViewModel();
    CurrencyAdapter adapter;
    EditText valueInserted;

    CurrencyResponse dataCurrency;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.price_calculate, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        valueInserted = getView().findViewById(R.id.insert_value);

        getAllCurrencyValues();
    }


    public void getAllCurrencyValues() {
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
                    handleEditTextWhenValueChanged(valueInserted,dataCurrency);
                }
            }
            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Log.e("Failed", "onFailure" + call.toString());
            }
        });
    }


    private void handleEditTextWhenValueChanged(EditText valueInserted, CurrencyResponse dataCurrency) {
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
                if (valueInserted.getText().toString().length() > 0) {
                    generateCurrencyResults(dataCurrency, Double.parseDouble(valueInserted.getText().toString()));
                }

            }
        });
    }

    private void generateCurrencyResults(CurrencyResponse currencyResponse, double valueInserted) {
        adapter = new CurrencyAdapter(currencyResponse,getContext(), valueInserted);
        adapter.setCurrencyList(currencyResponse);
        adapter.notifyDataSetChanged();
        RecyclerView recyclerView_produit = getView().findViewById(R.id.my_recycler_currency);
        recyclerView_produit.setHasFixedSize(true);
        recyclerView_produit.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView_produit.setAdapter(adapter);
    }


}
