package edu.gatech.m1ndbl33d.model;

public class Player {
    private int money;
    private int health;
    private String name;
    private String difficulty;
    private int moneyEarned;

    public Player(String name, String difficulty, int money, int health) {
        this.money = money;
        this.health = health;
        this.name = name;
        this.difficulty = difficulty;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getMoneyEarned() {
        return moneyEarned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void incMoneyEarned(int moreMoney) {
        this.moneyEarned += moreMoney;
    }
}
