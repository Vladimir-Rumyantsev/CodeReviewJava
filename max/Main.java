package max;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Главный класс для демонстрации выполнения лабораторной работы.
 * Содержит меню для выбора заданий и демонстрации результатов.
 */
public class Main {
  static Main main = new Main();
  static Scanner scanner = new Scanner(System.in);

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
      throw new IllegalArgumentException("Минимальное значение не может быть больше максимального");
    }
    while (true) {
      try {
        if (prompt != null) {
          System.out.print(prompt);
        }
        int value = Integer.parseInt(scanner.nextLine());
        if (value >= minVal && value <= maxVal) {
          return value;
        }
        System.out.printf("Error: Value must be between %d and %d%n", minVal, maxVal);
      } catch (NumberFormatException e) {
        System.out.println("Error: Invalid input. Expected integer number.");
      }
    }
  }

  public static void main(String[] args) {

    System.out.print(
      """
      
      ЛАБОРАТОРНАЯ РАБОТА №2
      ВЫПОЛНИЛ - КУЛАКОВ МАКСИМ ИТ-6 (ПМИ-10/2023)
      ВАРИАНТ 2
      
      Задание 1 - задания 3 темы 1
      Задание 2 - задания 4 темы 1
      Задание 3 - задание 4 темы 2
      Задание 4 - задание 4 темы 3
      Задание 5 - задание 4 темы 4
      Задание 6 - задание 4 темы 5
      Что-бы выйти - введите 0

      Чтобы просмотреть интересующее вас задание - введите его номер.
      """
    );
    main.choice();
  }

  /**
   * Обрабатывает главное меню и распределяет по заданиям в зависимости от выбора пользователя.
   */
  public void choice() {
    while(true) {
      int nextInt = inputNumber(
        "\nВведите число (от 0 до 6) для выбора дальнейшего действия: ", 0, 6
      );
      if (nextInt == 0) {
        System.out.println("\nРабота программы завершена!");
        break;
      }
      else {
        System.out.println("\n————————————————————————————————————————————————————————————");
        tasks(nextInt);
        System.out.print(
          """
          
          ————————————————————————————————————————————————————————————
          
          Задание 1 - задания 3 темы 1
          Задание 2 - задания 4 темы 1
          Задание 3 - задание 4 темы 2
          Задание 4 - задание 4 темы 3
          Задание 5 - задание 4 темы 4
          Задание 6 - задание 4 темы 5
          Что-бы выйти - введите 0
          
          Чтобы просмотреть интересующее вас задание - введите его номер.
          """
        );
      }
    }
  }

  /**
   * Демонстрирует задание исходя из выбранного номера.
   * @param selectedTask Номер задания (от 1 до 6)
   */
  public void tasks(int selectedTask) {
    switch (selectedTask) {
      case 1:
        System.out.println(
          """
          
          Текст задания 3 темы 1
          
          Имена.
          Создайте сущность Имя, которая описывается тремя параметрами: Фамилия, Личное имя,
          Отчество. Имя может быть приведено к строковому виду, включающему традиционное
          представление всех трех параметров: Фамилия Имя Отчество (например “Иванов Иван
          Иванович”). Необходимо предусмотреть возможность того, что какой-либо из параметров
          может быть не задан, и в этом случае он не учитывается при приведении к текстовому виду.
          Необходимо создать следующие имена:
           Клеопатра
           Пушкин Александр Сергеевич
           Маяковский Владимир
          Обратите внимание, что при выводе на экран, не заданные параметры никак не участвуют в
          образовании строки.
          
          """
        );
        System.out.println("Результат работы:");

        PersonName cleopatra = new PersonName("Клеопатра");
        PersonName pushkin = new PersonName("Александр", "Пушкин", "Сергеевич");
        PersonName mayakovsky = new PersonName("Владимир", "Маяковский");
        System.out.println(cleopatra);
        System.out.println(pushkin);
        System.out.println(mayakovsky);

        break;

      case 2:
        System.out.println(
          """
          
          Текст задания 4 темы 1
          
          Время.
          Создайте сущность Время, которое будет описывать текущее время суток в 24-х часовом
          формате. Время описывается числом секунд, прошедшим с начала суток. Время может быть
          приведено к текстовой форме следующего формата: “ЧЧ:ММ:СС”. Например,если время задано
          как 12000, то текстовая форма будет “3:20:00”. Если общее время превышает 24 часа,
          то отображаться в текстовом виде должно только то время, которое прошло с начала
          последних суток, например 91800, это не 25:30:00, а 1:30:00.
          Необходимо создать и вывести на экран текстовую форму для следующих вариантов времени:
           10 секунд
           10000 секунд
           100000 секунд
          
          """
        );
        int seconds = inputNumber("Введите время в секундах: ", 0, Integer.MAX_VALUE);
        System.out.println("Результат работы: " + new Time(seconds));
        break;

      case 3:
        System.out.println(
          """
          
          Текст задания 4 темы 2
          
          Сотрудники и отделы.
          Создайте сущность Сотрудник, которая описывается именем (в строковой форме) и отделом,
          в котором сотрудник работает, причем у каждого отдела есть название и начальник, который
          также является Сотрудником. Сотрудник может быть приведен к текстовой форме вида: “Имя
          работает в отделе Название, начальник которого Имя”. В случае если сотрудник является
          руководителем отдела, то текстовая форма должна быть “Имя начальник отдела Название”.
          Необходимо выполнить следующие задачи:
          1. Создать Сотрудников Петрова, Козлова, Сидорова работающих в отделе IT.
          2. Сделать Козлова начальником IT отдела.
          3. Вывести на экран текстовое представление всех трех Сотрудников (у всех троих должен
          оказаться один и тот же отдел и начальник).
          
          """
        );
        System.out.println("Результат работы: ");

        Worker kozlov = new Worker("Козлов");
        Department departmentIT = new Department("IT", kozlov);
        Worker petrov = new Worker("Петров", departmentIT);
        Worker sidorov = new Worker("Сидоров", departmentIT);

        System.out.println(kozlov);
        System.out.println(petrov);
        System.out.println(sidorov);
        break;

      case 4:
        System.out.println(
          """
          
          Текст задания 4 темы 3
          
          Сотрудники и отделы.
          Измените решение, полученное в задаче 2.4 таким образом,
          чтобы имея ссылку на сотрудника,
          можно было бы узнать список всех сотрудников этого отдела.
          
          """
        );

        Worker employee = new Worker("Козлов");
        Department department = new Department("IT", employee);
        department.addWorker(new Worker("Петров", department));
        department.addWorker(new Worker("Сидоров", department));

        List<String> allDepartmentEmployees = new ArrayList<>();
        for (Worker worker : employee.getAllDepartmentEmployees()) {
          allDepartmentEmployees.add(worker.getName());
        }
        System.out.println(
          "В отделе " + department.getDepartmentName() +
          " работают сотрудники: " + allDepartmentEmployees
        );

        break;

      case 5:
        System.out.println(
          """
          
          Текст задания 4 темы 4
          
          Создаем Время.
          Измените сущность Время из задачи 1.4. Новые требования включают:
           Время можно создать указав количество секунд с начала суток
           Время можно создать указав количество часов, минут, секунд текущего времени.
           Гарантируйте, что операция присвоения в инициализаторах будет использована не более
          одного раза
          Необходимо создать и вывести на экран текстовую форму для следующих вариантов времени:
          1. 10000 секунд
          2. 2 часа, 3 минуты, 5 секунд
          
          """
        );

        System.out.println("Результат: " + main.promptForTime());
        break;

      case 6:
        System.out.println(
          """
          
          Текст задания 4 темы 5
          
          Сколько сейчас времени?
          Измените сущность Время из задачи 4.4.
          Добавьте ей возможность возвращать следующие сведения:
           Какой сейчас час (целое число)
           Сколько минут прошло с начала текущего часа (целое число)
           Сколько секунд прошло с начала текущей минуты (целое число)
          Необходимо выполнить следующие задачи:
          1. Вывести на экран сколько часов соответствуют времени 34056
          2. Вывести на экран сколько минут соответствуют времени 4532
          3. Вывести на экран сколько секунд соответствуют времени 123
          
          """
        );
        Time time = main.promptForTime();
        int inputNumber = inputNumber(
          "Выберите, что хотите узнать: 1 - часы, 2 - минуты, 3 - секунды: ", 1, 3
        );
        if (inputNumber == 1) {
          System.out.println("Результат: " + time.getHours());
        }
        else if (inputNumber == 2) {
          System.out.println("Результат: " + time.getMinutes());
        }
        else {
          System.out.println("Результат: " + time.getSeconds());
        }
        break;
    }
  }

  /**
   * Обрабатывает повторяющуюся в нескольких заданиях работу с сущностью Time.
   * @return Возвращает сущность Time с введёнными параметрами
   */
  public Time promptForTime() {
    int inputNumber = inputNumber(
      "Выберите режим работы. 1 - ввести секунды, 2 - ввести часы, минуты, секунды: ", 1, 2
    );
    if (inputNumber == 1) {
      int seconds = inputNumber("Введите время в секундах: ", 0, Integer.MAX_VALUE);
      return new Time(seconds);
    }
    else {
      int hours = inputNumber("Введите часы: ", 0, 23);
      int minutes = inputNumber("Введите минуты: ", 0, 59);
      int seconds = inputNumber("Введите секунды: ", 0, 59);
      return new Time(hours, minutes, seconds);
    }
  }
}
