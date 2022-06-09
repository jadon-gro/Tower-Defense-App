package edu.gatech.m1ndbl33d;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.controller.MapEngine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * M2: This test confirms that the path can be created from a map string array
 */
@RunWith(AndroidJUnit4.class)
public class CreatePathTest {
    @Test
    public void createPath() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MapEngine map = new MapEngine(appContext, "map1");

        assertTrue(map.isEmpty(0, 0));
        assertFalse(map.isEmpty(3, 1));
    }
}