package com.oliver.coinmarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.oliver.coinmarket.adapter.RV_Adapter;
import com.oliver.coinmarket.api.RestApi;
import com.oliver.coinmarket.klasi.CoinMarket;
import com.oliver.coinmarket.modeli.CoinModel;
import com.oliver.coinmarket.prefferences.SharedPrefferences;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settings extends AppCompatActivity {
    @BindView(R.id.seek1)
    SeekBar euro;
    @BindView(R.id.limit)
    TextView limit;
    @BindView(R.id.kopce)
    Button kopce_euro;
    @BindView(R.id.kopce1)
    Button kopce_limit;
    Context context;
    RestApi api;
    ArrayList<CoinMarket> coinMarkets;
    RV_Adapter mAdapter;
    public CoinModel model;
    OnClick onClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        context = this;

        euro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                limit.setText("Progress : "+progress);
                euro.setMax(100);
                SharedPrefferences.addLimit(progress, context);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @OnClick(R.id.kopce)
        public void Klik (View v){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


        }

}
