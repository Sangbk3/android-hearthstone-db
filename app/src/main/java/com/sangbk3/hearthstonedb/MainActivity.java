package com.sangbk3.hearthstonedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sangbk3.hearthstonedb.adapter.CardsAdapter;
import com.sangbk3.hearthstonedb.api.ApiManager;
import com.sangbk3.hearthstonedb.model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Card> cards;
    private RecyclerView recyclerView;
    private CardsAdapter cardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        cards = new ArrayList<>();

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardsAdapter = new CardsAdapter(cards, R.layout.list_cards, getApplicationContext());
        recyclerView.setAdapter(cardsAdapter);

        Call<HashMap<String, List<Card>>> call = ApiManager.getInstance().getMarketplaceApiService().getAllCards(ApiManager.apiKey);
        call.enqueue(new Callback<HashMap<String, List<Card>>>() {
            @Override
            public void onResponse(Call<HashMap<String, List<Card>>> call, Response<HashMap<String, List<Card>>> response) {
                cards = response.body().get("Basic");
                cardsAdapter.setCards(cards);
                cardsAdapter.notifyDataSetChanged();

                Log.d("SANGLOG:", "NUMBER OF CARDS: " + cards.size());
            }

            @Override
            public void onFailure(Call<HashMap<String, List<Card>>> call, Throwable t) {
                Log.d("SANGERR:", "ERROR LOADING DATA: "+t.toString());
            }
        });
    }
}
