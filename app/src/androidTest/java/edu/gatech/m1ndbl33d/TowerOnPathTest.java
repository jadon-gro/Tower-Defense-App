package edu.gatech.m1ndbl33d;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.EmptyTile;
import edu.gatech.m1ndbl33d.model.Tower;

import static org.junit.Assert.assertFalse;

/**
 * M3: This test confirms that towers are not allowed to be placed on the path.
 */
@RunWith(AndroidJUnit4.class)
public class TowerOnPathTest {
    @Test
    public void towerOnPath() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine map = new MapEngine(appContext, "map1");
        Tower bug = new Bug();
        if (map.getTile(3, 1) instanceof EmptyTile) {
            map.placeTower(3, 1, bug);
        }
        assertFalse(map.getTile(3, 1) instanceof Tower);
    }
}
