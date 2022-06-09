package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.controller.EnemyEngine;

/**
 * M6: 3 statistics must be displayed at the end of the game, including the kill statistic.
 */

@RunWith(AndroidJUnit4.class)
public class KillStatisticIsCorrectTest {
    @Test
    public void testKills() {
        EnemyEngine.clearEnemies();
        EnemyEngine.setNumEnemiesRemoved(0);
        EnemyEngine.initializeEnemy();
        EnemyEngine.removeEnemy(0, true);
        int removed = EnemyEngine.getNumEnemiesRemoved();
        assertEquals(removed, 1);

    }
}
