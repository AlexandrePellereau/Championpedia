package com.alexpell.championpedia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.alexpell.championpedia.champion.Champion;

import org.junit.Test;

public class ChampionUnitTest {
    @Test
    public void testChampionConstructor() {
        Champion champion = new Champion(19,"TestChampion","Good","Very Good",50,50,80,5,5);
        assertNotNull(champion);
    }

    @Test
    public void testChampionGetters() {
        String name = "Goat";
        String lore = "The best";
        String full_lore = "Unbelievable";
        double pickrate = 50;
        double banrate = 50;
        double winrate = 100;
        int difficulty = 5;
        int fun = 5;
        Champion champion = new Champion(1, name, lore, full_lore, pickrate, banrate, winrate, difficulty, fun);
        assertEquals(pickrate, champion.getPickrate());
        assertEquals(banrate, champion.getBanrate());
        assertEquals(lore, champion.getLore());
        assertEquals(name, champion.getName());
        assertEquals(difficulty, champion.getDifficulty());
        assertEquals(fun, champion.getFun());
        assertNotEquals(fun + 1, champion.getFun());
    }
}
