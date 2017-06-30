package fozzie.fuzzer.commands;

public class RefCommand extends Command {

  int flag;

  public RefCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*REF";
  }

  @Override
  public void makeParameters() {
    flag = makeFlag();
  }

  public int makeFlag() {
    int takeOffAndLandBit = random.nextInt(2);
    int emergencyBit = random.nextInt(2);
    return (1 << 28) | (1 << 24) | (1 << 22) | (1 << 20) | (1 << 18) | (takeOffAndLandBit << 9) | (emergencyBit << 8);
  }

  @Override
  public String toString() {
    return String.format("%s=%d,%d<CR>", name, sequenceNumber, flag);
  }
}
