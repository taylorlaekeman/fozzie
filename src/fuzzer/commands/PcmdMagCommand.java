package fozzie.fuzzer.commands;

public class PcmdMagCommand extends Command {

  int flag;
  float roll, pitch, gaz, yaw, psi, psiAccuracy;

  public PcmdMagCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*PCMD_MAG";
  }

  @Override
  public void makeParameters() {
    flag = makeFlag();
    roll = (random.nextFloat() * 2) - 1;
    pitch = (random.nextFloat() * 2) - 1;
    gaz = (random.nextFloat() * 2) - 1;
    yaw = (random.nextFloat() * 2) - 1;
    psi = (random.nextFloat() * 2) - 1;
    psiAccuracy = (random.nextFloat() * 2) - 1;
  }

  public int makeFlag() {
    int absoluteControlEnableBit = random.nextInt(2);
    int combinedYawEnableBit = random.nextInt(2);
    int progressiveCommandsEnableBit = random.nextInt(2);
    return (absoluteControlEnableBit << 2) | (combinedYawEnableBit << 1) | progressiveCommandsEnableBit;
  }

  @Override
  public String toString() {
    return String.format("%s=%d,%d,%f,%f,%f,%f,%f,%f<CR>", name, sequenceNumber, flag, roll, pitch, gaz, yaw, psi, psiAccuracy);
  }
}
