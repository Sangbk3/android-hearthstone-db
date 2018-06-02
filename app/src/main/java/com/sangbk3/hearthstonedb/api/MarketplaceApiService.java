package com.sangbk3.hearthstonedb.api;

import com.sangbk3.hearthstonedb.model.Card;
import com.sangbk3.hearthstonedb.model.CardResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MarketplaceApiService {

    @GET("cards")
    Call<HashMap<String,List<Card>>> getAllCards(@Header("X-Mashape-Key") String apiKey);
}
