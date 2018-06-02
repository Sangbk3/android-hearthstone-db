package com.sangbk3.hearthstonedb.api;

import com.sangbk3.hearthstonedb.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiManager apiManager = null;
    private Retrofit retrofit = null;
    private MarketplaceApiService marketplaceApiService = null;

    public static final String BASE_URL = "https://omgvamp-hearthstone-v1.p.mashape.com/";
    public static String apiKey = BuildConfig.hearthstone_db_api_key;

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
            apiManager.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiManager.marketplaceApiService = apiManager.retrofit.create(MarketplaceApiService.class);
        }

        return apiManager;
    }

    public MarketplaceApiService getMarketplaceApiService() {
        return marketplaceApiService;
    }
}
