package com.oliver.coinmarket.onClick;

import com.oliver.coinmarket.klasi.CoinMarket;
import com.oliver.coinmarket.modeli.CoinModel;

import java.util.ArrayList;

/**
 * Created by Oliver on 1/22/2018.
 */

public interface OnClick {
    public void onRowClick(CoinMarket favorites, int position );
}
