import java.util.Random;

public abstract class Command {

  String name;
  int sequenceNumber;
  Random random;

  public Command(int sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    this.random = new Random();
    this.makeParameters();
  }

  abstract void makeParameters();
}
