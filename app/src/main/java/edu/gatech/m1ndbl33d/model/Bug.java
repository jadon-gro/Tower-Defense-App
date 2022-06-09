package edu.gatech.m1ndbl33d.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import edu.gatech.m1ndbl33d.R;

public class Bug extends Tower {
    private static int basePrice;
    private static int attackPower = 20;
    private static int attackRange = 3;

    public Bug() {
        super(basePrice, attackPower, attackRange);
    }

    public Drawable getSprite(Context context) {
        // Choose sprite according to current upgrades
        if (powerUpgraded && rangeUpgraded) {
            return ContextCompat.getDrawable(context, R.drawable.bug_pr);
        } else if (powerUpgraded) {
            return ContextCompat.getDrawable(context, R.drawable.bug_p);
        } else if (rangeUpgraded) {
            return ContextCompat.getDrawable(context, R.drawable.bug_r);
        } else {
            return ContextCompat.getDrawable(context, R.drawable.bug);
        }
    }

    public static void setBasePrice(int price) {
        basePrice = price;
    }
}
