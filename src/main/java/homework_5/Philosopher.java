package homework_5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    private final String name;
    private final int leftFork;
    private final int rightFork;
    private int countEat;
    private final Random random;
    private final CountDownLatch countDownLatch;
    private final Fork fork;

    public Philosopher(String name, Fork fork, int leftFork, int rightFork, CountDownLatch countDownLatch) {
        this.fork = fork;
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.countDownLatch = countDownLatch;
        countEat = 0;
        random = new Random();
    }

    @Override
    public void run() {
        while (countEat < 3) {
            try {
                thinking();
                eating();
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }
        }
        System.out.println(name + " закончил трапезу");
        countDownLatch.countDown();
    }

    private void eating() throws InterruptedException {
        if (fork.tryGetForks(leftFork, rightFork)) {
            System.out.println(name + " ест, используя вилки: " + leftFork + " и " + rightFork);
            sleep(random.nextLong(2500, 4500));
            fork.putForks(leftFork, rightFork);
            System.out.println(name + " решил разложит пасьянс, не забыв при этом положить вилки " + leftFork + " и " + rightFork);
            countEat++;
        }
    }

    private void thinking() throws InterruptedException {
        sleep(random.nextLong(100, 2000));
    }
}