package com.alexpell.championpedia.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private boolean admin;

    public User(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
