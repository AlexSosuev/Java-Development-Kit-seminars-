package multithreading.tast_3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {
    private final String name;
    private final CountDownLatch countDownLatch;
    private final Random rnd = new Random();

    public Runner(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            toStart();
            countDownLatch.await();
            toFinish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void toStart() throws InterruptedException {
        System.out.println(name + " move to start");
        Thread.sleep(rnd.nextInt(1000,2000));
        System.out.println(name + " ready");
        countDownLatch.countDown();
    }

    public void toFinish() throws InterruptedException {
        System.out.println(name + " move to finish");
        Thread.sleep(rnd.nextInt(1000,2000));
        System.out.println(name + " finished");
    }
}