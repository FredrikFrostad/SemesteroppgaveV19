package com.programutvikling.models.utils.helpers;

public class ThreadHelper {
    public static void runThread(Thread thread) throws InterruptedException {
        thread.start();
        thread.join();
    }
}
