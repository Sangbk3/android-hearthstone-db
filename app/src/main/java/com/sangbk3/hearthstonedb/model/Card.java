package com.sangbk3.hearthstonedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Card {

    @SerializedName("cardId")
    private String cardId;

    @SerializedName("dbfId")
    private String dbfId;

    @SerializedName("name")
    private String name;

    @SerializedName("cardSet")
    private String cardSet;

    @SerializedName("type")
    private String type;

    @SerializedName("text")
    private String text;

    @SerializedName("playerClass")
    private String playerClass;

    @SerializedName("locale")
    private String locale;

    @SerializedName("faction")
    private String faction;

    @SerializedName("rarity")
    private String rarity;

    @SerializedName("attack")
    private int attack;

    @SerializedName("health")
    private int health;

    @SerializedName("race")
    private String race;

    @SerializedName("img")
    private String img;

    @SerializedName("imgGold")
    private String imgGold;

    public Card(String cardId, String dbfId, String name, String cardSet, String type, String text, String playerClass, String locale, String faction, String rarity, int attack, int health, String race, String img, String imgGold) {
        this.cardId = cardId;
        this.dbfId = dbfId;
        this.name = name;
        this.cardSet = cardSet;
        this.type = type;
        this.text = text;
        this.playerClass = playerClass;
        this.locale = locale;
        this.faction = faction;
        this.rarity = rarity;
        this.attack = attack;
        this.health = health;
        this.race = race;
        this.img = img;
        this.imgGold = imgGold;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDbfId() {
        return dbfId;
    }

    public void setDbfId(String dbfId) {
        this.dbfId = dbfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgGold() {
        return imgGold;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
    }
}
