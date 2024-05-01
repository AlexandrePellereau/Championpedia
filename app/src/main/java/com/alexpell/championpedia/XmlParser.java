package com.alexpell.championpedia;

import com.alexpell.championpedia.Champion;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public static List<Champion> parseChampions(InputStream inputStream) throws XmlPullParserException, IOException {
        List<Champion> champions = new ArrayList<>();

        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);

        int eventType = parser.getEventType();
        Champion champion = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("champion".equals(tagName)) {
                        champion = new Champion();
                    }
                    break;

                case XmlPullParser.TEXT:
                    String text = parser.getText();
                    if (champion != null) {
                        switch (tagName) {
                            case "name":
                                champion.setName(text);
                                break;
                            case "lore":
                                champion.setLore(text);
                                break;
                            case "full_lore":
                                champion.setFull_lore(text);
                                break;
                            case "pickrate":
                                champion.setPickrate(Double.parseDouble(text));
                                break;
                            case "banrate":
                                champion.setBanrate(Double.parseDouble(text));
                                break;
                            case "winrate":
                                champion.setWinrate(Double.parseDouble(text));
                                break;
                            case "difficulty":
                                champion.setDifficulty(Integer.parseInt(text));
                                break;
                        }
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("champion".equals(tagName)) {
                        champions.add(champion);
                        champion = null;
                    }
                    break;
            }
            eventType = parser.next();
        }

        return champions;
    }
}
