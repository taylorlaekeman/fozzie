package fozzie;

import fozzie.fuzzer.CommandFactory;

public class Fozzie {

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++)
      System.out.println(CommandFactory.makeRandomCommand(i));
  }
}
