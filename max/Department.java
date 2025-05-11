package max;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет отдел компании с сотрудниками и руководителем.
 * Обеспечивает двустороннюю синхронизацию с объектами {@link Worker}.
 */
public class Department {

  private String departmentName;
  private Worker departmentHead;
  private List<Worker> workers;

  /**
   * Создает отдел с указанным названием, руководителем и списком сотрудников.
   * @param departmentName Название отдела (не пустое)
   * @param departmentHead Руководитель отдела (не null)
   * @param workers Список сотрудников (может быть null)
   * @throws IllegalArgumentException если параметры невалидны
   */
  public Department(String departmentName, Worker departmentHead, List<Worker> workers) {
    if (departmentName == null || departmentName.isBlank()) {
      throw new IllegalArgumentException("Название отдела не может быть пустым");
    }
    if (departmentHead == null) {
      throw new IllegalArgumentException("Начальник отдела не может быть null");
    }
    this.departmentName = departmentName;
    this.departmentHead = departmentHead;
    setWorkers(workers, departmentHead);
  }

  /**
   * Создает отдел с указанным названием и руководителем.
   * @param departmentName Название отдела (не пустое)
   * @param departmentHead Руководитель отдела (не null)
   */
  public Department(String departmentName, Worker departmentHead) {
    this(departmentName, departmentHead, null);
  }

  /**
   * Обновляет список сотрудников отдела.
   * @param newWorkers Новый список сотрудников (null трактуется как пустой список)
   */
  private void setWorkers(List<Worker> newWorkers, Worker departmentHead) {
    if (this.workers != null) {
      List<Worker> oldWorkers = new ArrayList<>(this.workers);
      for (Worker worker : oldWorkers) {
        worker.setDepartment(null);
      }
      this.workers.clear();
    } else {
      this.workers = new ArrayList<>();
    }
    if (newWorkers != null) {
      if (newWorkers.contains(null)) {
        throw new IllegalArgumentException("Список работников не может содержать null");
      }
      for (Worker worker : newWorkers) {
        worker.setDepartment(this);
      }
    }
    if (!this.workers.contains(departmentHead)) {
      setDepartmentHead(departmentHead);
    }
  }

  public void setWorkers(List<Worker> workers) {
    setWorkers(workers, departmentHead);
  }

  /**
   * Устанавливает новое название отдела.
   * @param departmentName Новое название (не null, не пустое)
   * @throws IllegalArgumentException если departmentName == null или пустое
   */
  public void setDepartmentName(String departmentName){
    if (departmentName == null || departmentName.isBlank()) {
      throw new IllegalArgumentException("Название отдела не может быть пустым");
    }
    this.departmentName = departmentName;
  }

  /**
   * Устанавливает нового руководителя отдела.
   * @param departmentHead Новый руководитель (не null)
   * @throws IllegalArgumentException если newHead == null
   */
  public void setDepartmentHead(Worker departmentHead){
    if (departmentHead == null) {
      throw new IllegalArgumentException("Начальник отдела не может быть null");
    }
    departmentHead.setDepartment(this);
    this.departmentHead = departmentHead;
  }

  public String getDepartmentName(){
    return departmentName;
  }

  public Worker getDepartmentHead(){
    return departmentHead;
  }

  public List<Worker> getWorkers() {
    return new ArrayList<>(workers);
  }

  /**
   * Добавляет сотрудника в отдел.
   * @param worker Сотрудник для добавления (не null)
   */
  public void addWorker(Worker worker) {
    if (worker == null) {
      throw new IllegalArgumentException("Сотрудник не может быть null");
    }
    if (!workers.contains(worker)) {
      workers.add(worker);
    }
  }

  /**
   * Удаляет сотрудника из отдела.
   * @param worker Сотрудник для удаления (не null)
   * @throws IllegalStateException если пытаются удалить руководителя или worker == null
   */
  public void removeWorker(Worker worker) {
    if (worker == null) {
      throw new IllegalArgumentException("Сотрудник не может быть null");
    }
    if (worker == departmentHead) {
      throw new IllegalArgumentException("Нельзя увольнять главу отдела");
    }
    if (workers.remove(worker)) {
      worker.setDepartment(null);
    }
  }

  public boolean isDepartmentHead(Worker worker) {
    return (worker == departmentHead);
  }

  @Override
  public String toString() {
    return ("Отдел \"" + departmentName + "\", начальник которого \"" + departmentHead.getName() + '"');
  }
}
