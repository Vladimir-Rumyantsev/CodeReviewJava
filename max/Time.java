package max;

/**
 * Класс для представления времени в формате часов, минут и секунд.
 * Поддерживает создание времени из общего количества секунд или отдельных компонентов.
 * Объекты класса неизменяемые (immutable).
 */
public final class Time {
  private final int hours;
  private final int minutes;
  private final int seconds;

  /**
   * Создает объект времени из общего количества секунд.
   * @param totalSeconds общее количество секунд (должно быть >= 0)
   * @throws IllegalArgumentException если totalSeconds < 0
   */
  public Time(int totalSeconds){
    if (totalSeconds < 0) {
      throw new IllegalArgumentException("Общее количество секунд не может быть отрицательным: " + totalSeconds);
    }
    this.seconds = totalSeconds % 60;
    int totalMinutes = (totalSeconds - this.seconds) / 60;
    this.minutes = totalMinutes % 60;
    int totalHours = (totalMinutes - this.minutes) / 60;
    this.hours = totalHours % 24;
  }

  /**
   * Создает объект времени из отдельных компонентов.
   * @param hours часы (0-23)
   * @param minutes минуты (0-59)
   * @param seconds секунды (0-59)
   * @throws IllegalArgumentException если компоненты выходят за допустимые границы
   */
  public Time(int hours, int minutes, int seconds) {
    if (hours < 0 || hours >= 24) {
      throw new IllegalArgumentException("Часы должны быть в диапазоне [0, 23]: " + hours);
    }
    if (minutes < 0 || minutes >= 60) {
      throw new IllegalArgumentException("Минуты должны быть в диапазоне [0, 59]: " + minutes);
    }
    if (seconds < 0 || seconds >= 60) {
      throw new IllegalArgumentException("Секунды должны быть в диапазоне [0, 59]: " + seconds);
    }
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  /**
   * Возвращает время в формате HH:MM:SS.
   */
  @Override
  public String toString() {
    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }

  public int getHours() { return hours; }
  public int getMinutes() { return minutes; }
  public int getSeconds() { return seconds; }
}
