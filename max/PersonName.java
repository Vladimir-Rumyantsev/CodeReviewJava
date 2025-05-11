package max;

/**
 * Представляет ФИО (Фамилию, Имя, Отчество) человека.
 * Поддерживает частичное заполнение данных (например, только имя).
 * Класс неизменяемый (immutable).
 */
public final class PersonName {
  private final String name;
  private final String surname;
  private final String patronymic;

  /**
   * Создает объект с указанными фамилией, именем и отчеством.
   * @param surname Фамилия (может быть null)
   * @param name Имя (может быть null)
   * @param patronymic Отчество (может быть null)
   */
  public PersonName(String name, String surname, String patronymic) {
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
  }

  public PersonName(String name, String surname) {
    this(name, surname, null);
  }

  public PersonName(String name) {
    this(name, null, null);
  }

  public PersonName() {
    this(null, null, null);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    if (surname != null) {
      sb.append(surname).append(" ");
    }

    if (name != null) {
      sb.append(name).append(" ");
    }

    if (patronymic != null) {
      sb.append(patronymic).append(" ");
    }

    return sb.isEmpty() ? "" : sb.deleteCharAt(sb.length() - 1).toString();
  }
}
