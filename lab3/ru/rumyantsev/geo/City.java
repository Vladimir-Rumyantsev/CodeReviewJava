package lab3.ru.rumyantsev.geo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class City {
  private String name;
  private final Map<City, Path> paths = new HashMap<>();

  /**
   * Создаёт город с указанным именем и списком путей.
   * @param name Название города (не может быть null или пустым).
   * @param paths Список путей (может быть null).
   * @throws IllegalArgumentException если name == null или пустое.
   */
  public City(String name, List<Path> paths) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("City name cannot be null or empty");
    }
    this.name = name;
    if (paths != null) {
      setPaths(paths);
    }
  }

  /**
   * Создаёт город с указанным именем и пустым списком путей.
   * @param name Название города (не может быть null или пустым).
   * @throws IllegalArgumentException если name == null или пустое.
   */
  public City(String name) { this(name, null); }

  /**
   * Устанавливает новое название города.
   * @param name Новое название города (не может быть null или пустым).
   * @throws IllegalArgumentException если name == null или пустое.
   */
  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("City name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * Возвращает название города.
   * @return Название города.
   */
  public String getName() { return name; }

  /**
   * Обрабатывает новый путь: добавляет его или заменяет существующий, если стоимость ниже.
   * @param newPath Новый путь для обработки.
   */
  private void processPath(Path newPath) {
    City dest = newPath.getDestination();
    if (paths.containsKey(dest)) {
      Path existing = paths.get(dest);
      if (newPath.getCost() < existing.getCost()) {
        paths.put(dest, newPath);
      }
    } else {
      paths.put(dest, newPath);
    }
  }

  /**
   * Устанавливает пути из коллекции, заменяя текущие пути.
   * @param paths Коллекция путей для установки.
   */
  private void setPaths(Collection<Path> paths) {
    this.paths.clear();
    if (paths != null) {
      for (Path path : paths) {
        processPath(path);
      }
    }
  }

  /**
   * Устанавливает пути из массива, заменяя текущие пути.
   * @param paths Массив путей для установки.
   */
  public void setPath(Path[] paths) { setPaths(paths != null ? Arrays.asList(paths) : null); }

  /**
   * Устанавливает пути из списка, заменяя текущие пути.
   * @param paths Список путей для установки.
   */
  public void setPath(List<Path> paths) { setPaths(paths); }

  /**
   * Устанавливает пути из множества, заменяя текущие пути.
   * @param paths Множество путей для установки.
   */
  public void setPath(Set<Path> paths) { setPaths(paths); }

  /**
   * Проверяет наличие пути в указанный город.
   * @param city Город назначения для проверки.
   * @return true если путь существует, иначе false.
   */
  public boolean hasPathTo(City city) { return paths.containsKey(city); }

  /**
   * Возвращает список всех путей из города.
   * @return Неизменяемый список путей.
   */
  public List<Path> getPaths() { return List.copyOf(paths.values()); }

  /**
   * Добавляет новый путь к указанному городу назначения с заданной стоимостью.
   * Если путь к городу уже существует, заменяет его только если новая стоимость ниже.
   *
   * @param destination Город назначения (не может быть null).
   * @param cost Стоимость пути (должна быть положительной).
   * @throws IllegalArgumentException если destination == null или cost <= 0.
   */
  public void addPath(City destination, int cost) {
    if (destination == null) {
      throw new IllegalArgumentException("Destination city cannot be null");
    }
    if (cost <= 0) {
      throw new IllegalArgumentException("Cost must be positive");
    }
    addPath(new Path(destination, cost));
  }

  /**
   * Добавляет путь в текущий город. Если путь в тот же город назначения уже существует,
   * заменяет его только если стоимость нового пути ниже.
   *
   * @param path Путь для добавления (не может быть null).
   * @throws IllegalArgumentException если path == null.
   */
  public void addPath(Path path) {
    if (path == null) {
      throw new IllegalArgumentException("Path cannot be null");
    }
    processPath(path);
  }

  /**
   * Удаляет путь к указанному городу назначения, если он существует.
   *
   * @param destination Город назначения для удаления пути (не может быть null).
   * @throws IllegalArgumentException если destination == null.
   */
  public void removePath(City destination) {
    if (destination == null) {
      throw new IllegalArgumentException("Destination city cannot be null");
    }
    paths.remove(destination);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass() || paths.isEmpty()) return false;
    City city = (City) o;
    return Objects.equals(paths, city.paths);
  }

  @Override
  public int hashCode() { return Objects.hash(paths); }

  @Override
  public String toString() {
    if (paths.isEmpty()) return name + ": {}";
    return name + ": {" + paths.values().stream()
        .map(Path::toString)
        .collect(Collectors.joining(", ")) + "}";
  }
}
