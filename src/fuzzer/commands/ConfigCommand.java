package fozzie.fuzzer.commands;

public class ConfigCommand extends Command {

  String key, value;

  public ConfigCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*CONFIG";
  }

  @Override
  public void makeParameters() {
    key = "test";
    value = "test";
  }

  @Override
  public String toString() {
    return String.format("%s=%d,\"%s\",\"%s\"<CR>", name, sequenceNumber, key, value);
  }
}
