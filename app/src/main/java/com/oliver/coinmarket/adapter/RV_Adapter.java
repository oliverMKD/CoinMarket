package com.oliver.coinmarket.adapter;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oliver on 1/21/2018.
 */

public class RV_Adapter extends RecyclerView.Adapter<RV_Adapter.ViewHolder> {
    OnClick onClick;
    ArrayList<CoinMarket> favorites = new ArrayList<>();
    Context context1;
    public void setItems(ArrayList<CoinMarket> results) {
        favorites = results;
        notifyDataSetChanged();
    }

    public RV_Adapter(ArrayList<CoinMarket> favorites, Context context) {
        this.favorites = favorites;
        this.context1 = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row1, parent, false);
        RV_Adapter.ViewHolder holder = new RV_Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CoinMarket results = favorites.get(position);
        holder.name.setText("Name :" + results.getName());
        holder.symbol.setText("Symbol : " + results.getSymbol());
        holder.price_ud.setText("Price in USD :" + results.getPrice_usd());
        holder.price_btc.setText("Price in BTC :" + results.getPrice_btc());
        holder.rank.setText("Rank :" + results.getRank());
        Picasso.with(context1).load("https://files.coinmarketcap.com/static/img/coins/64x64/"+ results.getId()+".png").fit().into(holder.slika);


    }

    @Override
    public int getItemCount() {
        return favorites.size();
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
        @BindView(R.id.row)
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
