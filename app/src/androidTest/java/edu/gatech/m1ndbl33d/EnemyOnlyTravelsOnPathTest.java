package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.model.MapTile;
import edu.gatech.m1ndbl33d.model.Path;
import edu.gatech.m1ndbl33d.model.Position;

/**
 * M4: The enemy should not be able to travel on tiles that are not paths. This generates the
 * enemy's sequence and checks against the mapEngine's map to ensure that each tile
 * in the sequence is an instance of the path class.
 */

@RunWith(AndroidJUnit4.class)
public class EnemyOnlyTravelsOnPathTest {
    @Test
    public void testPathSequence() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine mapEngine = new MapEngine(appContext, "map1");
        mapEngine.initializeMap(appContext);

        List<Position> pathSequence = mapEngine.generatePathSequence();

        Position sampleOne = pathSequence.get(0);
        Position sampleThree = pathSequence.get(3);
        Position sampleTen = pathSequence.get(10);

        List<Position> samples = new ArrayList<>(Arrays.asList(sampleOne, sampleThree, sampleTen));

        for (Position sample: samples) {
            MapTile tile = mapEngine.getTile(sample.getX(), sample.getY());
            assertTrue(tile instanceof Path);
        }

    }
}
