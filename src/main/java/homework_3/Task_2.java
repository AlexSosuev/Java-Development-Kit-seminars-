package homework_3;

//Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые, и
// false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать
// элементы одного типа.

public class Task_2 {
    public static void main(String[] args) {
        Integer[] intArray1 = {1, 2, 3, 4, 5};
        Integer[] intArray2 = {1, 2, 3, 4, 5};
        boolean result1 = compareArray(intArray1, intArray2);
        System.out.println("Результат для целочисленных массивов: " + result1);

        String[] stringArray1 = {"Hello", "World"};
        String[] stringArray2 = {"Hello", "World"};
        boolean result2 = compareArray(stringArray1, stringArray2);
        System.out.println("Результат для строковых массивов: " + result2);

        Double[] doubleArray1 = {1.5, 2.4, 3.7, 4.9, 5.5};
        Double[] doubleArray2 = {1.5, 2.4, 3.7};
        boolean result3 = compareArray(doubleArray1, doubleArray2);
        System.out.println("Результат, есди массивы разной длины: " + result3);

        Float[] floatArray1 = {1.5f, 2.4f, 3.7f, 4.9f, 5.5f};
        Float[] floatArray2 = {1.5f, 2.4f, 3.7f, 4.9f, 3.3f};
        boolean result4 = compareArray(floatArray1, floatArray2);
        System.out.println("Результат, есди массивы не одинаковые: " + result4);
    }

    private static <T> boolean compareArray(T[] arr, T[] brr) {
        if (arr.length != brr.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].getClass().equals(brr[i].getClass())) {
                return false;
            }
        }
        return true;
    }
}