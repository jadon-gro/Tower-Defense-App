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
 * M6: The power upgrade should modify the power of all types of towers.
 */

@RunWith(AndroidJUnit4.class)
public class PowerUpgradeModifiesTowersTest {
    @Test
    public void testPower() {
        TowerEngine.setTowerPrices("Easy");
        Bug bug = new Bug();
        Virus virus = new Virus();
        RootKit rootKit = new RootKit();

        int baseBugPower = bug.getAttackPower();
        int baseVirusPower = virus.getAttackPower();
        int baseRootKitPower = rootKit.getAttackPower();

        bug.upgradeAttackPower();
        rootKit.upgradeAttackPower();
        virus.upgradeAttackPower();

        int newBugPower = bug.getAttackPower();
        int newVirusPower = virus.getAttackPower();
        int newRootKitPower = rootKit.getAttackPower();

        assertTrue(newBugPower > baseBugPower);
        assertTrue(newVirusPower > baseVirusPower);
        assertTrue(newRootKitPower > baseRootKitPower);
    }
}
