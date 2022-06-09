package edu.gatech.m1ndbl33d.controller;

import edu.gatech.m1ndbl33d.model.Player;
import edu.gatech.m1ndbl33d.model.Tower;

public class PlayerEngine {
    public static final int EASY_MONEY = 300;
    public static final int MEDIUM_MONEY = 200;
    public static final int HARD_MONEY = 100;
    public static final int EASY_HEALTH = 30;
    public static final int MEDIUM_HEALTH = 20;
    public static final int HARD_HEALTH = 10;
    private static int livesLost = 0;
    private static Player player;

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayerEngine.player = player;
    }

    public static void initializePlayer(String name, String difficulty) {
        // PlayerEngine manages interactions between the screen and the model such as
        // the screen's choice of difficulty affecting the player's coin and health
        if (difficulty.equals("Easy")) {
            player = new Player(name, difficulty, EASY_MONEY, EASY_HEALTH);
        }
        if (difficulty.equals("Medium")) {
            player = new Player(name, difficulty, MEDIUM_MONEY, MEDIUM_HEALTH);
        }
        if (difficulty.equals("Hard")) {
            player = new Player(name, difficulty, HARD_MONEY, HARD_HEALTH);
        }
    }

    public static int getLivesLost() {
        return livesLost;
    }

    public static int getPlayerMoney() {
        return player.getMoney();
    }

    public static int getPlayerHealth() {
        return player.getHealth();
    }

    public static int getMoneyEarned() {
        return player.getMoneyEarned();
    }

    public static String getPlayerName() {
        return player.getName();
    }

    public static String getPlayerDifficulty() {
        return player.getDifficulty();
    }

    public static void decreaseMoneyByTower(Tower tower) {
        // This DOES NOT handle the placing of the tower upon purchasing
        // THis is currently not used as the tower details are abstracted away
        if (player.getMoney() < tower.getPrice()) {
            throw new IllegalArgumentException("You do not have enough money");
        }
        player.setMoney(player.getMoney() - tower.getPrice());
    }

    public static void decreaseMoneyByAmount(int decrease) {
        // This DOES NOT handle the placing of the tower upon purchasing
        if (player.getMoney() >= decrease) {
            player.setMoney(player.getMoney() - decrease);
            //throw new IllegalArgumentException("You do not have enough money");
        }
    }

    public static void decreaseHealthByAmount(int decrease) {
        player.setHealth(player.getHealth() - decrease);
        livesLost += decrease;
    }

    public static void increaseMoneyByAmount(int increase) {
        player.setMoney(player.getMoney() + increase);
        player.incMoneyEarned(increase);
    }
}
