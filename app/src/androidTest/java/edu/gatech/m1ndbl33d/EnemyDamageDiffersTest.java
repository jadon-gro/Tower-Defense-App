package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.m1ndbl33d.model.Antivirus;
import edu.gatech.m1ndbl33d.model.Encryption;
import edu.gatech.m1ndbl33d.model.Firewall;

/**
 * M5: The enemies must deliver different amounts of damage to the player.
 */

@RunWith(AndroidJUnit4.class)
public class EnemyDamageDiffersTest {
    @Test
    public void testEnemyDamageDiffers() {
        Antivirus antivirus = new Antivirus();
        Firewall firewall = new Firewall();
        Encryption encryption = new Encryption();

        int antivirusDamage = antivirus.getDamage();
        int firewallDamage = firewall.getDamage();
        int encryptionDamage = encryption.getDamage();

        assertTrue(antivirusDamage != firewallDamage);
        assertTrue(antivirusDamage != encryptionDamage);
        assertTrue(firewallDamage != encryptionDamage);
    }
}
