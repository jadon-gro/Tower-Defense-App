package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.MapTile;
import edu.gatech.m1ndbl33d.model.Tower;
import edu.gatech.m1ndbl33d.model.Virus;

/**
 * M3: Users should be able to place towers on a map
 */
@RunWith(AndroidJUnit4.class)
public class PlaceTowerAfterPurchaseTest {
    @Test
    public void place() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        boolean success = TowerEngine.addNewTower("virus", 4000);
        Stack<Tower> towerStack = TowerEngine.getTowerStack();
        Tower tower = towerStack.pop();
        MapEngine map = new MapEngine(appContext, "map1");
        int x = 2;
        int y = 2;
        map.placeTower(x, y, tower);
        MapTile tile = map.getTile(x, y);
        assertTrue(tile instanceof Virus);

    }
}
