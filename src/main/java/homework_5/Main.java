package homework_5;

// ● Есть пять философов (потоки), которые могут либо обедать (выполнение кода) либо размышлять (ожидание).
// ● Каждый философ должен пообедать три раза. Каждый прием пищи длиться 500 миллисекунд
// ● После каждого приема пищи философ должен размышлять
// ● Не должно возникнуть общей блокировки
// ● Философы не должны есть больше одного раза подряд
// ● Можно брать только две вилки одновременно

public class Main {
    private static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        Object[] forks = new Object[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % NUM_PHILOSOPHERS];

            if (i == NUM_PHILOSOPHERS - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            new Thread(philosophers[i], "Philosopher " + (i + 1)).start();
        }
    }
}