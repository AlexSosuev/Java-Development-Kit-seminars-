package homework_5;

import java.util.concurrent.Semaphore;

public class Main_2 {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final Semaphore forkSemaphore = new Semaphore(NUM_PHILOSOPHERS - 1);

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Thread philosopher = new Thread(new Philosopher(i));
            philosopher.start();
        }
    }

    static class Philosopher implements Runnable {
        private final int id;

        public Philosopher(int id) {
            this.id = id;
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println("Philosopher " + id + " " + action);
            Thread.sleep(500);
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    doAction("is thinking");
                    forkSemaphore.acquire(); // acquire the forks

                    doAction("picked up left fork");
                    doAction("picked up right fork");
                    doAction("is eating");

                    forkSemaphore.release(); // release the forks
                    doAction("put down right fork");
                    doAction("put down left fork");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}