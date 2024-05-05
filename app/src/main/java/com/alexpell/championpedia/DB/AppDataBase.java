package com.alexpell.championpedia.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alexpell.championpedia.Champion;
import com.alexpell.championpedia.ContextProvider;
import com.alexpell.championpedia.Initialise;
import com.alexpell.championpedia.User;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

@Database(entities = {User.class, Champion.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {

    public static final String CHAMPIONS_TABLE = "champion_table";
    public static final String LOGINLOG_TABLE = "login_log";
    public static final String DATABASE_NAME = "LoginLog.DB";
    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO loginLogDAO();

    public abstract ChampionDAO championsDAO();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).allowMainThreadQueries().addCallback(addDefaultValues).fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            try {
                Initialise.initialiseDB(ContextProvider.getContext());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };
}
