package edu.gatech.m1ndbl33d;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.RootKit;
import edu.gatech.m1ndbl33d.model.Virus;

import static org.junit.Assert.assertNotEquals;

/**
 * M5: The range of attack of towers should vary
 */
@RunWith(AndroidJUnit4.class)
public class TowerRangeVaryTest {
    @Test
    public void testTowerRange() {
        Bug bug = new Bug();
        Virus virus = new Virus();
        RootKit rootKit = new RootKit();
        assertNotEquals(bug.getAttackRange(), virus.getAttackRange());
        assertNotEquals(bug.getAttackRange(), rootKit.getAttackRange());
        assertNotEquals(virus.getAttackRange(), rootKit.getAttackRange());
    }
}
