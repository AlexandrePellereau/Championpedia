package com.alexpell.championpedia.landing_page;

public class ChampionModel {
    private String name;
    private int image;

    public ChampionModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
