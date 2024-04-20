package com.alexpell.championpedia.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alexpell.championpedia.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.LOGINLOG_TABLE)
    List<User> getLoginLogs();

    @Query("SELECT * FROM " + AppDataBase.LOGINLOG_TABLE + " WHERE mLogID = :logId")
    List<User> getLoginLog(int logId);
}
