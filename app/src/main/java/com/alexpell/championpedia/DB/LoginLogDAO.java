package com.alexpell.championpedia.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alexpell.championpedia.LoginLog;

import java.util.List;

@Dao
public interface LoginLogDAO {

    @Insert
    void insert(LoginLog... loginLog);

    @Update
    void update(LoginLog... loginLog);

    @Delete
    void delete(LoginLog loginLog);

    @Query("SELECT * FROM " + AppDataBase.LOGINLOG_TABLE)
    List<LoginLog> getLoginLogs();

    @Query("SELECT * FROM " + AppDataBase.LOGINLOG_TABLE + " WHERE mLogID = :logId")
    List<LoginLog> getLoginLog(int logId);
}
