package com.oliver.coinmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oliver.coinmarket.api.RestApi;
import com.oliver.coinmarket.klasi.CoinMarket;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    @BindView(R.id.slika)
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
    RestApi api;
    CoinMarket detali;
    int pozicija = 0;
    ArrayList<CoinMarket> markets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        api = new RestApi(this);


        final Intent intent = getIntent();
        if (intent.hasExtra("details")) {
            Call <ArrayList<CoinMarket>> call = api.getBtc(intent.getStringExtra("details"));
            pozicija = intent.getIntExtra("details",0);
            call.enqueue(new Callback<ArrayList<CoinMarket>>() {
                @Override
                public void onResponse(Call<ArrayList<CoinMarket>> call, Response<ArrayList<CoinMarket>> response) {
                    if (response.code() == 200) {
                        markets = response.body();
                        detali=markets.get(0);

                        pozicija = intent.getIntExtra("pozicija", 0);
                        name.setText(detali.getName().toString());
                        symbol.setText(detali.getSymbol().toString());
                        price_ud.setText(detali.getPrice_usd()+"");
                        price_btc.setText(detali.getPrice_btc()+"");
                        Picasso.with(Details.this).load("https://files.coinmarketcap.com/static/img/coins/64x64/" + detali.getId() + ".png").centerInside().fit().into(slika);
                    } else if (response.code() == 401) {
                        Toast.makeText(Details.this, "Greska", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<CoinMarket>> call, Throwable t) {
                }
            });
        }
    }
}
