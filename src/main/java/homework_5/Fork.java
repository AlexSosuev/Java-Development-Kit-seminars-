package homework_5;

import java.util.concurrent.CountDownLatch;

public class Fork extends Thread {
    private final int PHILOSOPHER_COUNT = 5;
    private final boolean[] forksInUse = new boolean[PHILOSOPHER_COUNT];
    private final Philosopher[] philosophers = new Philosopher[PHILOSOPHER_COUNT];
    private final CountDownLatch countDownLatch = new CountDownLatch(PHILOSOPHER_COUNT);

    public Fork() {
        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            philosophers[i] = new Philosopher("Филосов №" + i, this, i, (i + 1) % PHILOSOPHER_COUNT, countDownLatch);
        }
    }

    @Override
    public void run() {
        try {
            for (Philosopher philosopher : philosophers) {
                philosopher.start();
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized boolean tryGetForks(int leftFork, int rightFork) {
        if (!forksInUse[leftFork] && !forksInUse[rightFork]) {
            forksInUse[leftFork] = true;
            forksInUse[rightFork] = true;
            return true;
        }
        return false;
    }

    public void putForks(int leftFork, int rightFork) {
        forksInUse[leftFork] = false;
        forksInUse[rightFork] = false;
    }
}