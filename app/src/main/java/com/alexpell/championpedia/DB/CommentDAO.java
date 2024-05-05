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
    void insert(Comment... comment);

    @Update
    void update(Comment... comment);

    @Delete
    void delete(Comment comment);

    @Query("SELECT * FROM " + AppDataBase.COMMENT_TABLE + " WHERE championId = :championId")
    List<Comment> getComments(int championId);

}
