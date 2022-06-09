package edu.gatech.m1ndbl33d;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.model.Enemy;

/**
 * M5: An enemy must die when its health reaches 0. Therefore, the enemy's health cannot be negative
 */
@RunWith(AndroidJUnit4.class)
public class EnemyCannotHaveSubZeroHealthTest {
    @Test(expected = IllegalArgumentException.class)
    public void enemyHealthCheck() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        EnemyEngine.initializeEnemy();
        List<Enemy> currentEnemies = EnemyEngine.getCurrentEnemies();
        EnemyEngine.setEnemyCurrentHealth(0, -1);
    }
}
