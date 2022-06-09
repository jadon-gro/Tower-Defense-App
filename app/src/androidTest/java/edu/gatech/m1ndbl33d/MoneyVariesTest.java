package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertFalse;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.controller.PlayerEngine;

/**
 * M2: Money should vary based on difficulty level
 */
@RunWith(AndroidJUnit4.class)
public class MoneyVariesTest {
    @Test
    public void checkHealth() {
        PlayerEngine.initializePlayer("Tan", "Easy");
        int easyMoney = PlayerEngine.getPlayerMoney();

        PlayerEngine.initializePlayer("Suha", "Medium");
        int mediumMoney = PlayerEngine.getPlayerMoney();

        PlayerEngine.initializePlayer("Jadon", "Hard");
        int hardMoney = PlayerEngine.getPlayerMoney();

        assertFalse(easyMoney == mediumMoney);
        assertFalse(hardMoney == easyMoney);
        assertFalse(hardMoney == mediumMoney);

    }
}
