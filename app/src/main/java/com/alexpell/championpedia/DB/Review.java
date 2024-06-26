package com.alexpell.championpedia.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review_table")
public class Review {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "champion")
    private String champion_name;

    @ColumnInfo(name = "difficulty")
    private int difficulty;

    @ColumnInfo(name = "fun")
    private int fun;

    public Review(String username, String champion_name, int difficulty, int fun) {
        this.username = username;
        this.champion_name = champion_name;
        this.difficulty = difficulty;
        this.fun = fun;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChampion_name() {
        return champion_name;
    }

    public void setChampion_name(String champion_name) {
        this.champion_name = champion_name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getFun() {
        return fun;
    }

    public void setFun(int fun) {
        this.fun = fun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
