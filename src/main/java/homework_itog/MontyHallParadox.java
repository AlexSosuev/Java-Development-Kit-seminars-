package homework_itog;

//В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла(Парадокс Монти Холла —
// Википедия) и наглядно убедиться в верности парадокса(запустить игру в цикле на 1000и вывести итоговый счет).
//Необходимо:
//  Создать свой Java Maven или Gradle проект;
//  Подключите зависимость lombok и возможно какую то математическую библиотеку(напр.commons-math3)
//  Самостоятельно реализовать прикладную задачу;
//  Сохранить результат игр в одну из коллекций или в какой то библиотечный класс
//  Вывести на экран статистику по победам и поражениям
//В качестве ответа прислать ссылку на репозиторий,в котором присутствует все важные файлы проекта(напр pom.xml)

import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.HashMap;
import java.util.Map;

public class MontyHallParadox {
    private static final RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    public static void main(String[] args) {
        int totalGames = 1000;
        int switchWins = 0;
        int stayWins = 0;
        Map<Integer, Boolean> gameResults = new HashMap<>();

        for (int i = 0; i < totalGames; i++) {
            int carDoor = randomDataGenerator.nextInt(1, 3);
            int selectedDoor = randomDataGenerator.nextInt(1, 3);

            int openedDoor = getOpenedDoor(carDoor, selectedDoor);
            int finalDoor = getFinalDoor(selectedDoor, openedDoor);

            boolean win = finalDoor == carDoor;
            gameResults.put(i, win);

            if (win) {
                switchWins++;
            } else {
                stayWins++;
            }
        }

        System.out.println("Побед при оставлении выбора неизменным: " + stayWins);
        System.out.println("Победы после смены выбора: " + switchWins);

//        for (Map.Entry<Integer, Boolean> entry : gameResults.entrySet()) {
//            System.out.println("Игра №" + entry.getKey() + ": " + (entry.getValue() ? "Победа" : "Поражение"));
//        }
    }

    private static int getFinalDoor(int selectedDoor, int openedDoor) {
        return 1 + 2 + 3 - selectedDoor - openedDoor;
    }

    private static int getOpenedDoor(int carDoor, int selectedDoor) {
        int doorToOpen = randomDataGenerator.nextInt(1, 3);
        while (doorToOpen == carDoor || doorToOpen == selectedDoor) {
            doorToOpen = randomDataGenerator.nextInt(1, 3);
        }
        return doorToOpen;
    }
}