public class FtrimCommand extends Command {

  public FtrimCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*FTRIM";
  }

  @Override
  public void makeParameters() {/* do nothing */}

  @Override
  public String toString() {
    return String.format("%s=%d<CR>", name, sequenceNumber);
  }
}

