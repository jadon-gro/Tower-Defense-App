package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertFalse;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.controller.PlayerEngine;

/**
 * M2: Monument health should vary based on difficulty level
 */
@RunWith(AndroidJUnit4.class)
public class HealthVariesTest {
    @Test
    public void checkHealth() {
        PlayerEngine.initializePlayer("Tan", "Easy");
        int easyHealth = PlayerEngine.getPlayerHealth();

        PlayerEngine.initializePlayer("Suha", "Medium");
        int mediumHealth = PlayerEngine.getPlayerHealth();

        PlayerEngine.initializePlayer("Jadon", "Hard");
        int hardHealth = PlayerEngine.getPlayerHealth();

        assertFalse(easyHealth == mediumHealth);
        assertFalse(hardHealth == mediumHealth);
        assertFalse(hardHealth == easyHealth);

    }
}
