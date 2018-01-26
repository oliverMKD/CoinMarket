package com.oliver.coinmarket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oliver.coinmarket.adapter.RecyclerAdapter;
import com.oliver.coinmarket.api.RestApi;
import com.oliver.coinmarket.klasi.CoinMarket;
import com.oliver.coinmarket.modeli.CoinModel;
import com.oliver.coinmarket.onClick.OnClick;
import com.oliver.coinmarket.prefferences.SharedPrefferences;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar;
    RecyclerAdapter mAdapter;
    Context context;
    RestApi api;
    ArrayList<CoinMarket> coinMarkets;
    public CoinModel model;
    OnClick onClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        model = new CoinModel();
        context = this;
        api = new RestApi(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,1));

        int limit = SharedPrefferences.getLimit(context);

        Call<ArrayList<CoinMarket>> call = api.getCoins(limit);
        call.enqueue(new Callback<ArrayList<CoinMarket>>() {
            @Override
            public void onResponse(Call<ArrayList<CoinMarket>> call, Response<ArrayList<CoinMarket>> response) {

                if (response.isSuccessful()) {
                    coinMarkets = response.body();
                    mAdapter = new RecyclerAdapter(coinMarkets,context,onClick);
                    mRecyclerView.setAdapter(mAdapter);
                    progressBar.setVisibility(View.GONE);

                } else {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }


            }

            @Override
            public void onFailure(Call<ArrayList<CoinMarket>> call, Throwable t) {
                Toast.makeText(Main2Activity.this, "failure", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);


                    }
                });

//            mAdapter =  new RecyclerAdapter(favorites, context,onClick) {
//            @Override
//            public void onClick(CoinModel model, int position) {
//                Intent intent;
//                intent = new Intent(Main2Activity.this,MainActivity.class);
//                intent.putExtra("novoextra",favorites);
//                setResult(RESULT_OK,intent);
//                finish();
//
//            }
//        });

    }

    @Override
    public void onBackPressed() {

            SharedPrefferences.addFavorites(model,context);
            Intent intent = new Intent();
//            intent.putExtra("fav",1000);
            setResult(RESULT_OK,intent);
            finish();



    }
}

