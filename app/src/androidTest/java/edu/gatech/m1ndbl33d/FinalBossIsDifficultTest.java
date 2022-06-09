package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.model.Antivirus;
import edu.gatech.m1ndbl33d.model.Encryption;
import edu.gatech.m1ndbl33d.model.FinalBoss;
import edu.gatech.m1ndbl33d.model.Firewall;

/**
 * M6: The final boss must be more difficult to defeat than other enemies.
 */

@RunWith(AndroidJUnit4.class)
public class FinalBossIsDifficultTest {
    @Test
    public void testFinalBoss() {
        Antivirus antivirus = new Antivirus();
        Firewall firewall = new Firewall();
        Encryption encryption = new Encryption();
        FinalBoss finalBoss = new FinalBoss();

        int antivirusDamage = antivirus.getDamage();
        int firewallDamage = firewall.getDamage();
        int encryptionDamage = encryption.getDamage();
        int finalBossDamage = finalBoss.getDamage();

        assertTrue(finalBossDamage > firewallDamage);
        assertTrue(finalBossDamage > encryptionDamage);
        assertTrue(finalBossDamage > antivirusDamage);

    }
}
