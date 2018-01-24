package com.oliver.coinmarket.klasi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Oliver on 1/21/2018.
 */

public class CoinMarket implements Serializable {

    String id;
    String name;
    String symbol;
    double price_usd;
    double price_btc;
    int rank;
    @SerializedName("24h_volume_usd")
    public double h_volume_usd;

    public CoinMarket() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    public double getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(double price_btc) {
        this.price_btc = price_btc;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getH_volume_usd() {
        return h_volume_usd;
    }

    public void setH_volume_usd(double h_volume_usd) {
        this.h_volume_usd = h_volume_usd;
    }
}
