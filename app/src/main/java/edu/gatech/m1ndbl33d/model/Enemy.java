package edu.gatech.m1ndbl33d.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public abstract class Enemy {
    // Should become an abstract class inherited by 3 different enemies
    private int maxHealth;
    private int currentHealth;
    private int pathPosition;
    private int damage;

    public Enemy(int maxHealth, int damage) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public abstract int getDamage();

    public abstract Drawable getSprite(Context context);

    public int getPathPosition() {
        return pathPosition;
    }

    public void setPathPosition(int pathPosition) {
        this.pathPosition = pathPosition;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }
}
