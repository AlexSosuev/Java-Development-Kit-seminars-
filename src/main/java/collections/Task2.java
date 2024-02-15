package collections;

import java.util.*;
import java.util.stream.Collectors;

/*
В рамках выполнения задачи необходимо:
Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
Получите уникальный список Set на основании List
Определите наименьший элемент (алфавитный порядок)
Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
Удалите все элементы содержащие букву ‘A’
*/

public class Task2 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Roma","Alexey","Konstantin","Olga","Olga", "Sergey"));
        Set<String> uniqueNames = new HashSet<>(names);
        System.out.println(uniqueNames);
        System.out.println(firstItemByAlphabet(uniqueNames));
        System.out.println(firstItemByLength(uniqueNames));
        System.out.println(withoutA(uniqueNames));
        removeElementsWith(uniqueNames, "A");
        System.out.println(uniqueNames);
    }

    private static String firstItemByAlphabet(Set<String> names){
        return names.stream().min(String::compareTo).orElseGet(null);
    }

    private static String firstItemByLength(Set<String> names){
        return names.stream().max(Comparator.comparingInt(String::length)).orElseGet(null);
    }

    private static Set<String> withoutA(Set<String> names){
        Set<String> tmpNames = new HashSet<>();
        for (String item: names) {
            if (!item.toUpperCase().contains("A")){
                tmpNames.add(item);
            }
        }
        return tmpNames;
    }

    public static void removeElementsWith(Set<String> set, String targetForDel) {
        //метод removeIf - изменяет коллекцию!!!
        set.removeIf(x -> x.contains(targetForDel));
    }

    public static Set<String> deleteSomeFilter(Set<String> names) {
        return names.stream().filter(x -> !x.contains("А") && !x.contains("а")).collect(Collectors.toSet());
    }
}
