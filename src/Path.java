package src;

/**
 * Представляет путь до города с указанием стоимости перемещения.
 * Объект неизменяем (immutable).
 */
public class Path {
  private final City destination;
  private final int cost;

  /**
   * Создаёт путь до указанного города.
   * @param destination Город назначения (не может быть null).
   * @param cost Стоимость пути (должна быть >= 0).
   * @throws IllegalArgumentException если destination == null или cost < 0.
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

  /**
   * Возвращает строковое представление пути в формате "(Название_города: стоимость)".
   * @return Непустая строка, например: "(Москва: 150)".
   */
  @Override
  public String toString() {
    return "(" + destination.getName() + ": " + cost + ")";
  }

  public City getDestination() {
    return destination;
  }

  public int getCost() {
    return cost;
  }
}
