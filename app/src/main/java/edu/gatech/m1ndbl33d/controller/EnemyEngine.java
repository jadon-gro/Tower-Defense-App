package edu.gatech.m1ndbl33d.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.gatech.m1ndbl33d.model.Antivirus;
import edu.gatech.m1ndbl33d.model.Encryption;
import edu.gatech.m1ndbl33d.model.Enemy;
import edu.gatech.m1ndbl33d.model.FinalBoss;
import edu.gatech.m1ndbl33d.model.Firewall;

public class EnemyEngine {
    //private static Enemy enemy;

    // Create a list of alive enemies
    private static List<Enemy> currentEnemies = new ArrayList<Enemy>();

    private static int enemyCount = 0;

    private static int numEnemiesBeforeBoss = 10;

    private static int numEnemiesRemoved = 0;

    //Returns zero 1 if a final boss has been spawned.
    public static int initializeEnemy() {
        // Add one enemy to the list
        // Occurs after "StartCombat" is clicked in GameScreenActivity
        Enemy enemy;

        Random randomizer = new Random();
        switch (randomizer.nextInt(3)) {
        case(0):
            enemy = new Encryption();
            break;
        case(1):
            enemy = new Antivirus();
            break;
        default:
            enemy = new Firewall();
        }
        // Sets path position to the start point
        // Probably a better way to do this but don't know how to do it without coupling
        if (enemyCount == numEnemiesBeforeBoss) {
            enemy = new FinalBoss();
            enemy.setPathPosition(0);
            currentEnemies.add(enemy);
            enemyCount++;
            return 1;
        }
        enemy.setPathPosition(0);
        currentEnemies.add(enemy);
        enemyCount++;
        return 0;
    }

    public static List<Enemy> getCurrentEnemies() {
        return currentEnemies;
    }

    public static void addEnemy(Enemy enemy) {
        currentEnemies.add(enemy);
    }

    // Gets enemy position on path at specific index
    public static int getEnemyPosition(int index) {
        return currentEnemies.get(index).getPathPosition();
    }

    // Get all positions at once
    public static List<Integer> getEnemyPositionList() {
        List<Integer> positionList = new ArrayList<>();
        for (Enemy enemy : currentEnemies) {
            positionList.add(enemy.getPathPosition());
        }
        return positionList;
    }

    // Gets enemy sprite of a given index or ID of enemy
    public static Drawable getEnemySprite(int index, Context context) {
        return currentEnemies.get(index).getSprite(context);
    }

    // Sets enemy position on path at specific index
    public static void setEnemyPosition(int index, int position) {
        currentEnemies.get(index).setPathPosition(position);
    }

    public static int getMoney(int index) {
        if (currentEnemies.get(index)
                instanceof Firewall) {
            return 2;
        } else if (currentEnemies.get(index)
                instanceof Antivirus) {
            return 10;
        } else if (currentEnemies.get(index)
                instanceof Encryption) {
            return 7;
        }
        return 0;
    }

    public static int getEnemyMaxHealth(int index) {
        return currentEnemies.get(index).getMaxHealth();
    }

    public static int getEnemyCurrentHealth(int index) {
        return currentEnemies.get(index).getCurrentHealth();
    }

    public static void setEnemyCurrentHealth(int index, int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative");
        }
        currentEnemies.get(index).setCurrentHealth(health);
    }

    // Remove enemy at specific index
    public static void removeEnemy(int index, boolean murdered) {
        currentEnemies.remove(index);
        if (murdered) {
            numEnemiesRemoved++;
        }
    }

    public static void setNumEnemiesRemoved(int num) {
        numEnemiesRemoved = num;
    }

    public static int getNumEnemiesRemoved() {
        return numEnemiesRemoved;
    }

    //Remove all enemies
    public static void clearEnemies() {
        currentEnemies.clear();
    }

    public static int getNumEnemiesBeforeBoss() {
        return numEnemiesBeforeBoss;
    }
}
