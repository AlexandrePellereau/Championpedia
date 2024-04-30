package com.alexpell.championpedia.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommentDAO {

    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.COMMENT_TABLE + " WHERE championId = :championId")
    List<Comment> getComments(int championId);

}
