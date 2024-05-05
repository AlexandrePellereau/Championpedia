package com.alexpell.championpedia.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AllDAO {

    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE id = :userId")
    User getUser(int userId);

    @Query("DELETE FROM " + AppDataBase.USER_TABLE + " WHERE id = :userId")
    void deleteUser(int userId);

    @Insert
    void insert(Comment... comment);

    @Update
    void update(Comment... comment);

    @Delete
    void delete(Comment comment);

    @Query("SELECT * FROM " + AppDataBase.COMMENT_TABLE + " WHERE championId = :championId")
    List<Comment> getComments(int championId);
}
