package fozzie.fuzzer.commands;

public class ConfigIdsCommand extends Command {

  int session, user, application;

  public ConfigIdsCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*CONFIG_IDS";
  }

  @Override
  public void makeParameters() {
    session = 0;
    user = 0;
    application = 0;
  }

  @Override
  public String toString() {
    return String.format("%s=%d,%d,%d,%d<CR>", name, sequenceNumber, session, user, application);
  }
}
