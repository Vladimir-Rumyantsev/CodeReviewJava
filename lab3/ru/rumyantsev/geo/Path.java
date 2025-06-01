package lab3.ru.rumyantsev.geo;

import java.util.Objects;

/**
 * Представляет путь из одного города в другой с определённой стоимостью.
 * Два пути считаются ведущими в один и тот же город, если их города назначения равны.
 */
public final class Path {
  private final City destination;
  private final int cost;

  /**
   * Создаёт путь до указанного города с заданной стоимостью.
   *
   * @param destination Город назначения (не может быть null)
   * @param cost Стоимость пути (должна быть неотрицательной)
   * @throws IllegalArgumentException если destination == null или cost < 0
   */
  public Path(City destination, int cost) {
    if (destination == null) {
      throw new IllegalArgumentException("Город назначения не может быть null");
    }
    if (cost < 0) {
      throw new IllegalArgumentException("Стоимость пути не может быть отрицательной: " + cost);
    }
    this.destination = destination;
    this.cost = cost;
  }

  public City getDestination() { return destination; }

  public int getCost() { return cost; }

  /**
   * Проверяет, ведёт ли данный путь в тот же город, что и указанный путь.
   *
   * @param other Другой путь для сравнения (может быть null)
   * @return true если пути ведут в один город, false в противном случае
   */
  public boolean isDestinationSameAs(Path other) {
    if (other == null) return false;
    return destination.equals(other.destination);
  }

  /**
   * Проверяет, ведёт ли данный путь в указанный город.
   *
   * @param city Город для проверки (может быть null)
   * @return true если путь ведёт в указанный город, false в противном случае
   */
  public boolean isDestination(City city) {
    if (city == null) return false;
    return destination.equals(city);
  }

  /**
   * Сравнивает объекты на равенство. Два пути считаются равными, если:
   * 1. Это один и тот же объект
   * 2. Оба пути ведут в один город (по equals) и имеют одинаковую стоимость
   *
   * @param obj Объект для сравнения
   * @return true если объекты равны, false в противном случае
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Path path = (Path) obj;
    return cost == path.cost && Objects.equals(destination, path.destination);
  }

  /**
   * Возвращает хэш-код пути на основе города назначения и стоимости.
   *
   * @return Хэш-код объекта
   */
  @Override
  public int hashCode() { return Objects.hash(destination, cost); }

  /**
   * Возвращает строковое представление пути в формате:
   * (ИмяГорода: стоимость)
   *
   * @return Строковое представление пути
   */
  @Override
  public String toString() { return "(" + destination.getName() + ": " + cost + ")"; }
}
