package com.example.priceconvertorapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.priceconvertorapp.R;
import com.example.priceconvertorapp.data.model.CurrencyResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CurrencyAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public CurrencyResponse response;
    private Context mContext;
    protected LayoutInflater inflater;
    double valueInserted;
    List<String> listCurrency;
    List<Double> listValueCurrency;


    public CurrencyAdapter(CurrencyResponse response, Context context, double valueInserted){
        this.response = response;
        this.valueInserted = valueInserted;
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
        listCurrency = new ArrayList<>(this.response.getRates().keySet());
        listValueCurrency = new ArrayList<>(this.response.getRates().values());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.curreny, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CurrencyAdapter.ItemHolder itemHolder = (CurrencyAdapter.ItemHolder) holder;

        itemHolder.currency_name.setText(listCurrency.get(position));
        double result = valueInserted*listValueCurrency.get(position);

        itemHolder.value_calculated.setText(""+ result);
        itemHolder.valueEuro.append("1 EUR = " + listValueCurrency.get(position).toString() + " " + listCurrency.get(position));

        double result2 = valueInserted/listValueCurrency.get(position);

        itemHolder.valueEutoToThisCurrency.setText("1 " + listCurrency.get(position) + " = " + result2 + " EUR" );

    }



    @Override
    public int getItemCount() {
        return listCurrency.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        public TextView currency_name;
        public TextView value_calculated;
        public TextView valueEuro;
        public TextView valueEutoToThisCurrency;



        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            currency_name = itemView.findViewById(R.id.currency_name);
            value_calculated = itemView.findViewById(R.id.value_calculated);
            valueEuro = itemView.findViewById(R.id.valueEuro);
            valueEutoToThisCurrency = itemView.findViewById(R.id.valueEutoToThisCurrency);
        }

    }

    public  void setCurrencyList(CurrencyResponse response){
        this.response = response;
        notifyDataSetChanged();
    }
}
