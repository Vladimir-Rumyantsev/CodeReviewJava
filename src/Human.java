package src;

/**
 * Представляет человека с именем (в виде строки или объекта Name) и ростом.
 * Поддерживает вывод информации в консоль.
 */
public class Human {
  /**
   * Внутренний класс для хранения имени человека в двух форматах: строки или объекта Name.
   */
  static class HumanName {
    private String stringName;
    private Name customName;

    private HumanName(String name) {
      this.stringName = name != null ? name : "Неизвестное имя";
    }

    private HumanName(Name name) {
      if (name != null) {
        this.customName = name;
      } else {
        this.stringName = "Неизвестное имя";
      }
    }

    public String toString() {
      return (customName != null) ? customName.toString() : stringName;
    }
  }

  private HumanName name;
  private int height;

  /**
   * Создаёт человека с именем в виде строки и указанным ростом.
   * @param name Имя (может быть null, тогда используется "Неизвестное имя").
   * @param height Рост в сантиметрах (должен быть > 0).
   * @throws IllegalArgumentException если height <= 0.
   */
  public Human(String name, int height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Рост не может быть не натуральным числом");
    }
    this.name = new HumanName(name);
    this.height = height;
  }

  /**
   * Создаёт человека с именем в виде объекта Name и указанным ростом.
   * @param name Имя (может быть null, тогда используется "Неизвестное имя").
   * @param height Рост в сантиметрах (должен быть > 0).
   * @throws IllegalArgumentException если height <= 0.
   */
  public Human(Name name, int height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Рост не может быть не натуральным числом");
    }
    this.name = new HumanName(name);
    this.height = height;
  }

  public Human() {
    this.name = new HumanName("Неизвестное имя");
    this.height = 1;
  }

  public void setName(String name) {
    this.name = new HumanName(name);
  }

  public void setName(Name name) {
    this.name = new HumanName(name);
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return (name + ", рост: " + height);
  }

  public void printInformation() {
    this.printInformation("\n");
  }

  /**
   * Выводит информацию о человеке в консоль с заданным окончанием строки.
   * @param end Строка, добавляемая в конце вывода (например, "\n").
   */
  public void printInformation(String end) {
    System.out.print("Человек с именем \"" + name + "\" и ростом " + height + end);
  }
}
