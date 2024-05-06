package com.alexpell.championpedia;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Intent;

import com.alexpell.championpedia.champion.Champion;
import com.alexpell.championpedia.champion.ChampionActivity;
import com.alexpell.championpedia.champion.ContextProvider;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testParseString() {
        assertNotEquals("Vel'Koz", MainActivity.parseString("Vel'Koz"));
        assertNotEquals("Aatrox", MainActivity.parseString("Aatrox"));
        assertEquals("velkoz", MainActivity.parseString("Vel'Koz"));
        assertEquals("aatrox", MainActivity.parseString("Aatrox"));
    }

    @Test
    public void testGetImageFromUsername() {
        MainActivity.initImages();
        assertEquals(R.drawable.quinn, MainActivity.getImageFromUsername("admin"));
    }

}