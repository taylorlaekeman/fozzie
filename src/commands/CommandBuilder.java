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
      /*case FTRIM :
        return FtrimCommandBuilder.getInstance();
      case CONFIG :
        return ConfigCommandBuilder.getInstance();
      case CONFIG_IDS :
        return ConfigIdsCommandBuilder.getInstance();
      case COMWDG :
        return ComwdgCommandBuilder.getInstance();
      case CALIB :
        return CalibCommandBuilder.getInstance();*/
      default :
        return null;
		}
	}

	public String build(int sequenceNumber, boolean valid, boolean safeMode) {
		String command;
		if (valid)
			command = makeValid(sequenceNumber);
		else {
			command = makeInvalid(sequenceNumber);
			if (safeMode) {
				command += "\\rAT*REF=1,290717696<CR>\\rAT*PCMD=1,0,0,0,0,0<CR>";
			}
		}
		return command;
	}

	protected abstract String makeValid(int sequenceNumber);

	protected String makeInvalid(int sequenceNumber) {
		/* if no invalid commands exist, generate valid command */
		return makeValid(sequenceNumber);
	}
}
