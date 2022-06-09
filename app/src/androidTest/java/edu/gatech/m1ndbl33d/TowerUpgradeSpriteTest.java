package edu.gatech.m1ndbl33d;

import android.content.Context;
import android.graphics.drawable.Drawable;

import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;
import edu.gatech.m1ndbl33d.model.Bug;

import static org.junit.Assert.assertNotEquals;

/**
 * M6: Upgrading a tower changes its visual appearance
 */
public class TowerUpgradeSpriteTest {
    @Test
    public void checkUpgradedSprite() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Bug bug = new Bug();
        Drawable firstSprite = bug.getSprite(appContext);

        bug.upgradeAttackPower();
        Drawable secondSprite = bug.getSprite(appContext);
        assertNotEquals(firstSprite, secondSprite);

        bug.upgradeAttackRange();
        Drawable thirdSprite = bug.getSprite(appContext);
        assertNotEquals(secondSprite, thirdSprite);
    }
}
