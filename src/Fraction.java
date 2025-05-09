import java.util.Objects;

public final class Fraction extends Number {
  private int numerator;
  private int denominator;
  private Double cachedRealValue;

  public Fraction(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
    }

    int gcd = gcd(numerator, denominator);
    numerator /= gcd;
    denominator /= gcd;

    if (denominator < 0) {
      numerator *= -1;
      denominator *= -1;
    }

    this.numerator = numerator;
    this.denominator = denominator;
    this.cachedRealValue = null;
  }

  public Fraction(int numerator) {
    this(numerator, 1);
  }

  public void setNumerator(int numerator) {
    if (numerator != this.numerator) {
      int gcd = gcd(numerator, this.denominator);
      this.numerator = numerator / gcd;
      this.denominator /= gcd;
      this.cachedRealValue = null;
    }
  }

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

  // Метод для нахождения наибольшего общего делителя (GCD)
  private int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  public Fraction sum(Fraction other) {
    int newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
    int newDenominator = this.denominator * other.denominator;
    return new Fraction(newNumerator, newDenominator);
  }

  public Fraction sum(int number) {
    return sum(new Fraction(number));
  }

  public Fraction minus(Fraction other) {
    int newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
    int newDenominator = this.denominator * other.denominator;
    return new Fraction(newNumerator, newDenominator);
  }

  public Fraction minus(int number) {
    return minus(new Fraction(number));
  }

  public Fraction multiply(Fraction other) {
    int newNumerator = this.numerator * other.numerator;
    int newDenominator = this.denominator * other.denominator;
    return new Fraction(newNumerator, newDenominator);
  }

  public Fraction multiply(int number) {
    return multiply(new Fraction(number));
  }

  public Fraction div(Fraction other) {
    if (other.numerator == 0) {
      throw new IllegalArgumentException("Деление на ноль невозможно");
    }
    return multiply(new Fraction(other.denominator, other.numerator));
  }

  public Fraction div(int number) {
    if (number == 0) {
      throw new IllegalArgumentException("Деление на ноль невозможно");
    }
    return div(new Fraction(number));
  }

  // Реализация методов, наследуемых от класса Number
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
