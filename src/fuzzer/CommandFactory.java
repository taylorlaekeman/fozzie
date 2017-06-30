package fozzie.fuzzer;

import java.util.Random;
import fozzie.fuzzer.commands.*;

public abstract class CommandFactory {
  private static final int NUM_COMMAND_TYPES = 8;
  private static final int REF = 0;
  private static final int PCMD = 1;
  private static final int PCMD_MAG = 2;
  private static final int FTRIM = 3;
  private static final int CONFIG = 4;
  private static final int CONFIG_IDS = 5;
  private static final int COMWDG = 6;
  private static final int CALIB = 7;

  public static Command makeRandomCommand(int sequenceNumber) {
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
