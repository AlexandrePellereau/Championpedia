package com.alexpell.championpedia.DB;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alexpell.championpedia.champion.Champion;
import com.alexpell.championpedia.champion.ContextProvider;
import com.alexpell.championpedia.champion.Initialise;

@Database(entities = {User.class, Comment.class, Champion.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static final String USER_TABLE = "user";
    public static final String COMMENT_TABLE = "comment";
    public static final String CHAMPIONS_TABLE = "champion_table";
    public static final String DATABASE_NAME = "Championpedia.DB";
    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract AllDAO getAllDAO();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                            .allowMainThreadQueries().
                            addCallback(addDefaultValues).
                            fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //Not working
            Log.d("AppDataBase", "onCreate: ");
            try {
                Initialise.initialiseDB(ContextProvider.getContext());
                Log.d("AppDataBase", "onCreate: DB initialised");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };
}
