package edu.gatech.m1ndbl33d.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class EmptyTile extends MapTile {
    public boolean isEmpty() {
        return true;
    }

    public Drawable getSprite(Context context) {
        return null;
    }
}
