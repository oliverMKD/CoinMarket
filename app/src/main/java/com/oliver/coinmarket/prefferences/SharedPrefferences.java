package com.oliver.coinmarket.prefferences;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.oliver.coinmarket.modeli.CoinModel;

/**
 * Created by Oliver on 1/21/2018.
 */

public class SharedPrefferences {
    private static android.content.SharedPreferences getPreferences(Context c){
        return c.getApplicationContext().getSharedPreferences("UserPrefferences", Activity.MODE_PRIVATE);
    }

    public static void addFavorites (CoinModel model, Context c){
        Gson gson = new Gson();
        String mapString = gson.toJson(model);
        getPreferences(c).edit().putString("favorites", mapString).apply();
    }

    public static CoinModel getFavorites(Context c){

        return new Gson().fromJson(getPreferences(c).getString("favorites", ""), CoinModel.class);
    }

    public static void addLimit (int limit, Context c){
        getPreferences(c).edit().putInt("limit", limit).apply();
    }

    public static int getLimit (Context context){
        return getPreferences(context).getInt("limit",0);
    }
    public static void addConvert (String convert, Context c){
        getPreferences(c).edit().putString("convert", convert).apply();
    }
    public static String getConvert (Context context, String convert) {
        return getPreferences(context).getString("convert", convert);
    }
}

