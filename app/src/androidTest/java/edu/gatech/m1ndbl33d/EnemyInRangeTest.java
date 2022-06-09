package edu.gatech.m1ndbl33d;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.model.Position;

import static org.junit.Assert.assertNull;

/**
 * M5: Tower can only attack enemy that is in proximity. Tests whether enemies found by
 * a tower are truly within range.
 */
@RunWith(AndroidJUnit4.class)
public class EnemyInRangeTest {
    @Test
    public void testEnemyRange() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //Initialize one enemy
        EnemyEngine.initializeEnemy();

        //Create map
        MapEngine mapEngine = new MapEngine(appContext, "map1");
        mapEngine.initializeMap(appContext);
        //Generate the positions in the path of the map
        List<Position> pathSequence = mapEngine.generatePathSequence();

        //Set the position of first enemy to the start of path
        EnemyEngine.setEnemyPosition(0, 0);

        List<Integer> pathPositions = EnemyEngine.getEnemyPositionList();

        Integer target = mapEngine.findClosestEnemy(new Position(6, 6), 3, pathPositions);

        assertNull(target);

    }
}
