package com.alexpell.championpedia;

import android.content.Context;

import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.ChampionDAO;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Initialise {

    public static void initialiseDB(Context context) throws XmlPullParserException, IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.champion_data);

        addChampions(context, XmlParser.parseChampions(inputStream));

    }

    public static void addChampions(Context context, List<Champion> champions){
        AppDataBase database = AppDataBase.getInstance(context);
        ChampionDAO championDAO = database.championsDAO();

        for (Champion champion : champions) {
            championDAO.insert(champion);
        }
    }
}
