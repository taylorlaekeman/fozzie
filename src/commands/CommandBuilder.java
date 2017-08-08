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
				command += "AT*REF=1,290717696\rAT*PCMD=1,0,0,0,0,0\r";
			}
		}
		return command;
	}

	protected abstract String makeValid(int sequenceNumber);

	protected String makeInvalid(int sequenceNumber) {
		/* if no invalid commands exist, generate valid command */
		return makeValid(sequenceNumber);
	}

	protected String formatFloat(float f) {
		return formatInt(Float.floatToIntBits(f)); // integer representation of the bits in the float
	}

	protected String formatInt(int i) {
		return Integer.toString(i); // signed integer representation of integers
		//return Integer.toUnsignedString(i); // unsigned integer representation of integers
	}
}
