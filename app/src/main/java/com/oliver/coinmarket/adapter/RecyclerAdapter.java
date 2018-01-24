package com.oliver.coinmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oliver.coinmarket.Main2Activity;
import com.oliver.coinmarket.MainActivity;
import com.oliver.coinmarket.R;
import com.oliver.coinmarket.klasi.CoinMarket;
import com.oliver.coinmarket.modeli.CoinModel;
import com.oliver.coinmarket.onClick.OnClick;
import com.oliver.coinmarket.prefferences.SharedPrefferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Oliver on 1/21/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        OnClick onClickListener;
       ArrayList<CoinMarket> coinMarkets;
       public CoinModel modelFavorit = new CoinModel();
        Context context1;

    public void setItems(ArrayList<CoinMarket> coints) {
        this.coinMarkets = coints;
    }

    public RecyclerAdapter(ArrayList <CoinMarket> list, Context context,OnClick onClickListener) {
        this.coinMarkets = list;
        this.context1 = context;
        this.onClickListener = onClickListener;

    }



    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
        final CoinMarket results = coinMarkets.get(position);
        holder.name.setText("Name :" + results.getName());
        holder.symbol.setText("Symbol : " + results.getSymbol());
        holder.price_ud.setText("Price in USD :" + results.getPrice_usd());
        holder.price_btc.setText("Price in BTC :" + results.getPrice_btc());
        holder.rank.setText("Rank :" + results.getRank());
        Picasso.with(context1).load("https://files.coinmarketcap.com/static/img/coins/64x64/"+ results.getId()+".png").fit().into(holder.slika);

        holder.kopce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main2Activity)context1).model.favorites.add(results);
            }

        });
    }



    @Override
    public int getItemCount(){

        return coinMarkets.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id)
        ImageView slika;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.symbol)
        TextView symbol;
        @BindView(R.id.price_usd)
        TextView price_ud;
        @BindView(R.id.price_btc)
        TextView price_btc;
        @BindView(R.id.rank)
        TextView rank;
        @BindView(R.id.kopce)
        Button kopce;
        @BindView(R.id.rel_lay)
        RelativeLayout rel_lay;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
