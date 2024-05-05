package com.alexpell.championpedia.comment;

public class CommentModel {

    private int id;
    private String name;
    private String date;
    private String content;
    private int image;

    public CommentModel(int id, String name, String date, String content, int image) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
