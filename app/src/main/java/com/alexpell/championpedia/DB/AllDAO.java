package com.alexpell.championpedia.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alexpell.championpedia.champion.Champion;

import java.util.List;

@Dao
public interface AllDAO {

    //Users
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



    //Comments
    @Insert
    void insert(Comment... comment);

    @Update
    void update(Comment... comment);

    @Delete
    void delete(Comment comment);

    @Query("SELECT * FROM " + AppDataBase.COMMENT_TABLE + " WHERE championId = :championId")
    List<Comment> getCommentsByChampion(int championId);

    @Query("DELETE FROM " + AppDataBase.COMMENT_TABLE + " WHERE id = :commentId")
    void deleteComment(int commentId);

    @Query("SELECT * FROM " + AppDataBase.COMMENT_TABLE + " ORDER BY id DESC LIMIT 1")
    Comment getLastComment();



    //Champions
    @Insert
    void insert(Champion entity);

    @Update
    void update(Champion entity);

    @Delete
    void delete(Champion entity);

    @Query("SELECT * FROM " + AppDataBase.CHAMPIONS_TABLE + " WHERE id = :id")
    Champion getChampionById(Integer id);

    @Query("SELECT * FROM " + AppDataBase.CHAMPIONS_TABLE + " WHERE champion_name = :name")
    Champion getChampionByName(String name);

    @Query("DELETE FROM " + AppDataBase.CHAMPIONS_TABLE)
    void boom();

    @Insert
    void insert(Review entity);

    @Update
    void update(Review entity);

    @Delete
    void delete(Review review);

    @Query("SELECT difficulty FROM " + AppDataBase.REVIEWS_TABLE + " WHERE champion = :name")
    List<Integer> getChampionsReviewDifficulty(String name);

    @Query("SELECT fun FROM " + AppDataBase.REVIEWS_TABLE + " WHERE champion = :name")
    List<Integer> getChampionsReviewFun(String name);

    @Query("SELECT * FROM " + AppDataBase.REVIEWS_TABLE + " WHERE champion = :name AND username = :username")
    Review getReview(String username, String name);
}
