package multithreading.tast_3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //    В рамках выполнения задачи необходимо:
        //    3 бегуна должны прийти к старту гонки
        //    Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
        //    Программа должна отсчитать “На старт”, “Внимание”, “Марш”
        //    Программа должна завершиться когда все участники закончат гонку.
        //    Подумайте об использовании примитива синхронизации

        CountDownLatch countDownLatch = new CountDownLatch(4);
        Runner runnerOne = new Runner("Roma", countDownLatch);
        Runner runnerTwo = new Runner("Ivan", countDownLatch);
        Runner runnerThree = new Runner("Mikhail", countDownLatch);
        runnerOne.start();
        runnerTwo.start();
        runnerThree.start();
        while (countDownLatch.getCount() != 1) {
            Thread.sleep(100);
        }
        System.out.println("На старт");
        Thread.sleep(1000);
        System.out.println("Внимание");
        Thread.sleep(1000);
        System.out.println("Марш");
        countDownLatch.countDown();
    }
}