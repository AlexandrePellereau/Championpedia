package com.alexpell.championpedia.champion;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static List<String> parseChampions(Context context, int xmlFile){
        List<String> champions = new ArrayList<>();
        Resources resources = context.getResources();
        XmlResourceParser parser = resources.getXml(xmlFile);
       try {
           while (parser.next() != XmlPullParser.END_DOCUMENT) {
               if (parser.getEventType() == XmlPullParser.START_TAG &&  !parser.getName().equals("champion") && !parser.getName().equals("champions")) {
                   champions.add(parser.nextText());
                  /* Champion champion = new Champion();
                   for(int i = 0; i<7;i++){
                       switch (i){
                           case 0:
                               champion.setName(parser.nextText());
                               break;
                           case 1:
                               champion.setLore(parser.nextText());
                               break;
                           case 2:
                               champion.setFull_lore(parser.nextText());
                               break;
                           case 3:
                               champion.setPickrate(Double.parseDouble(parser.nextText()));
                               break;
                           case 4:
                               champion.setBanrate(Double.parseDouble(parser.nextText()));
                               break;
                           case 5:
                               champion.setWinrate(Double.parseDouble(parser.nextText()));
                               break;
                           case 6:
                               champion.setDifficulty(Integer.parseInt(parser.nextText()));
                               break;
                       }

                   }
                   champions.add(champion);*/
               }
           }
           return champions;

       }
       catch (Exception e){
           return champions;
       }
    }
}
