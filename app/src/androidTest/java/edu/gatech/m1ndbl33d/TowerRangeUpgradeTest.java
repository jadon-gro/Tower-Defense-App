package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import edu.gatech.m1ndbl33d.model.RootKit;

/**
 * M6: Upgrades to the range of a tower can be done once
 */
public class TowerRangeUpgradeTest {
    @Test
    public void checkTowerRange() {
        RootKit rootKit = new RootKit();
        int baseRootKitRange = rootKit.getAttackRange();

        //1st upgrade rootkit range upgrades the range of the rootkit
        rootKit.upgradeAttackRange();
        assertNotEquals(baseRootKitRange, rootKit.getAttackRange());
        int oneUpgradeRootKitRange = rootKit.getAttackRange();

        //2nd upgrade to rootkit range does not have any effect
        rootKit.upgradeAttackRange();
        assertEquals(oneUpgradeRootKitRange, rootKit.getAttackRange());
    }
}
