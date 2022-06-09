package edu.gatech.m1ndbl33d;

import android.content.Context;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import edu.gatech.m1ndbl33d.controller.PlayerEngine;
import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.Bug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * M6: The same type of upgrade can only be bought once without applying to tower
 */
public class NoDuplicateUpgradeTest {
    @Test
    public void checkUpgradePurchase() {
        Bug bug = new Bug();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerEngine.initializePlayer("player", "Easy");

        int money = PlayerEngine.getPlayerMoney();
        if (!TowerEngine.getUpdateAttackPower()) {
            PlayerEngine.decreaseMoneyByAmount(10);
            TowerEngine.setUpdateAttackPower(true);
        }
        int newMoney = PlayerEngine.getPlayerMoney();
        assertTrue(money > newMoney);

        if (!TowerEngine.getUpdateAttackPower()) {
            PlayerEngine.decreaseMoneyByAmount(10);
            TowerEngine.setUpdateAttackPower(true);
        }
        int newerMoney = PlayerEngine.getPlayerMoney();
        assertEquals(newMoney, newerMoney);
    }
}
