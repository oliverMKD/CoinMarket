package com.oliver.coinmarket.api;

import com.oliver.coinmarket.klasi.CoinMarket;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Oliver on 1/21/2018.
 */

public interface ApiService {

    @GET("ticker")
    Call<ArrayList<CoinMarket>> getCoins();

}
