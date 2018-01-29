package com.oliver.coinmarket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.oliver.coinmarket.adapter.RV_Adapter;
import com.oliver.coinmarket.adapter.RecyclerAdapter;
import com.oliver.coinmarket.klasi.CoinMarket;
import com.oliver.coinmarket.modeli.CoinModel;
import com.oliver.coinmarket.onClick.OnClick;
import com.oliver.coinmarket.prefferences.SharedPrefferences;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    int kluc = 1000;
    RV_Adapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    public CoinModel model;
    public ArrayList<CoinMarket> favorites;
    Context context;
    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,1));

        model = SharedPrefferences.getFavorites(this);
        adapter = new RV_Adapter(model.favorites, context, new OnClick() {
            @Override
            public void onRowClick(CoinMarket market, int position) {
                market = model.favorites.get(position);
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("details", market.getId());
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent, kluc);
            }
        });


    }
    ArrayList<CoinMarket> getList() {

        model = SharedPrefferences.getFavorites(this);
        if (model.favorites !=null && model.favorites.isEmpty()) {
            Toast.makeText(this, "empty ", Toast.LENGTH_SHORT).show();
        } else {

        }return model.favorites;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1000&&resultCode==RESULT_OK);{

            model = SharedPrefferences.getFavorites(this);
            adapter.setItems(getList());
//            adapter = new RV_Adapter(model.favorites,context);
            mRecyclerView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent2 = new Intent(this,Settings.class);
            startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.FIREBASENOTIF");
        mReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    String msg_for_me = intent.getStringExtra("notification");
                    String notificationBody = intent.getStringExtra("notificationbody");
                    if (msg_for_me!=null&&!msg_for_me.equals(""))
                        handleFirebaseNotification(msg_for_me,notificationBody);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        registerReceiver(mReceiver,intentFilter);
    }
    public void handleFirebaseNotification(String notification, String notificationBody){
        Toast.makeText(this, notification+"-//-"+notificationBody, Toast.LENGTH_SHORT).show();
    }
}
