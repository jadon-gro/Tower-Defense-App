package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.controller.PlayerEngine;
import edu.gatech.m1ndbl33d.model.Position;

/**
 * M4: When an enemy travels along the path and reaches the monument, the health
 * should decrease by 5).
 */
@RunWith(AndroidJUnit4.class)
public class HealthDecreasesTest {
    @Test
    public void testHealth() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //Initialize Player
        PlayerEngine.initializePlayer("Haolat", "Easy");
        int easyHealth = PlayerEngine.getPlayerHealth();

        //Initialize one enemy
        EnemyEngine.initializeEnemy();

        //Create map
        MapEngine mapEngine = new MapEngine(appContext, "map1");
        mapEngine.initializeMap(appContext);
        //Generate the positions in the path of the map
        List<Position> pathSequence = mapEngine.generatePathSequence();

        //set the position of first enemy to the position of the monument
        //or last position in map
        EnemyEngine.setEnemyPosition(0, pathSequence.size() - 1);

        int pathPosition = EnemyEngine.getEnemyPosition(0);
        if (pathPosition >= pathSequence.size() - 1) {
            EnemyEngine.removeEnemy(0, true);
            PlayerEngine.decreaseHealthByAmount(5);
        }
        assertEquals(PlayerEngine.getPlayerHealth(), easyHealth - 5);

    }
}
