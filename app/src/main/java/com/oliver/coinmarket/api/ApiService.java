package com.oliver.coinmarket.api;

import com.oliver.coinmarket.klasi.CoinMarket;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Oliver on 1/21/2018.
 */

public interface ApiService {

    @GET("ticker")
    Call<ArrayList<CoinMarket>> getCoins(@Query("limit") int limit);

    @GET("ticker/{id}")
    Call<ArrayList<CoinMarket>> getBtc(@Path("id") String id);

//    @GET("ticker")
//    Call<ArrayList<CoinMarket>> getLimit(@Query("convert") String convert, @Query("limit") int limit);


}
