package collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
В рамках выполнения задачи необходимо:
1. Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
2. Найдите человека с самым маленьким номером телефона
3. Найдите номер телефона человека чье имя самое большое в алфавитном порядке
* */
public class Task3 {
    public static void main(String[] args) {
        Map<String, String> phones = new HashMap<>();
        phones.putIfAbsent("123", "Ivan");
        phones.putIfAbsent("234", "Nikolay");
        phones.putIfAbsent("456", "Olga");
        phones.putIfAbsent("122", "Sasha");
        phones.putIfAbsent("234", "Natasha");

        System.out.println(phones.keySet().stream().map(Integer::parseInt).min(Comparator.naturalOrder()));
        String tmp = phones.values().stream().max(String::compareTo).orElseGet(null);
        for (var item : phones.entrySet()) {
            if (item.getValue().equals(tmp)) {
                System.out.println(item.getKey());
            }
        }
        System.out.println(phones.entrySet()
                .stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get().getKey());

        System.out.println(phones.entrySet()
                .stream().max(Map.Entry.comparingByValue()).get().getKey());
    }
}