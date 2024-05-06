package com.alexpell.championpedia.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review_table")
public class Review {
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "champion")
    private String champion_name;

    @ColumnInfo(name = "difficulty")
    private int difficulty;

    @ColumnInfo(name = "fun")
    private int fun;

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
}
