package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.RootKit;
import edu.gatech.m1ndbl33d.model.Virus;

/**
 * M6: The range upgrade should modify the range of all types of towers.
 */

@RunWith(AndroidJUnit4.class)
public class RangeUpgradeModifiesTowersTest {
    @Test
    public void testRange() {
        TowerEngine.setTowerPrices("Easy");
        Bug bug = new Bug();
        Virus virus = new Virus();
        RootKit rootKit = new RootKit();

        int baseBugRange = bug.getAttackRange();
        int baseVirusRange = virus.getAttackRange();
        int baseRootKitRange = rootKit.getAttackRange();

        bug.upgradeAttackRange();
        rootKit.upgradeAttackRange();
        virus.upgradeAttackRange();

        int newBugRange = bug.getAttackRange();
        int newVirusRange = virus.getAttackRange();
        int newRootKitRange = rootKit.getAttackRange();

        assertTrue(newBugRange > baseBugRange);
        assertTrue(newVirusRange > baseVirusRange);
        assertTrue(newRootKitRange > baseRootKitRange);
    }
}
