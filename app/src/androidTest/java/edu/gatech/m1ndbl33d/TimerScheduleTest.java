package edu.gatech.m1ndbl33d;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.TimerTask;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.gatech.m1ndbl33d.controller.PlayerEngine;
import edu.gatech.m1ndbl33d.controller.TimerEngine;

import static org.junit.Assert.assertEquals;

/**
 * M4: Ensures timed activities activate as needed.
 * In this case, uses a simple money decrease test for simplicity.
 */
@RunWith(AndroidJUnit4.class)
public class TimerScheduleTest {
    @Test(timeout = 120)
    public void scheduleTimerTask() throws InterruptedException {
        PlayerEngine.initializePlayer("Tan", "Medium");
        int previousMoney = PlayerEngine.getPlayerMoney();

        TimerEngine.createTimer();
        // Create a timer task for periodically updating money
        TimerTask updateMoneyTask = new TimerTask() {
            @Override
            public void run() {
                PlayerEngine.decreaseMoneyByAmount(10);
            }
        };
        TimerEngine.scheduleTask(updateMoneyTask, 0, 1000);

        Thread.sleep(100);

        int currentMoney = PlayerEngine.getPlayerMoney();

        assertEquals(previousMoney - 10, currentMoney);
    }
}
