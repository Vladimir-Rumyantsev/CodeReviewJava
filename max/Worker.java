package max;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет сотрудника компании, который может быть привязан к отделу.
 * Поддерживает двустороннюю связь с объектом {@link Department}.
 */
public class Worker {
  private String name;
  private Department department;

  /**
   * Создает сотрудника с указанным именем и отделом.
   * @param name Имя сотрудника (не может быть пустым или null)
   * @param department Отдел (может быть null)
   * @throws IllegalArgumentException если имя пустое или null
   */
  public Worker(String name, Department department) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Имя не может быть пустым");
    }
    this.name = name;
    if (department != null) {
      department.addWorker(this);
    }
    this.department = department;
  }

  /**
   * Создает сотрудника без привязки к отделу.
   * @param name Имя сотрудника (не может быть пустым или null)
   */
  public Worker(String name){
    this(name, null);
  }

  /**
   * Проверяет, является ли сотрудник руководителем отдела.
   * @return true если сотрудник - руководитель своего отдела
   */
  public boolean isDepartmentHead() {
    if (department == null) {
      return false;
    }
    return department.isDepartmentHead(this);
  }

  /**
   * Устанавливает новое имя сотрудника.
   * @throws IllegalArgumentException если имя пустое или null
   */
  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Имя не может быть пустым");
    }
    this.name = name;
  }

  /**
   * Привязывает сотрудника к новому отделу, автоматически обновляя
   * связи в старом и новом отделах.
   */
  public void setDepartment(Department department) {
    if (department == this.department) {
      return;
    }
    Department oldDepartment = this.department;
    this.department = department;
    if (oldDepartment != null) {
      oldDepartment.removeWorker(this);
    }
    if (department != null) {
      department.addWorker(this);
    }
  }

  public String getName() {
    return name;
  }

  public Department getDepartment() {
    return department;
  }

  /**
   * Возвращает неизменяемый список всех сотрудников отдела.
   * @return пустой список, если сотрудник не принадлежит отделу
   */
  public List<Worker> getAllDepartmentEmployees() {
    if (department == null) {
      return new ArrayList<>();
    }
    return department.getWorkers();
  }

  /**
   * Возвращает строку в зависимости от роли сотрудника в отделе.
   */
  @Override
  public String toString() {
    if (department == null) {
      return (name + " без отдела");
    }
    if (isDepartmentHead()) {
      return (name + " начальник отдела " + department.getDepartmentName());
    }
    return (
            name + " работает в отделе " + department.getDepartmentName() +
            ", начальник которого " + department.getDepartmentHead().getName()
    );
  }
}
