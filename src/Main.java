package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрационный класс для тестирования функционала:
 * - Работа с объектами Human, Name.
 * - Моделирование городов (City) и путей между ними (Path).
 * - Арифметические операции с дробями (Fraction).
 */
public class Main {
  public static void main(String[] args) {
    System.out.println("\nЗадание 1.2:");

    Human human1 = new Human("Клеопатра", 152);
    Human human2 = new Human("Пушкин", 167);
    Human human3 = new Human("Владимир", 189);

    human1.printInformation();
    human2.printInformation();
    human3.printInformation();

    Name name1 = new Name("Клеопатра");
    Name name2 = new Name("Пушкин", "Александр", "Сергеевич");
    Name name3 = new Name("Маяковский", "Владимир");

    System.out.println("\nЗадание 1.3:\n" + name1 + "\n" + name2 + "\n" + name3);

    human1.setName(name1);
    human2.setName(name2);
    human3.setName(name3);

    System.out.println("\nЗадание 2.2:");
    human1.printInformation();
    human2.printInformation();
    human3.printInformation();

    City A = new City("A");
    City B = new City("B");
    City C = new City("C");
    City D = new City("D");
    City E = new City("E");
    City F = new City("F");

    List<Path> pathsA = new ArrayList<>();
    pathsA.add(new Path(B, 5));
    pathsA.add(new Path(D, 6));
    pathsA.add(new Path(F, 1));
    A.setPath(pathsA);

    List<Path> pathsB = new ArrayList<>();
    pathsB.add(new Path(A, 5));
    pathsB.add(new Path(C, 3));
    B.setPath(pathsB);

    List<Path> pathsC = new ArrayList<>();
    pathsC.add(new Path(B, 3));
    pathsC.add(new Path(D, 4));
    C.setPath(pathsC);

    List<Path> pathsD = new ArrayList<>();
    pathsD.add(new Path(A, 6));
    pathsD.add(new Path(C, 4));
    pathsD.add(new Path(E, 2));
    D.setPath(pathsD);

    List<Path> pathsE = new ArrayList<>();
    pathsE.add(new Path(F, 2));
    E.setPath(pathsE);

    List<Path> pathsF = new ArrayList<>();
    pathsF.add(new Path(B, 1));
    pathsF.add(new Path(E, 2));
    F.setPath(pathsF);

    System.out.println(
      "\nЗадание 3.3:\n" + A + "\n" + B + "\n" + C + "\n" + D + "\n" + E + "\n" + F
    );

    City G = new City("G", pathsD);
    System.out.println("\nЗадание 4.8:\n" + G);

    Fraction f1 = new Fraction(3, 4);
    Fraction f2 = new Fraction(1, 2);
    Fraction f3 = new Fraction(2, 5);
    Fraction f4 = new Fraction(5, 15);
    Fraction f5 = f4.sum(f2);
    Fraction f6 = f2.sum(2);
    Fraction f7 = f3.minus(f1);
    Fraction f8 = f4.minus(1);
    Fraction f9 = f1.multiply(f4);
    Fraction f10 = f1.multiply(2);
    Fraction f11 = f2.div(f1);
    Fraction f12 = f2.div(2);
    Fraction f13 = f1.sum(f2).div(f3).minus(5);

    System.out.println(
      "\nЗадание 5.5:\nf1 = " + f1 + "\nf2 = " + f2 + "\nf3 = " + f3 + "\nf4 = " + f4 +
      "\n" + f4 + " + " + f2 + " = " + f5 +
      "\n" + f2 + " + " + 2 + " = " + f6 +
      "\n" + f3 + " - " + f1 + " = " + f7 +
      "\n" + f4 + " - " + 1 + " = " + f8 +
      "\n" + f1 + " * " + f4 + " = " + f9 +
      "\n" + f1 + " * " + 2 + " = " + f10 +
      "\n" + f2 + " / " + f1 + " = " + f11 +
      "\n" + f2 + " / " + 2 + " = " + f12 +
      "\nf1.sum(f2).div(f3).minus(5) = (" + f1 + " + " + f2 + ") / " + f3 + " - 5 = " + f13
    );

    try {
      System.out.print("\n0/4 + 2/0 = ");
      Fraction f14 = new Fraction(0, 4);
      Fraction f15 = new Fraction(2, 0);
      System.out.println(f14.sum(f15));
    } catch (IllegalArgumentException ex) {
      System.out.println("\n" + ex);
    } finally {
      System.out.println("\nПрограмма окончила работу!");
    }
  }
}
