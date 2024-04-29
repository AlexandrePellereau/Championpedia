package com.alexpell.championpedia.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alexpell.championpedia.DB.AppDataBase;

@Entity(tableName = AppDataBase.LOGINLOG_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    private String mUsername;
    private String mEmail;
    private String mPassword;
    private boolean mIsAdmin;

    public User(String username, String email, String password, Boolean isAdmin) {
        mUsername = username;
        mEmail = email;
        mPassword = password;
        mIsAdmin = isAdmin;
    }

    @Override
    public String
    toString() {
        return "LoginLog{" +
                "mLogId=" + mLogId +
                ", mUsername='" + mUsername + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mIsAdmin=" + mIsAdmin +
                '}';
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        mIsAdmin = admin;
    }
}
