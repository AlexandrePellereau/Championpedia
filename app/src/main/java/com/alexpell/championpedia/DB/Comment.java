package com.alexpell.championpedia.DB;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
/*
@Entity(tableName = AppDataBase.COMMENT_TABLE,
        foreignKeys = @ForeignKey(entity = Champion.class,
                parentColumns = "id",
                childColumns = "championId"))

 */
@Entity(tableName = AppDataBase.COMMENT_TABLE)
public class Comment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int championId;
    private int userId;
    private String content;
    private String publicationDate;

    public Comment(int championId, int userId, String content, String publicationDate) {
        this.championId = championId;
        this.userId = userId;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", championId=" + championId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}
