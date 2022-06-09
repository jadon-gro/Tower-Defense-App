package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.Tower;

/**
 * M3: One purchase corresponds to one tower. Else, placement would fail.
 */
@RunWith(AndroidJUnit4.class)
public class PurchaseLimitedToOneTowerTest {
    @Test
    public void purchase() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        boolean success = TowerEngine.addNewTower("virus", 4000);
        Stack<Tower> towerStack = TowerEngine.getTowerStack();
        Tower tower = towerStack.pop();
        boolean empty = towerStack.empty();
        assertTrue(empty);
    }
}
