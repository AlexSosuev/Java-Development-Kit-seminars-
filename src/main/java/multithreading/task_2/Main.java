package multithreading.task_2;

public class Main {
    static volatile boolean switcher = true;
    static volatile boolean work = true;

    public static void main(String[] args) {
        //    В рамках выполнения задачи необходимо:
        //    Создайте два потока A и B.
        //    Поток A меняет значение Boolean переменной switcher с задержкой 1000 миллисекунд
        //    (true в состояние false и наоборот).
        //    Поток B ожидает состояния true переменной switcher и выводит на консоль обратный отсчет от 100
        //    с задержкой 100 миллисекунд и приостанавливает свое действие, как только поток A переключит switcher
        //    в состояние false.
        //    Условием завершения работы потоков является достижение отсчета нулевой отметки.

        Thread changer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (work) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    switcher = !switcher;
                }
            }
        });
        Thread counter = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 100;
                while (count >= 0) {
                    if (switcher) {
                        System.out.println(count--);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                work = !work;
            }
        });
        changer.start();
        counter.start();
    }
}