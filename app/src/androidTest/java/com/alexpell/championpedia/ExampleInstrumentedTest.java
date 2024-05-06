package com.alexpell.championpedia;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.champion.ContextProvider;
import com.alexpell.championpedia.champion.Initialise;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.alexpell.championpedia", appContext.getPackageName());
    }

    @Test
    public void testFillChampionTable() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AllDAO allDAO = AppDataBase.getInstance(appContext).getAllDAO();
        ContextProvider.initialize(appContext);
        try {
            Initialise.initialiseDB(appContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(allDAO.getChampionByName("Vel'Koz"));
        assertNotNull(allDAO.getChampionByName("Zed"));
    }
}