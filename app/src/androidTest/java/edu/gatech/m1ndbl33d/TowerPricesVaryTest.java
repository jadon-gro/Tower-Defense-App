package edu.gatech.m1ndbl33d;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.gatech.m1ndbl33d.controller.TowerEngine;
import edu.gatech.m1ndbl33d.model.Bug;
import edu.gatech.m1ndbl33d.model.RootKit;
import edu.gatech.m1ndbl33d.model.Virus;

/**
 * M3: Ensure that tower prices vary with difficulty level
 */
public class TowerPricesVaryTest {
    @Test
    public void checkPrices() {
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        TowerEngine.setTowerPrices("Easy");
        Bug easyBug = new Bug();
        int easyBugPrice = easyBug.getPrice();
        Virus easyVirus = new Virus();
        int easyVirusPrice = easyVirus.getPrice();
        RootKit easyRootKit = new RootKit();
        int easyRootKitPrice = easyRootKit.getPrice();

        TowerEngine.setTowerPrices("Hard");
        Bug hardBug = new Bug();
        int hardBugPrice = hardBug.getPrice();
        Virus hardVirus = new Virus();
        int hardVirusPrice = hardVirus.getPrice();
        RootKit hardRootKit = new RootKit();
        int hardRootKitPrice = hardRootKit.getPrice();

        assertTrue(easyBugPrice != hardBugPrice);
        assertTrue(easyVirusPrice != hardVirusPrice);
        assertTrue(easyRootKitPrice != hardRootKitPrice);

    }
}
