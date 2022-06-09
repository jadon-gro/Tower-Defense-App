package edu.gatech.m1ndbl33d;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.model.Enemy;
import edu.gatech.m1ndbl33d.model.Position;

import static org.junit.Assert.assertEquals;

/**
 * M4: Starting from the beginning of the path, the enemy should be able to reach the end of path
 */

@RunWith(AndroidJUnit4.class)
public class EnemyTravelMonumentTest {

    @Test
    public void testEnemyMonument() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine mapEngine = new MapEngine(appContext, "map1");
        mapEngine.initializeMap(appContext);

        List<Position> pathSequence = mapEngine.generatePathSequence();

        EnemyEngine.initializeEnemy();
        Enemy enemy = EnemyEngine.getCurrentEnemies().get(0);

        int pathPosition = enemy.getPathPosition();
        while (pathPosition < pathSequence.size() - 1) {
            enemy.setPathPosition(pathPosition + 1);
            pathPosition = enemy.getPathPosition();
        }
        assertEquals(new Position(6, 1), pathSequence.get(pathPosition));
    }
}
