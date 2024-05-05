package com.alexpell.championpedia.champion;

import android.content.Context;

public class ContextProvider {

    private static Context context;

    private ContextProvider(){}

    public static void initialize(Context ct){
        context = ct;
    }

    public static Context getContext() {
        return context;
    }
}
