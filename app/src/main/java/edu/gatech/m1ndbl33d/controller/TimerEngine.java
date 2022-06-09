package edu.gatech.m1ndbl33d.controller;

import java.util.Timer;
import java.util.TimerTask;


public class TimerEngine {
    private static Timer timer;

    public static void createTimer() {
        timer = new Timer(false);
    }

    // Schedules a task at a given schedule
    // Can add a tick rate control here if we want to standardize it?
    public static void scheduleTask(TimerTask task, long delay, long period) {
        timer.schedule(task, delay, period);
    }

    public static void cancelTasks() {
        timer.cancel();
    }

}
