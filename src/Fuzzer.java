import java.util.Random;

class Fuzzer {
  private static final int NUM_COMMANDS_INDEX = 0;

  public static void main(String[] args) {
    int commands = (args.length > 0) ? Integer.parseInt(args[NUM_COMMANDS_INDEX]) : 1;
    for (int i = 0; i < commands; i++)
      System.out.println(CommandFactory.makeRandomCommand(i));
  }
}
