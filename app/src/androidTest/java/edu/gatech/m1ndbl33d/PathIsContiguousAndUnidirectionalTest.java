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
import edu.gatech.m1ndbl33d.model.Position;

/**
 * M4: When an enemy travels along the path, it should only travel one tile at a time.
 * Otherwise, the movement of the enemy is invalid. Therefore, on the path sequence, every position
 * should have only one logical next step contiguous to the current position. Thus, it is contiguous
 * and unidirectional (in terms of enemy movement not screen position).
 */
@RunWith(AndroidJUnit4.class)
public class PathIsContiguousAndUnidirectionalTest {
    @Test
    public void testPath() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine mapEngine = new MapEngine(appContext, "map1");
        mapEngine.initializeMap(appContext);
        List<Position> pathSequence = mapEngine.generatePathSequence();

        Position sampleOne = pathSequence.get(0);
        Position sampleTwo = pathSequence.get(1);

        List<Position> newPathSequence = new ArrayList<>();
        newPathSequence.add(sampleOne);
        List<Position> sampleOneNeighbors = mapEngine.generateNeighbors(sampleOne, newPathSequence);
        assertTrue(sampleOneNeighbors.contains(sampleTwo));

        Position sampleFive = pathSequence.get(5);
        Position sampleSix = pathSequence.get(6);

        List<Position> anotherPathSequence = new ArrayList<>();
        anotherPathSequence.add(sampleFive);
        List<Position> sampleFiveNeighbors = mapEngine.generateNeighbors(sampleFive,
                anotherPathSequence);
        assertTrue(sampleFiveNeighbors.contains(sampleSix));


    }
}
