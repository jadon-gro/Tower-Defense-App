package edu.gatech.m1ndbl33d.model;

public abstract class Tower extends MapTile {
    private int price;
    private int attackPower;
    private int attackRange;
    protected boolean powerUpgraded;
    protected boolean rangeUpgraded;

    public Tower(int price, int attackPower, int attackRange) {
        this.price = price;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        powerUpgraded = false;
        rangeUpgraded = false;
    }

    public boolean isEmpty() {
        return false;
    }

    public int getPrice() {
        return price;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getAttackRange() {
        return attackRange;
    }

    //upgrades the attack power of a tower
    //and returns true is successful
    public boolean upgradeAttackPower() {
        if (!powerUpgraded) {
            attackPower = (int) (attackPower * 1.5);
            powerUpgraded = true;
            return true;
        }
        return false;
    }

    //upgrades the attack range of a tower
    //and return true if successful
    public boolean upgradeAttackRange() {
        if (!rangeUpgraded) {
            attackRange = attackRange + 1;
            rangeUpgraded = true;
            return true;
        }
        return false;
    }

    /* This detail should not exist in the model
        public int getSellPrice() {
            return (int) (getTotalPrice() * 0.6);
    }*/
}
