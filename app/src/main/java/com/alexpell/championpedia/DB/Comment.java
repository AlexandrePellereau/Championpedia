package com.alexpell.championpedia.DB;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.USER_TABLE,
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "mLogId",
                childColumns = "userId"))
public class Comment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int championId;
    private int userId;
    private String content;
    private boolean publicationDate;

    public Comment(int championId, int userId, String content, boolean publicationDate) {
        this.championId = championId;
        this.userId = userId;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", championId=" + championId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", publicationDate=" + publicationDate +
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

    public boolean isPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(boolean publicationDate) {
        this.publicationDate = publicationDate;
    }
}
