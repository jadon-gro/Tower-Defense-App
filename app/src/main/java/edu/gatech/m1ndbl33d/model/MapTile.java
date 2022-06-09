package edu.gatech.m1ndbl33d.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

public abstract class MapTile {
    public abstract boolean isEmpty();

    public abstract Drawable getSprite(Context context);
}
