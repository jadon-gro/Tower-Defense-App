package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertNotEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.m1ndbl33d.model.Antivirus;
import edu.gatech.m1ndbl33d.model.Encryption;
import edu.gatech.m1ndbl33d.model.Enemy;
import edu.gatech.m1ndbl33d.model.Firewall;

/**
 * M5: Enemy health should vary
 */
@RunWith(AndroidJUnit4.class)
public class EnemyHealthVaryTest {
    @Test
    public void checkEnemyHealth() {
        //Initialize three enemies
        List<Enemy> currentEnemies = new ArrayList<Enemy>();
        currentEnemies.add(new Encryption());
        currentEnemies.add(new Antivirus());
        currentEnemies.add(new Firewall());

        //get health of the three different enemies
        int encryptionHealth = currentEnemies.get(0).getCurrentHealth();
        int antiVirusHealth = currentEnemies.get(1).getCurrentHealth();
        int fireWallHealth = currentEnemies.get(2).getCurrentHealth();

        //check that the healths are not equal
        assertNotEquals(encryptionHealth, antiVirusHealth);
        assertNotEquals(encryptionHealth, fireWallHealth);
        assertNotEquals(antiVirusHealth, fireWallHealth);
    }
}
