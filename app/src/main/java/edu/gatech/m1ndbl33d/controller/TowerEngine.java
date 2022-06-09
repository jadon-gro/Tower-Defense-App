package edu.gatech.m1ndbl33d.controller;

import java.util.Stack;

import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.RootKit;
import edu.gatech.m1ndbl33d.model.Tower;
import edu.gatech.m1ndbl33d.model.Virus;


public class TowerEngine {
    private static Stack<Tower> towerStack = new Stack<Tower>();
    private static int numBug;
    private static int numRootKit;
    private static int numVirus;
    private static int upgradeAttackPowerPrice;
    private static int upgradeAttackRangePrice;
    private static boolean upgradeAttackPower;
    private static boolean upgradeAttackRange;

    public static Stack<Tower> getTowerStack() {
        // These are towers that have been purchased, but not placed
        return towerStack;
    }

    public static void setTowerPrices(String difficulty) {
        // Prices need to vary with difficulty

        if (difficulty.equals("Easy")) {
            Bug.setBasePrice(10);
            RootKit.setBasePrice(15);
            Virus.setBasePrice(20);
        }
        if (difficulty.equals("Medium")) {
            Bug.setBasePrice(20);
            RootKit.setBasePrice(30);
            Virus.setBasePrice(40);
        }
        if (difficulty.equals("Hard")) {
            Bug.setBasePrice(30);
            RootKit.setBasePrice(45);
            Virus.setBasePrice(60);
        }
        upgradeAttackPowerPrice = 10;
        upgradeAttackRangePrice = 5;
        upgradeAttackPower = false;
        upgradeAttackRange = false;
    }


    public static boolean addNewTowerHelper(Tower tower, int money) {
        if (tower.getPrice() > money) {
            return false;
        } else {
            towerStack.push(tower);
            return true;
        }
    }

    public static boolean addNewTower(String name, int money) {
        // Returns whether or not adding a tower was successful
        return addNewTowerHelper(determineTower(name), money);
    }

    public static Tower determineTower(String name) {
        // This string look up exists so that model instantiation doesn't occur in screens
        if (name.equals("Bug") || name.equals("bug")) {
            Bug result = new Bug();
            return result;
        } else if (name.equals("Rootkit") || name.equals("rootkit") || name.equals("RootKit")) {
            RootKit result = new RootKit();
            return result;
        } else if (name.equals("Virus") || name.equals("virus")) {
            Virus result = new Virus();
            return result;
        } else {
            Tower result = null;
            //throw new IllegalArgumentException("The name must be an actual Tower");
            return result;
        }
    }

    public static int getTowerPrice(String name) {
        Tower tower = determineTower(name);
        return tower.getPrice();
    }

    public static void purchaseTower(String name, int money) {
        // This method is currently not used as price details are left with PlayerEngine
        Tower tower = determineTower(name);
        int price = tower.getPrice();
        if (price <= money) {
            towerStack.push(tower);
        }
    }

    public static void clearTowers() {
        towerStack.clear();
        numBug = 0;
        numRootKit = 0;
        numVirus = 0;
    }

    public static int getNumBug() {
        return numBug;
    }
    //increase number of bugs by 1
    public static void increaseNumBug() {
        numBug += 1;
    }
    public static int getNumRootKit() {
        return numRootKit;
    }
    public static void increaseNumRootKit() {
        numRootKit += 1;
    }
    public static int getNumVirus() {
        return numVirus;
    }
    public static void increaseNumVirus() {
        numVirus += 1;
    }
    public static int getUpdateAttackPowerPrice() {
        return upgradeAttackPowerPrice;
    }
    public static int getUpdateAttackRangePrice() {
        return upgradeAttackRangePrice;
    }
    public static boolean getUpdateAttackPower() {
        return upgradeAttackPower;
    }
    public static boolean getUpdateAttackRange() {
        return upgradeAttackRange;
    }
    public static void setUpdateAttackPower(boolean isPowerUpgradeBought) {
        upgradeAttackPower = isPowerUpgradeBought;
    }
    public static void setUpdateAttackRange(boolean isRangeUpgradeBought) {
        upgradeAttackRange = isRangeUpgradeBought;
    }
}
