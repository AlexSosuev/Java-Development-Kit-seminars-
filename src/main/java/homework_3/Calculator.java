package homework_3;

//Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(),
// divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.

public class Calculator {
    public static <N extends Number> double sum(N number1, N number2) {
        return number1.doubleValue() + number2.doubleValue();
    }

    public static <N extends Number> double subtract(N number1, N number2) {
        return number1.doubleValue() - number2.doubleValue();
    }

    public static <N extends Number> double multiply(N number1, N number2) {
        return number1.doubleValue() * number2.doubleValue();
    }

    public static <N extends Number> double divide(N number1, N number2) {
        if (number2.doubleValue() == 0) {
            throw new ArithmeticException("Делить на ноль нельзя!");
        }
        return number1.doubleValue() / number2.doubleValue();
    }
}
