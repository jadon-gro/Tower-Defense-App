package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.m1ndbl33d.controller.MapEngine;
import edu.gatech.m1ndbl33d.model.Position;

/**
 * M4: Each path position should have only one valid neighbor to travel on next. Otherwise,
 * the movement of the enemy is invalid as there are multiple options for them to travel,
 * which is not allowed in the present path. This checks the neighbor of path positions
 * to make sure that the neighbor is seen only once in the path.
 */
@RunWith(AndroidJUnit4.class)
public class PathPositionsHaveOnlyOneValidNeighborTest {
    @Test
    public void testPathPosition() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine mapEngine = new MapEngine(appContext, "map1");
        mapEngine.initializeMap(appContext);
        List<Position> pathSequence = mapEngine.generatePathSequence();

        Position sampleOne = pathSequence.get(0);

        int row = sampleOne.getX();
        int col = sampleOne.getY();
        int gridWidth = mapEngine.getGridWidth();
        int gridHeight = mapEngine.getGridHeight();

        Position topPosition = new Position(row - 1, col);
        Position rightPosition = new Position(row, col + 1);
        Position bottomPosition = new Position(row + 1, col);
        Position leftPosition = new Position(row, col - 1);

        if (pathSequence.contains(topPosition)) {
            assertFalse(pathSequence.contains(rightPosition));
            assertFalse(pathSequence.contains(bottomPosition));
            assertFalse(pathSequence.contains(leftPosition));
        } else if (pathSequence.contains(rightPosition)) {
            assertFalse(pathSequence.contains(topPosition));
            assertFalse(pathSequence.contains(bottomPosition));
            assertFalse(pathSequence.contains(leftPosition));
        } else if (pathSequence.contains(bottomPosition)) {
            assertFalse(pathSequence.contains(topPosition));
            assertFalse(pathSequence.contains(rightPosition));
            assertFalse(pathSequence.contains(leftPosition));
        } else if (pathSequence.contains(leftPosition)) {
            assertFalse(pathSequence.contains(topPosition));
            assertFalse(pathSequence.contains(rightPosition));
            assertFalse(pathSequence.contains(bottomPosition));
        }



    }
}
