package fozzie;

import fozzie.CommandFactory;

public class Fozzie {

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++)
      System.out.println(i + " " + CommandFactory.makeCommand());
  }
}
