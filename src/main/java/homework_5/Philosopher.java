package homework_5;

public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(500);
    }

    @Override
    public void run() {
        try {
            int NUM_EAT_TIMES = 3;
            for (int i = 0; i < NUM_EAT_TIMES; i++) {
                doAction("размышляет");
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        doAction("ест");
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}