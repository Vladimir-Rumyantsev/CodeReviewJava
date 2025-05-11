package src;

import java.util.Objects;

/**
 * Представляет неизменяемую дробь (numerator/denominator) с автоматическим сокращением.
 * Реализует арифметические операции (+, -, *, /) и методы конвертации в числа.
 */
public class Fraction extends Number {
  private int numerator;
  private int denominator;
  private Double cachedRealValue;

  /**
   * Создаёт дробь с указанными числителем и знаменателем.
   * @param numerator Числитель.
   * @param denominator Знаменатель (не может быть 0).
   * @throws IllegalArgumentException если denominator == 0.
   */
  public Fraction(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
    }

    if (denominator < 0) {
      numerator *= -1;
      denominator *= -1;
    }

    int gcd = gcd(numerator, denominator);
    this.numerator = numerator / gcd;
    this.denominator = denominator / gcd;
    this.cachedRealValue = null;
  }

  /**
   * Создаёт дробь из целого числа, обращаясь к предыдущему конструктору.
   */
  public Fraction(int numerator) {
    this(numerator, 1);
  }

  /**
   * Обновляет числитель, сокращая дробь при возможности, и обнуляет кэш.
   * @param numerator Числитель.
   */
  public void setNumerator(int numerator) {
    if (numerator != this.numerator) {
      int gcd = gcd(numerator, this.denominator);
      this.numerator = numerator / gcd;
      this.denominator /= gcd;
      this.cachedRealValue = null;
    }
  }

  /**
   * Обновляет знаменатель, сокращая дробь при возможности, и обнуляет кэш.
   * @param denominator Знаменатель (не может быть 0).
   * @throws IllegalArgumentException если denominator == 0.
   */
  public void setDenominator(int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
    }
    if (denominator != this.denominator) {
      if (denominator < 0) {
        this.numerator *= -1;
        denominator *= -1;
      }

      int gcd = gcd(this.numerator, denominator);
      this.numerator /= gcd;
      this.denominator = denominator / gcd;
      this.cachedRealValue = null;
    }
  }

  /**
   * Вычисляет наибольший общий делитель (НОД) двух целых чисел, используя алгоритм Евклида.
   * Возвращает всегда положительное значение, даже если один или оба аргумента отрицательные.
   * <p>
   * Если один из аргументов равен нулю, метод вернёт абсолютное значение другого аргумента.
   * Например:
   * <ul>
   *   <li>{@code gcd(0, 5) → 5}</li>
   *   <li>{@code gcd(-12, 0) → 12}</li>
   *   <li>{@code gcd(0, 0) → 0} (особый случай, пойман исключением в конструкторе и сетторе)</li>
   * </ul>
   *
   * @param a первое целое число (может быть нулём)
   * @param b второе целое число (может быть нулём)
   * @return положительное значение НОД для заданных чисел
   * @see <a href="https://ru.wikipedia.org/wiki/Алгоритм_Евклида">Алгоритм Евклида</a>
   */
  private int gcd(int a, int b) {
    int gcd = (b == 0) ? a : gcd(b, a % b);
    return (gcd > 0) ? gcd : -gcd;
  }

  /**
   * Складывает текущую дробь с другой дробью.
   * @param other Дробь для сложения (не может быть null).
   * @return Новая дробь — результат сложения.
   * @throws NullPointerException если other == null.
   */
  public Fraction sum(Fraction other) {
    if (other == null) {
      throw new NullPointerException("Дробь для сложения не может быть null");
    }
    int newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
    int newDenominator = this.denominator * other.denominator;
    return new Fraction(newNumerator, newDenominator);
  }

  /**
   * Складывает текущую дробь с числом, создавая из числа вторую дробь.
   * @param number Число для сложения.
   * @return Новая дробь — результат сложения.
   */
  public Fraction sum(int number) {
    return sum(new Fraction(number));
  }

  /**
   * Вычитает из текущей дроби другую дробь.
   * @param other Дробь — вычитаемое (не может быть null).
   * @return Новая дробь — результат вычитания.
   * @throws NullPointerException если other == null.
   */
  public Fraction minus(Fraction other) {
    if (other == null) {
      throw new NullPointerException("Вычитаемая дробь не может быть null");
    }
    int newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
    int newDenominator = this.denominator * other.denominator;
    return new Fraction(newNumerator, newDenominator);
  }

  /**
   * Вычитает из текущей дроби число, создавая из числа вторую дробь.
   * @param number Число для вычитания.
   * @return Новая дробь — результат вычитания.
   */
  public Fraction minus(int number) {
    return minus(new Fraction(number));
  }

  /**
   * Умножает текущую дробь с другой дробью.
   * @param other Дробь для умножения (не может быть null).
   * @return Новая дробь — результат умножения.
   * @throws NullPointerException если other == null.
   */
  public Fraction multiply(Fraction other) {
    if (other == null) {
      throw new NullPointerException("Дробь для умножения не может быть null");
    }
    int newNumerator = this.numerator * other.numerator;
    int newDenominator = this.denominator * other.denominator;
    return new Fraction(newNumerator, newDenominator);
  }

  /**
   * Умножает текущую дробь с числом, создавая из числа вторую дробь.
   * @param number Число для умножения.
   * @return Новая дробь — результат умножения.
   */
  public Fraction multiply(int number) {
    return multiply(new Fraction(number));
  }

  /**
   * Делит текущую дроби на другую дробь.
   * @param other Дробь-делитель (не может быть null).
   * @return Новая дробь — результат деления.
   * @throws NullPointerException если other == null.
   * @throws IllegalArgumentException если дробь-делитель == 0.
   */
  public Fraction div(Fraction other) {
    if (other == null) {
      throw new NullPointerException("Дробь-делитель не может быть null");
    }
    if (other.numerator == 0) {
      throw new IllegalArgumentException("Деление на ноль невозможно");
    }
    return multiply(new Fraction(other.denominator, other.numerator));
  }

  /**
   * Делит текущую дробь на число, создавая из числа вторую дробь.
   * @param number Число для деления.
   * @return Новая дробь — результат вычитания.
   * @throws IllegalArgumentException если делитель == 0.
   */
  public Fraction div(int number) {
    if (number == 0) {
      throw new IllegalArgumentException("Деление на ноль невозможно");
    }
    return div(new Fraction(number));
  }

  /**
   * Реализация методов, наследуемых от класса Number.
   */
  @Override
  public int intValue() {
    return numerator / denominator;
  }

  @Override
  public long longValue() {
    return (long) numerator / denominator;
  }

  @Override
  public float floatValue() {
    return (float) numerator / denominator;
  }

  @Override
  public double doubleValue() {
    if (cachedRealValue == null) {
      cachedRealValue = (double) numerator / denominator;
    }
    return cachedRealValue;
  }

  @Override
  public byte byteValue() {
    return (byte) (numerator / denominator);
  }

  @Override
  public short shortValue() {
    return (short) (numerator / denominator);
  }

  /**
   * Реализация методов, наследуемых от класса Object.
   */
  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  @Override
  public Fraction clone() {
    return new Fraction(this.numerator, this.denominator);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Fraction other = (Fraction) obj;
    return (numerator == other.numerator) && (denominator == other.denominator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }
}
