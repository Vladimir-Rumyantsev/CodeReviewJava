package lab3.ru.rumyantsev.geo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Представляет маршрут между двумя городами. Маршрут всегда имеет начальный и конечный город.
 * Поддерживает изменение городов начала и конца маршрута, а также вычисление оптимального пути.
 */
public class Route {
  private City startCity;
  private City endCity;

  /**
   * Создаёт маршрут между указанными городами.
   *
   * @param startCity Начальный город маршрута (не может быть null)
   * @param endCity Конечный город маршрута (не может быть null)
   * @throws IllegalArgumentException если startCity или endCity == null
   */
  public Route(City startCity, City endCity) {
    if (startCity == null || endCity == null) {
      throw new IllegalArgumentException("Start and end cities must not be null");
    }
    this.startCity = startCity;
    this.endCity = endCity;
  }

  /**
   * Устанавливает новый начальный город маршрута.
   *
   * @param startCity Новый начальный город (не может быть null)
   * @throws IllegalArgumentException если startCity == null
   */
  public void setStartCity(City startCity) {
    if (startCity == null) {
      throw new IllegalArgumentException("Start city must not be null");
    }
    this.startCity = startCity;
  }

  /**
   * Устанавливает новый конечный город маршрута.
   *
   * @param endCity Новый конечный город (не может быть null)
   * @throws IllegalArgumentException если endCity == null
   */
  public void setEndCity(City endCity) {
    if (endCity == null) {
      throw new IllegalArgumentException("End city must not be null");
    }
    this.endCity = endCity;
  }

  public City getStartCity() { return startCity; }

  public City getEndCity() { return endCity; }

  /**
   * Возвращает массив городов, представляющий маршрут от начального города ({@code startCity})
   * до конечного города ({@code endCity}). Если маршрут не найден, возвращает пустой массив.
   * <p>
   * Алгоритм использует рекурсивный поиск в глубину (DFS) с откатом (backtracking), чтобы найти путь.
   * Маршрут формируется в порядке посещения городов, начиная с {@code startCity} и заканчивая {@code endCity}.
   * </p>
   *
   * @return Массив городов {@code City[]}, представляющий маршрут, или пустой массив, если путь не существует.
   * @see #findRoute(City, City, List)
   */
  public City[] getRoute() {
    List<City> route = new ArrayList<>();
    if (findRoute(startCity, endCity, route)) return route.toArray(new City[0]);
    else return new City[0];
  }

  /**
   * Рекурсивно ищет маршрут между текущим городом ({@code current}) и целевым городом ({@code destination}),
   * заполняя список {@code route} пройденными городами. Использует алгоритм DFS с проверкой на циклы.
   * <p>
   * Если целевой город достигнут, возвращает {@code true}, и маршрут сохраняется в списке {@code route}.
   * Если путь не найден, происходит откат (backtracking) — последний город удаляется из маршрута.
   * </p>
   *
   * @param current Текущий город, который рассматривается в данной итерации.
   * @param destination Конечный город, до которого нужно найти маршрут.
   * @param route Список, в который добавляются города по мере их посещения (изменяется в процессе поиска).
   * @return {@code true}, если маршрут найден, {@code false} — если путь не существует.
   */
  private boolean findRoute(City current, City destination, List<City> route) {
    route.add(current);

    if (current.equals(destination)) {
      return true;
    }

    for (Path path : current.getPaths()) {
      City nextCity = path.getDestination();
      if (!route.contains(nextCity)) {
        if (findRoute(nextCity, destination, route)) {
          return true;
        }
      }
    }

    route.remove(route.size() - 1);
    return false;
  }

  /**
   * Возвращает строковое представление маршрута в формате:
   * "Город 1 -> Город 2 -> ... -> Город N"
   *
   * @return Строковое представление маршрута или "No route found", если маршрут не существует
   */
  @Override
  public String toString() {
    City[] route = getRoute();
    if (route.length == 0) return "Маршрут не найден";
    return Arrays.stream(route)
        .map(City::getName)
        .collect(Collectors.joining(" -> "));
  }
}
