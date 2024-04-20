package com.alexpell.championpedia;

import androidx.room.PrimaryKey;

public class LoginLog {

    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    private String mLogin;
    private String mPassword;

    public LoginLog(String login, String password) {
        mLogin = login;
        mPassword = password;
    }

    @Override
    public String toString() {
        return "GymLog{" +
                "mLogin='" + mLogin + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
