package lab3.ru.rumyantsev.main;

import lab3.ru.rumyantsev.geo.City;
import lab3.ru.rumyantsev.geo.Route;
import lab3.ru.rumyantsev.math.Fraction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Главный класс приложения, демонстрирующий функциональность классов:
 * - Fraction (дроби)
 * - City, Path (города и пути)
 * - Route (маршруты)
 *
 * <p>Предоставляет интерактивное меню для взаимодействия с сущностями.
 * Обрабатывает некорректный ввод пользователя с помощью универсальных методов ввода.
 */
public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  private static final Map<String, City> cities = new HashMap<>();
  private static final List<Fraction> fractions = new ArrayList<>();

  /**
   * Точка входа в приложение. Обрабатывает аргументы командной строки для возведения в степень.
   *
   * @param args Аргументы командной строки (для возведения в степень)
   */
  public static void main(String[] args) {
    System.out.println("\nЗадание 7.3: Обработка аргументов командной строки");
    if (args.length >= 2) {
      double result = power(args[0], args[1]);
      System.out.printf("Результат возведения %s в степень %s: %.2f%n", args[0], args[1], result);
    } else {
      System.out.println("Для возведения в степени требуется два аргумента.");
    }
    System.out.println();

    showMainMenu();
    scanner.close();
  }

  /**
   * Обрабатывает вводимые с клавиатуры целочисленные значения.
   * @param prompt Текст выводимый в консоль с указанием, какие данные ожидаются
   * @param minVal Минимальное целочисленное значение
   * @param maxVal Максимальное целочисленное значение
   * @throws IllegalArgumentException если minVal > maxVal
   * @return Возвращает целое число в заданных пределах
   */
  public static int inputNumber(String prompt, int minVal, int maxVal) {
    if (minVal > maxVal) {
      throw new IllegalArgumentException(
          "Минимальное значение не может быть больше максимального"
      );
    }
    while (true) {
      try {
        if (prompt != null) System.out.print(prompt);
        int value = Integer.parseInt(scanner.nextLine());
        if (value >= minVal && value <= maxVal) return value;
        System.out.printf(
            "Ошибка: Значение должно быть в диапазоне от %d до %d%n", minVal, maxVal
        );
      } catch (NumberFormatException e) {
        System.out.println("Ошибка: Ожидается целое число.");
      }
    }
  }

  /**
   * Возвращает результат возведения X в степень Y.
   *
   * @param xStr Строковое представление основания степени
   * @param yStr Строковое представление показателя степени
   * @return Результат возведения в степень или 0 при ошибке
   */
  public static double power(String xStr, String yStr) {
    try {
      int x = Integer.parseInt(xStr);
      int y = Integer.parseInt(yStr);
      return Math.pow(x, y);
    } catch (NumberFormatException ex) {
      return 0;
    }
  }

  /**
   * Выводит главное меню и обрабатывает выбор пользователя.
   */
  private static void showMainMenu() {
    while (true) {
      System.out.println("\nГлавное меню:");
      System.out.println("1. Работа с дробями");
      System.out.println("2. Работа с городами и путями");
      System.out.println("3. Работа с маршрутами");
      System.out.println("4. Демонстрация суммирования чисел");
      System.out.println("5. Возведение в степень (ручной ввод)");
      System.out.println("0. Выход");

      int choice = inputNumber("Выберите опцию: ", 0, 5);
      switch (choice) {
        case 1 -> demonstrateFractions();
        case 2 -> demonstrateCitiesAndPaths();
        case 3 -> demonstrateRoutes();
        case 4 -> demonstrateSumNumbers();
        case 5 -> demonstratePower();
        case 0 -> { return; }
      }
    }
  }

  /**
   * Демонстрирует работу с дробями: создание, операции, клонирование.
   */
  private static void demonstrateFractions() {
    while (true) {
      System.out.println("\nМеню дробей:");
      System.out.println("1. Создать новую дробь");
      System.out.println("2. Показать все дроби");
      System.out.println("3. Сложить две дроби");
      System.out.println("4. Вычесть две дроби");
      System.out.println("5. Умножить две дроби");
      System.out.println("6. Разделить две дроби");
      System.out.println("7. Клонировать дробь");
      System.out.println("8. Сравнить две дроби");
      System.out.println("0. Назад");

      int choice = inputNumber("Выберите опцию: ", 0, 9);
      switch (choice) {
        case 1 -> createFraction();
        case 2 -> showFractions();
        case 3 -> addFractions();
        case 4 -> subtractFractions();
        case 5 -> multiplyFractions();
        case 6 -> divideFractions();
        case 7 -> cloneFraction();
        case 8 -> compareFractions();
        case 0 -> { return; }
      }
    }
  }

  private static void createFraction() {
    try {
      System.out.println("\nСоздание новой дроби:");
      int num = inputNumber("Введите числитель: ", Integer.MIN_VALUE, Integer.MAX_VALUE);

      int denom;
      while (true) {
        denom = inputNumber("Введите знаменатель (не 0): ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (denom != 0) break;
        System.out.println("Ошибка: Знаменатель не может быть нулем.");
      }

      fractions.add(new Fraction(num, denom));
      System.out.println("Дробь создана: " + fractions.getLast());
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void showFractions() {
    if (fractions.isEmpty()) {
      System.out.println("Список дробей пуст");
        return;
    }

    System.out.println("\nТекущие дроби:");
    for (int i = 0; i < fractions.size(); i++) {
      Fraction f = fractions.get(i);
      System.out.printf("%d. %s = %.4f%n", i + 1, f, f.doubleValue());
    }
  }

  /**
   * Выбирает дробь по индексу с валидацией.
   * @param prompt Приглашение для ввода
   * @return Выбранная дробь
   */
  private static Fraction selectFraction(String prompt) {
    if (fractions.isEmpty()) {
      throw new IllegalStateException("Список дробей пуст");
    }

    showFractions();
    int maxIndex = fractions.size();
    int index = inputNumber(prompt, 1, maxIndex) - 1;
    return fractions.get(index);
  }

  private static void addFractions() {
    if (fractions.size() < 2) {
      System.out.println("Нужно минимум две дроби");
      return;
    }

    try {
      Fraction f1 = selectFraction("Выберите первую дробь: ");
      Fraction f2 = selectFraction("Выберите вторую дробь: ");

      Fraction result = f1.sum(f2);
      System.out.printf("\nРезультат сложения: %s + %s = %s (≈%.4f)%n",
          f1, f2, result, result.doubleValue());
      fractions.add(result);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void subtractFractions() {
    if (fractions.size() < 2) {
      System.out.println("Нужно минимум две дроби");
      return;
    }

    try {
      Fraction f1 = selectFraction("Выберите уменьшаемую дробь: ");
      Fraction f2 = selectFraction("Выберите вычитаемую дробь: ");

      Fraction result = f1.minus(f2);
      System.out.printf("\nРезультат вычитания: %s - %s = %s (≈%.4f)%n",
          f1, f2, result, result.doubleValue());
      fractions.add(result);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void multiplyFractions() {
    if (fractions.size() < 2) {
      System.out.println("Нужно минимум две дроби");
      return;
    }

    try {
      Fraction f1 = selectFraction("Выберите первую дробь: ");
      Fraction f2 = selectFraction("Выберите вторую дробь: ");

      Fraction result = f1.multiply(f2);
      System.out.printf("\nРезультат умножения: %s * %s = %s (≈%.4f)%n",
          f1, f2, result, result.doubleValue());
      fractions.add(result);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void divideFractions() {
    if (fractions.size() < 2) {
      System.out.println("Нужно минимум две дроби");
      return;
    }

    try {
      Fraction f1 = selectFraction("Выберите делимое: ");
      Fraction f2 = selectFraction("Выберите делитель: ");

      if (f2.getNumerator() == 0) {
        System.out.println("Ошибка: Деление на ноль невозможно");
        return;
      }

      Fraction result = f1.div(f2);
      System.out.printf("\nРезультат деления: %s / %s = %s (≈%.4f)%n",
          f1, f2, result, result.doubleValue());
      fractions.add(result);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void multiplyFractionByNumber() {
    if (fractions.isEmpty()) {
      System.out.println("Список дробей пуст");
      return;
    }

    try {
      Fraction f = selectFraction("Выберите дробь: ");
      int multiplier = inputNumber("Введите множитель (целое число): ",
          Integer.MIN_VALUE, Integer.MAX_VALUE);

      Fraction result = f.multiply(multiplier);
      System.out.printf("\nРезультат умножения: %s * %d = %s (≈%.4f)%n",
          f, multiplier, result, result.doubleValue());
      fractions.add(result);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void cloneFraction() {
    if (fractions.isEmpty()) {
      System.out.println("Список дробей пуст");
      return;
    }

    try {
      Fraction original = selectFraction("Выберите дробь для клонирования: ");
      Fraction clone = original.clone();
      fractions.add(clone);
      System.out.printf("Клон создан: %s (оригинал: %s)%n", clone, original);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void compareFractions() {
    if (fractions.size() < 2) {
      System.out.println("Нужно минимум две дроби");
      return;
    }

    try {
      Fraction f1 = selectFraction("Выберите первую дробь: ");
      Fraction f2 = selectFraction("Выберите вторую дробь: ");

      System.out.printf("\nСравнение дробей: %s и %s%n", f1, f2);
      System.out.printf("Численное значение: %.4f и %.4f%n",
          f1.doubleValue(), f2.doubleValue());
      System.out.printf("Дроби %sравны%n", f1.equals(f2) ? "" : "не ");
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  /**
   * Демонстрирует работу с городами: создание, добавление/удаление путей.
   */
  private static void demonstrateCitiesAndPaths() {
    while (true) {
      System.out.println("\nМеню городов:");
      System.out.println("1. Создать город");
      System.out.println("2. Показать все города");
      System.out.println("3. Добавить путь между городами");
      System.out.println("4. Удалить путь");
      System.out.println("5. Сравнить два города");
      System.out.println("0. Назад");

      int choice = inputNumber("Выберите опцию: ", 0, 5);
      switch (choice) {
        case 1 -> createCity();
        case 2 -> showCities();
        case 3 -> addPath();
        case 4 -> removePath();
        case 5 -> compareCities();
        case 0 -> { return; }
      }
    }
  }

  private static void createCity() {
    System.out.print("\nВведите название города: ");
    String name = scanner.nextLine();

    if (name.isBlank()) {
      System.out.println("Ошибка: Название не может быть пустым");
      return;
    }

    if (cities.containsKey(name)) {
      System.out.println("Ошибка: Город с таким именем уже существует");
      return;
    }

    cities.put(name, new City(name));
    System.out.println("Город '" + name + "' создан");
  }

  /**
   * Выбирает город из списка с валидацией.
   * @param prompt Приглашение для ввода
   * @return Выбранный город
   */
  private static City selectCity(String prompt) {
    if (cities.isEmpty()) {
      throw new IllegalStateException("Список городов пуст");
    }

    System.out.println("\nДоступные города:");
    List<String> cityNames = new ArrayList<>(cities.keySet());
    for (int i = 0; i < cityNames.size(); i++) {
      System.out.printf("%d. %s%n", i + 1, cityNames.get(i));
    }

    int index = inputNumber(prompt, 1, cityNames.size()) - 1;
    return cities.get(cityNames.get(index));
  }

  private static void showCities() {
    if (cities.isEmpty()) {
      System.out.println("Список городов пуст");
      return;
    }

    System.out.println("\nТекущие города и пути:");
    cities.forEach((name, city) -> {
      String paths = city.getPaths().stream()
          .map(p -> p.getDestination().getName() + "(" + p.getCost() + ")")
          .collect(Collectors.joining(", "));
      System.out.printf("%s: {%s}%n", name, paths);
    });
  }

  private static void addPath() {
    if (cities.size() < 2) {
      System.out.println("Нужно минимум два города");
      return;
    }

    try {
      System.out.println("\nДобавление пути:");
      City from = selectCity("Выберите город отправления: ");
      City to = selectCity("Выберите город назначения: ");

      if (from.equals(to)) {
        System.out.println("Ошибка: Нельзя создать путь в тот же город");
        return;
      }

      int cost = inputNumber(
          "Введите стоимость пути (положительное число): ", 1, Integer.MAX_VALUE
      );

      from.addPath(to, cost);
      System.out.printf("Путь добавлен: %s -> %s (%d)%n",
          from.getName(), to.getName(), cost);
      } catch (IllegalArgumentException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void removePath() {
    if (cities.isEmpty()) {
      System.out.println("Список городов пуст");
      return;
    }

    try {
      System.out.println("\nУдаление пути:");
      City from = selectCity("Выберите город отправления: ");
      City to = selectCity("Выберите город назначения: ");

      if (!from.hasPathTo(to)) {
        System.out.println("Ошибка: Путь не существует");
        return;
      }

      from.removePath(to);
      System.out.println("Путь удален: " + from.getName() + " -> " + to.getName());
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  private static void compareCities() {
    if (cities.size() < 2) {
      System.out.println("Нужно минимум два города");
      return;
    }

    try {
      System.out.println("\nСравнение городов:");
      City city1 = selectCity("Выберите первый город: ");
      City city2 = selectCity("Выберите второй город: ");

      System.out.printf("Города %s и %s %sравны%n",
          city1.getName(), city2.getName(),
          city1.equals(city2) ? "" : "не ");
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  /**
   * Демонстрирует работу с маршрутами: создание, поиск пути.
   */
  private static void demonstrateRoutes() {
    if (cities.size() < 2) {
      System.out.println("Нужно минимум два города");
      return;
    }

    try {
      System.out.println("\nПостроение маршрута:");
      City start = selectCity("Выберите город отправления: ");
      City end = selectCity("Выберите город назначения: ");

      Route route = new Route(start, end);
      System.out.println(route);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }

  /**
   * Демонстрирует суммирование чисел разных типов.
   */
  private static void demonstrateSumNumbers() {
    System.out.println("\nПримеры суммирования (задание 5.1):");

    List<Number> example1 = List.of(2, new Fraction(3, 5), 2.3);
    System.out.printf("Пример 1: %s = %.2f%n",
        formatSum(example1), sumNumbers(example1));

    List<Number> example2 = List.of(3.6, new Fraction(49, 12), 3, new Fraction(3, 2));
    System.out.printf("Пример 2: %s = %.2f%n",
        formatSum(example2), sumNumbers(example2));

    List<Number> example3 = List.of(new Fraction(1, 3), 1);
    System.out.printf("Пример 3: %s = %.2f%n",
        formatSum(example3), sumNumbers(example3));
  }

  /**
   * Форматирует список чисел для вывода в виде суммы.
   */
  private static String formatSum(Collection<Number> nums) {
    return nums.stream()
        .map(n -> {
          if (n instanceof Fraction f) return f.toString();
          if (n instanceof Double d) return String.format("%.1f", d);
          return n.toString();
        })
        .collect(Collectors.joining(" + "));
  }

  /**
   * Суммирует коллекцию чисел разных типов.
   *
   * @param nums Коллекция чисел
   * @return Сумма чисел в вещественном формате
   */
  public static double sumNumbers(Collection<Number> nums) {
    return nums.stream()
        .mapToDouble(Number::doubleValue)
        .sum();
  }

  /**
   * Демонстрирует ручной ввод для возведения в степень.
   */
  private static void demonstratePower() {
    try {
      System.out.println("\nРучное возведение в степень:");
      int base = inputNumber("Введите основание степени: ",
          Integer.MIN_VALUE, Integer.MAX_VALUE);
      int exponent = inputNumber("Введите показатель степени: ",
          Integer.MIN_VALUE, Integer.MAX_VALUE);

      double result = Math.pow(base, exponent);
      System.out.printf("Результат: %d ^ %d = %.2f%n", base, exponent, result);
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
    }
  }
}
