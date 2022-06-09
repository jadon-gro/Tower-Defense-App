package edu.gatech.m1ndbl33d;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import edu.gatech.m1ndbl33d.controller.EnemyEngine;

import static org.junit.Assert.assertEquals;

/**
 * M5: Enemy decreases in opacity as it is damaged. Tests the opacity calculation at
 * different levels of health.
 */
@RunWith(AndroidJUnit4.class)
public class EnemyHealthVisibleTest {
    @Test
    public void testHealthOpacity() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //Initialize one enemy
        EnemyEngine.initializeEnemy();

        int startingHealth = EnemyEngine.getEnemyCurrentHealth(0);
        int alpha1 = 127 + (int) (startingHealth * 128.0 / EnemyEngine.getEnemyMaxHealth(0));
        assertEquals(alpha1, 255);

        EnemyEngine.setEnemyCurrentHealth(0, EnemyEngine.getEnemyMaxHealth(0) / 2);
        int someHealth = EnemyEngine.getEnemyCurrentHealth(0);
        int alpha2 = 127 + (int) (someHealth * 128.0 / EnemyEngine.getEnemyMaxHealth(0));
        assertEquals(alpha2, 191);
    }
}
