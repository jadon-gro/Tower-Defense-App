package edu.gatech.m1ndbl33d.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import edu.gatech.m1ndbl33d.R;

public class FinalBoss extends Enemy {
    private static int maxHealth = 1200;
    private static int damage = Integer.MAX_VALUE;
    public FinalBoss() {
        super(maxHealth, damage);
    }

    public int getDamage() {
        return damage;
    }

    public Drawable getSprite(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.finalboss);
    }
}

