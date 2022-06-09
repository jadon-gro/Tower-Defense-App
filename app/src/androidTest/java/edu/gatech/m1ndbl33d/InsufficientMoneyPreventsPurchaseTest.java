package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.RootKit;
import edu.gatech.m1ndbl33d.model.Tower;

/**
 * M3: There is an attempt to buy two RootKits, but there is not enough money for the second one.
 * The tower stack should be empty at the end since only one RootKit is purchased.
 * Hence, insufficient funds blocks the purchase (in this case: of the second RootKit).
 */
@RunWith(AndroidJUnit4.class)
public class InsufficientMoneyPreventsPurchaseTest {
    @Test
    public void purchase() {
        RootKit.setBasePrice(20);
        RootKit rootkit = new RootKit();
        int money = 20;
        boolean firstPurchase = TowerEngine.addNewTower("RootKit", money);
        if (firstPurchase) {
            money = money - rootkit.getPrice();
        }
        boolean secondPurchase = TowerEngine.addNewTower("RootKit", money);
        if (secondPurchase) {
            money = money - rootkit.getPrice();
        }
        Stack<Tower> towerStack = TowerEngine.getTowerStack();
        Tower purchasedTower = towerStack.pop();
        assertTrue(towerStack.isEmpty());
    }
}
