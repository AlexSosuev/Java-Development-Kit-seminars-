package multithreading.task_1;

public class Main {
    public static void main(String[] args) {
        System.out.println("seminar five");
        //В рамках выполнения задачи необходимо:
        //Создать два класс ObjectA, ObjectB
        //Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, ObjectB
        ObjectA objectA = new ObjectA();
        ObjectB objectB = new ObjectB();

        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + " object a is locked");
                    synchronized (objectB) {
                        System.out.println(Thread.currentThread().getName() + " object b is locked");
                    }
                }
            }
        });
        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + " object b is locked");
                    synchronized (objectA) {
                        System.out.println(Thread.currentThread().getName() + " object a is locked");
                    }
                }
            }
        });
        firstThread.start();
        secondThread.start();
        System.out.println("hello from main");
    }
}