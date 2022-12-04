import java.util.InputMismatchException;
import java.util.Scanner;

/*
Перед вами программа-калькулятор сложных процентов.
Допишите код классов-исключений LimitException (для ограничения количества попыток) и InputException (для ошибок при вводе),
а также добавьте их обработку.

Исключение LimitException должно быть унаследовано от класса RuntimeException.
Помимо текста исключения оно должно принимать количество попыток ввода attempts в виде целого числа.

Добавьте обработку LimitException в методе main().
При превышении лимита попыток предусмотрите вывод сообщения: Превышен лимит ошибок ввода: n, где n — количество реальных попыток.

Исключение InputException должно быть унаследовано от класса Exception.
При обработке ошибки предусмотрите вывод для пользователей следующих сообщений:
Введено отрицательное значение;
Введено не число;
Ошибка ввода: <подробное сообщение об ошибке>.
Сгенерируйте нужные исключения внутри класса FinancialCalculatorException.
*/
public class Main {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            calculate();
            // перехват исключения LimitException
        } catch (LimitException e) {
            System.out.println(e.getMessage() + e.attempts);
        }

    }

    public static double getInterest(final double rate, final int time, final double principal) {
        final double multiplier = Math.pow(1.0 + rate / 100.0, time) - 1.0;
        return multiplier * principal;
    }

    public static int getIntLimited(String greeting, int attempts) {
        for (int counter = 0; counter < attempts; counter++) {
            try {
                System.out.println(greeting + " => ");
                try {
                    final int value = scanner.nextInt();
                    // проверка на отрицательное значение
                    // сгенерируйте исключение "Введено отрицательное значение"
                    if (value < 0) {
                        throw new InputException("Введено отрицательное значение");
                    }
                    return value;

                } catch (InputMismatchException exception) {
                    // сгенерируйте исключение "Введено не число"
                    throw new InputException("Введено не число");
                }

            } catch (InputException exception) {
                // сгенерируйте вывод формата "Ошибка ввода: " + информация об исключении
                System.out.println("Ошибка ввода: ");
                exception.printStackTrace();
            }
        }
        // сгенерируйте исключение LimitException с сообщением "Превышен лимит ошибок ввода"
        throw new LimitException(attempts, "Превышен лимит ошибок ввода");
    }

    public static double getDoubleLimited(String greeting, int attempts) {
        for (int counter = 0; counter < attempts; counter++) {
            try {
                System.out.println(greeting + " => ");
                // добавьте недостающий код
                try {
                    final double value = scanner.nextDouble();
                    if (value < 0) {
                        throw new InputException("Введено отрицательное значение");
                    }
                    return value;
                } catch (InputMismatchException exception) {
                    throw new InputException("Введено не число");
                }
            } catch (InputException exception) {
                System.out.println("Ошибка ввода: ");
                exception.printStackTrace();
            }
        }
        throw new LimitException(attempts, "Превышен лимит ошибок ввода: ");
    }

    public static void calculate() {
        final double rate = getDoubleLimited("Введите ставку", 3);
        final double principal = getDoubleLimited("Введите размер вклада", 3);
        final int time = getIntLimited("Введите срок вклада в месяцах", 5);
        System.out.println("Ваша выгода = " + getInterest(rate, time, principal));
    }
}
