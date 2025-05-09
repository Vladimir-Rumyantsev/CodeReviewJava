package src;

public class Human {
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

    private String getName() {
      return customName != null ? customName.toString() : stringName;
    }
  }

  private HumanName name;
  private int height;

  public Human(String name, int height) {
    this.name = new HumanName(name);
    this.height = height;
  }

  public Human(Name name, int height) {
    this.name = new HumanName(name);
    this.height = height;
  }

  public Human() {
    this.name = new HumanName("Неизвестное имя");
    this.height = 0;
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

  public String toString() {
    return (name.getName() + ", рост: " + height);
  }

  public void printInformation() {
    this.printInformation("\n");
  }

  public void printInformation(String end) {
    System.out.print("Человек с именем \"" + name.getName() + "\" и ростом " + height + end);
  }
}
