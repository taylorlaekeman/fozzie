public class CalibCommand extends Command {

  int deviceNumber;

  public CalibCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*CALIB";
  }

  @Override
  public void makeParameters() {
    deviceNumber = 0;
  }

  @Override
  public String toString() {
    return String.format("%s=%d,%d<CR>", name, sequenceNumber, deviceNumber);
  }
}
