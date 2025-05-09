import java.util.List;

public class City {
  private final String name;
  private List<Path> paths;

  public City(String name) {
    this.name = name;
    this.paths = null;
  }

  public City(String name, List<Path> paths) {
    this.name = name;
    this.paths = paths;
  }

  public void setPath(List<Path> paths) {
    this.paths = paths;
  }

  public String getName() {
    return name;
  }

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
