package com.sangbk3.hearthstonedb;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.sangbk3.hearthstonedb.adapter.CardsAdapter;
import com.sangbk3.hearthstonedb.api.ApiManager;
import com.sangbk3.hearthstonedb.model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private List<Card> cards;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private CardsAdapter cardsAdapter;
    private FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hearthstone DB");

        cards = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        cardsAdapter = new CardsAdapter(cards, cards, R.layout.list_cards, getApplicationContext());
        recyclerView.setAdapter(cardsAdapter);

        Call<HashMap<String, List<Card>>> call = ApiManager.getInstance().getMarketplaceApiService().getAllCards(ApiManager.apiKey);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<HashMap<String, List<Card>>>() {
            @Override
            public void onResponse(Call<HashMap<String, List<Card>>> call, Response<HashMap<String, List<Card>>> response) {
                cards = new ArrayList<>();
                for (String key : response.body().keySet()) {
                    cards.addAll(response.body().get(key));
                }
                cardsAdapter.setCards(cards);
                cardsAdapter.notifyDataSetChanged();

                MainActivity.this.getSharedPreferences().edit().putString("HEARTHSTONE_CARDS", new Gson().toJson(response.body())).apply();

                progressBar.setVisibility(View.GONE);
                Log.d("SANGLOG:", "NUMBER OF CARDS: " + cards.size());
            }

            @Override
            public void onFailure(Call<HashMap<String, List<Card>>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                Log.d("SANGERR:", "ERROR LOADING DATA: "+t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager != null ?
                searchManager.getSearchableInfo(getComponentName()) : null);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("Sangbug", "onQueryTextSubmit: Querying based on query change");
                cardsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Sangbug", "onQueryTextChange: Query based on query change");
                cardsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
