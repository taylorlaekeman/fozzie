import java.util.Random;

class Fuzzer {
  private static final int NUM_COMMANDS_INDEX = 0;

  public static void main(String[] args) {
    int commands = (args.length > 0) ? Integer.parseInt(args[NUM_COMMANDS_INDEX]) : 1;
    for (int i = 0; i < commands; i++)
      System.out.println(CommandFactory.makeRandomCommand(i));
  }
}

abstract class CommandFactory {
  private static final int NUM_COMMAND_TYPES = 8;
  private static final int REF = 0;
  private static final int PCMD = 1;
  private static final int PCMD_MAG = 2;
  private static final int FTRIM = 3;
  private static final int CONFIG = 4;
  private static final int CONFIG_IDS = 5;
  private static final int COMWDG = 6;
  private static final int CALIB = 7;

  static Command makeRandomCommand(int sequenceNumber) {
    int commandToMake = (new Random()).nextInt(NUM_COMMAND_TYPES);
    switch (commandToMake) {
      case REF :
        return new RefCommand(sequenceNumber);
      case PCMD :
        return new PcmdCommand(sequenceNumber);
      case PCMD_MAG :
        return new PcmdMagCommand(sequenceNumber);
      case FTRIM :
        return new FtrimCommand(sequenceNumber);
      case CONFIG :
        return new ConfigCommand(sequenceNumber);
      case CONFIG_IDS :
        return new ConfigIdsCommand(sequenceNumber);
      case COMWDG :
        return new ComwdgCommand(sequenceNumber);
      case CALIB :
        return new CalibCommand(sequenceNumber);
      default:
        return null;
    }
  }
}

abstract class Command {

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

class RefCommand extends Command {

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

class PcmdCommand extends Command {

  int flag;
  float roll, pitch, gaz, yaw;

  public PcmdCommand(int sequenceNumber) {
    super(sequenceNumber);
    this.name = "AT*PCMD";
  }

  @Override
  public void makeParameters() {
    flag = makeFlag();
    roll = (random.nextFloat() * 2) - 1;
    pitch = (random.nextFloat() * 2) - 1;
    gaz = (random.nextFloat() * 2) - 1;
    yaw = (random.nextFloat() * 2) - 1;
  }

  public int makeFlag() {
    int absoluteControlEnableBit = random.nextInt(2);
    int combinedYawEnableBit = random.nextInt(2);
    int progressiveCommandsEnableBit = random.nextInt(2);
    return (absoluteControlEnableBit << 2) | (combinedYawEnableBit << 1) | progressiveCommandsEnableBit;
  }

  @Override
  public String toString() {
    return String.format("%s=%d,%d,%f,%f,%f,%f<CR>", name, sequenceNumber, flag, roll, pitch, gaz, yaw);
  }
}

class PcmdMagCommand extends Command {

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

class FtrimCommand extends Command {

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

class ConfigCommand extends Command {

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

class ConfigIdsCommand extends Command {

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

class ComwdgCommand extends Command {

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

class CalibCommand extends Command {

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
