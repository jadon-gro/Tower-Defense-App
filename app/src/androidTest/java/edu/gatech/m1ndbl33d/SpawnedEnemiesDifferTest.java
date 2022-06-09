package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.gatech.m1ndbl33d.controller.EnemyEngine;
import edu.gatech.m1ndbl33d.model.Antivirus;
import edu.gatech.m1ndbl33d.model.Encryption;
import edu.gatech.m1ndbl33d.model.Enemy;
import edu.gatech.m1ndbl33d.model.Firewall;

/**
 * M4: This test confirms that different types of enemies are spawned when combat starts
 */

@RunWith(AndroidJUnit4.class)
public class SpawnedEnemiesDifferTest {
    @Test
    public void testSpawnedEnemies() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        int enemyCount = 20;
        for (int i = 0; i < enemyCount + 1; i++) {
            EnemyEngine.initializeEnemy();
        }

        List<Enemy> currentEnemies = EnemyEngine.getCurrentEnemies();
        boolean antivirusFound = currentEnemies.stream().anyMatch(c -> c instanceof Antivirus);
        boolean encryptionFound = currentEnemies.stream().anyMatch(c -> c instanceof Encryption);
        boolean firewallFound = currentEnemies.stream().anyMatch(c -> c instanceof Firewall);

        assertTrue(antivirusFound);
        assertTrue(encryptionFound);
        assertTrue(firewallFound);
    }
}
