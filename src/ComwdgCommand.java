public class ComwdgCommand extends Command {

  public ComwdgCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*COMWDG";
  }

  @Override
  public void makeParameters() {

  }

  @Override
  public String toString() {
    return String.format("%s=%d<CR>", name, sequenceNumber);
  }
}
