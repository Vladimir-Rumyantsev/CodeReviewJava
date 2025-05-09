package src;

/**
 * Представляет ФИО (Фамилию, Имя, Отчество) человека.
 * Поддерживает частичное заполнение данных (например, только имя).
 */
public class Name {
  private String lastName;
  private String firstName;
  private String patronymic;

  /**
   * Создаёт объект с указанными фамилией, именем и отчеством.
   * @param lastName Фамилия (может быть null).
   * @param firstName Имя (может быть null).
   * @param patronymic Отчество (может быть null).
   */
  public Name(String lastName, String firstName, String patronymic) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
  }

  public Name(String lastName, String firstName) {
    this(lastName, firstName, null);
  }

  public Name(String firstName) {
    this(null, firstName, null);
  }

  public Name() {
    this(null, null, null);
  }

  /**
   * Возвращает ФИО в формате "Фамилия Имя Отчество".
   * Если какая-то часть отсутствует (null), она не включается в строку.
   * @return Непустая строка с ФИО или пустая строка, если все части null.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (lastName != null) {
      sb.append(lastName).append(" ");
    }

    if (firstName != null) {
      sb.append(firstName).append(" ");
    }

    if (patronymic != null) {
      sb.append(patronymic).append(" ");
    }

    if (sb.isEmpty()) {
      return "";
    }

    return sb.deleteCharAt(sb.length() - 1).toString();
  }

  public void setLastName(String LastName) {
    this.lastName = LastName;
  }

  public void setFirstName(String FirstName) {
    this.firstName = FirstName;
  }

  public void setPatronymic(String Patronymic) {
    this.patronymic = Patronymic;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getPatronymic() {
    return patronymic;
  }
}
