package edu.gatech.m1ndbl33d.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import edu.gatech.m1ndbl33d.R;

public class RootKit extends Tower {
    private static int basePrice;
    private static int attackPower = 30;
    private static int attackRange = 0;

    public RootKit() {
        super(basePrice, attackPower, attackRange);
    }

    public Drawable getSprite(Context context) {
        // Choose sprite according to current upgrades
        if (powerUpgraded && rangeUpgraded) {
            return ContextCompat.getDrawable(context, R.drawable.rootkit_pr);
        } else if (powerUpgraded) {
            return ContextCompat.getDrawable(context, R.drawable.rootkit_p);
        } else if (rangeUpgraded) {
            return ContextCompat.getDrawable(context, R.drawable.rootkit_r);
        } else {
            return ContextCompat.getDrawable(context, R.drawable.rootkit);
        }
    }

    public static void setBasePrice(int price) {
        basePrice = price;
    }
}