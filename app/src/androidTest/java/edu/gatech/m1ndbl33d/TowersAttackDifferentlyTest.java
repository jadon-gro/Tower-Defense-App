package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.Position;
import edu.gatech.m1ndbl33d.model.Virus;


/**
 * M5: The Bug and Virus towers attack different enemies.
 */

@RunWith(AndroidJUnit4.class)
public class TowersAttackDifferentlyTest {
    @Test
    public void attack() {
        //Generate a map
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine map = new MapEngine(appContext, "map1");

        //Place a bug and virus tower on the map
        Position bugPos = new Position(2, 0);
        Position virusPos = new Position(4, 0);
        Bug bug = new Bug();
        Virus virus = new Virus();
        map.placeTowerByPosition(bugPos, bug);
        map.placeTowerByPosition(virusPos, virus);

        //Create a dummy list of enemy positions
        List<Integer> enemyPositions = new ArrayList<Integer>();
        enemyPositions.add(1);
        enemyPositions.add(2);
        enemyPositions.add(3);

        map.generatePathSequence();

        //Find all the targets of a virus tower
        List<Integer> virusTargets = map.findEnemiesInRange(virusPos,
                virus.getAttackRange(), enemyPositions);

        //Find all the targets of a bug tower
        Integer bugTarget = map.findClosestEnemy(bugPos,
                bug.getAttackRange(), enemyPositions);

        // Ensure that these targets are not the same
        Integer first = virusTargets.get(0);
        Integer second = virusTargets.get(1);

        assertTrue(bugTarget != first || bugTarget != second);
    }
}
