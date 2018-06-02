package com.sangbk3.hearthstonedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardResponse {

    @SerializedName("Basic")
    private List<Card> basic;

    @SerializedName("Classic")
    private List<Card> classic;

    @SerializedName("Promo")
    private List<Card> promo;

    @SerializedName("Hall of Fame")
    private List<Card> hallOfFame;

    @SerializedName("Naxxramas")
    private List<Card> naxxramas;

    @SerializedName("Goblins vs Gnomes")
    private List<Card> goblinsVsGnomes;

    @SerializedName("Blackrock Mountain")
    private List<Card> blackrockMountain;

}
