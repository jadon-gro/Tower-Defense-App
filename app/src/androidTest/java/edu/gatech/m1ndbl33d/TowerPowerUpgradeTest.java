package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import edu.gatech.m1ndbl33d.model.RootKit;

/**
 * M6: Upgrades to the power of a tower can only be done once
 */
public class TowerPowerUpgradeTest {
    @Test
    public void checkTowerPower() {
        RootKit rootKit = new RootKit();
        int baseRootKitPower = rootKit.getAttackPower();

        //1st upgrade rootkit power upgrades the power of the rootkit
        rootKit.upgradeAttackPower();
        assertNotEquals(baseRootKitPower, rootKit.getAttackPower());
        int oneUpgradeRootKitPower = rootKit.getAttackPower();

        //2nd upgrade to rootkit power does not have any effect
        rootKit.upgradeAttackPower();
        assertEquals(oneUpgradeRootKitPower, rootKit.getAttackPower());
    }
}
