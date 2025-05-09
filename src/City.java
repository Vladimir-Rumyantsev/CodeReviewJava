package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет город с именем и списком путей до других городов.
 * Список путей может быть изменён после создания объекта.
 */
public class City {
  private final String name;
  private List<Path> paths;

  /**
   * Создаёт город с указанным именем и списком путей.
   * @param name Название города (не может быть null или пустым).
   * @param paths Список путей (может быть null, тогда используется пустой список).
   * @throws IllegalArgumentException если name == null или пустое.
   */
  public City(String name, List<Path> paths) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Название города не может быть null или пустым");
    }
    this.name = name;
    this.paths = (paths != null) ? paths : new ArrayList<>();
  }

  /**
   * Создаёт город с указанным именем и пустым списком путей, через предыдущий конструктор.
   */
  public City(String name) {
    this(name, null);
  }

  /**
   * Устанавливает новый список путей для города.
   * @param paths Список путей (может быть null, тогда используется пустой список).
   */
  public void setPath(List<Path> paths) {
    this.paths = (paths != null) ? paths : new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  /**
   * Возвращает строку в формате "Название_города: {(Доступный_город: стоимость), (Доступный_город: стоимость)}"
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(name + ": {");
    for (Path path : paths) {
      result.append(path.toString()).append(", ");
    }
    if (result.length() >= 2) {
      result.delete(result.length() - 2, result.length());
    }

    return result.append("}").toString();
  }
}
