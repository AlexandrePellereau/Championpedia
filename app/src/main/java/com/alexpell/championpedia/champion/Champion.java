package com.alexpell.championpedia.champion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "champion_table")
public class Champion {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "champion_name")
    private String name;

    @ColumnInfo(name = "champion_lore")
    private String lore;

    @ColumnInfo(name = "full_lore")
    private String full_lore;

    @ColumnInfo(name = "champion_pickrate")
    private double pickrate;

    @ColumnInfo(name = "champion_banrate")
    private double banrate;

    @ColumnInfo(name = "champion_winrate")
    private double winrate;

    @ColumnInfo(name = "champion_difficulty")
    private int difficulty;

    @ColumnInfo(name = "champion_fun")
    private int fun;

    public Champion() {
        difficulty = 0;
        fun = 0;
    }

    public Champion(int id, String name, String lore, String full_lore, double pickrate, double banrate, double winrate, int difficulty, int fun) {
        this.id = id;
        this.name = name;
        this.lore = lore;
        this.full_lore = full_lore;
        this.pickrate = pickrate;
        this.banrate = banrate;
        this.winrate = winrate;
        this.difficulty = difficulty;
        this.fun = fun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getFull_lore() {
        return full_lore;
    }

    public void setFull_lore(String full_lore) {
        this.full_lore = full_lore;
    }

    public double getPickrate() {
        return pickrate;
    }

    public void setPickrate(double pickrate) {
        this.pickrate = pickrate;
    }

    public double getBanrate() {
        return banrate;
    }

    public void setBanrate(double banrate) {
        this.banrate = banrate;
    }

    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
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
