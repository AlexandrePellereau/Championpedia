package com.alexpell.championpedia.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alexpell.championpedia.Champion;

@Dao
public interface ChampionDAO {

    @Insert
    void insert(Champion entity);

    @Update
    void update(Champion entity);

    @Delete
    void delete(Champion entity);

    @Query("SELECT * FROM " + AppDataBase.CHAMPIONS_TABLE + " WHERE champion_name = :name")
    Champion getChampionByName(String name);


}