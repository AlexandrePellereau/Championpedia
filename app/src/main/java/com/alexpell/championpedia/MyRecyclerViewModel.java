package com.alexpell.championpedia;

public class MyRecyclerViewModel {
    private String name;
    private String date;
    private String content;
    private int image;

    public MyRecyclerViewModel(String name, String date, String content, int image) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
    }
}
