package com.alexpell.championpedia;

import android.content.Context;
import android.widget.Toast;

import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.ChampionDAO;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Initialise {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static void initialiseDB(Context context) throws XmlPullParserException, IOException {
        List<String> parse = XmlParser.parseChampions(context,R.xml.champion_data);
        List<Champion> champions = new ArrayList<>();
        Champion champion= new Champion();
        for(int i = 0; i< parse.size();i++){
            switch (i%8){
                case 0:
                    champion= new Champion();
                    champion.setName(parse.get(i));
                    break;
                case 1:
                    champion.setLore(parse.get(i));
                    break;
                case 2:
                   champion.setFull_lore(parse.get(i));
                   break;
                case 3:
                    champion.setPickrate(Double.parseDouble(parse.get(i)));
                    break;
                case 4:
                    champion.setBanrate(Double.parseDouble(parse.get(i)));
                    break;
                case 5:
                    champion.setWinrate(Double.parseDouble(parse.get(i)));
                    break;
                case 6:
                    champion.setDifficulty(Integer.parseInt(parse.get(i)));
                    break;
                case 7:
                    champion.setFun(Integer.parseInt(parse.get(i)));
                    champions.add(champion);
                    break;
            }

        }
        Toast.makeText(context,"nice",Toast.LENGTH_SHORT).show();
        boomTable(context);
        addChampions(context, champions);

    }

    public static void addChampions(Context context, List<Champion> champions){
        AppDataBase database = AppDataBase.getInstance(context);
        ChampionDAO championDAO = database.championsDAO();
        databaseWriteExecutor.execute(() -> {
            for (Champion champion : champions) {
                championDAO.insert(champion);
            }
        });
    }

    public static void boomTable(Context context){
        AppDataBase database = AppDataBase.getInstance(context);
        ChampionDAO championDAO = database.championsDAO();
        championDAO.boom();
    }


}
