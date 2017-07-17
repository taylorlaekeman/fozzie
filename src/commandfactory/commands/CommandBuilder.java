package fozzie.commands;

import fozzie.CommandFactory.CommandType;
import java.util.Random;

public abstract class CommandBuilder {

	public static CommandBuilder getBuilder(CommandType type) {
		switch (type) {
      case REF :
        return RefCommandBuilder.getInstance();
      case PCMD :
        return PcmdCommandBuilder.getInstance();
      case PCMD_MAG :
        return PcmdMagCommandBuilder.getInstance();
      case FTRIM :
        return FtrimCommandBuilder.getInstance();
      case CONFIG :
        return ConfigCommandBuilder.getInstance();
      case CONFIG_IDS :
        return ConfigIdsCommandBuilder.getInstance();
      case COMWDG :
        return ComwdgCommandBuilder.getInstance();
      case CALIB :
        return CalibCommandBuilder.getInstance();
      default :
        return null;
		}
	}

	public String build(int sequenceNumber, boolean valid) {
		return (valid) ? makeValid(sequenceNumber) : makeInvalid(sequenceNumber);
	}

	protected abstract String makeValid(int sequenceNumber);
	protected abstract String makeInvalid(int sequenceNumber);
}
