package fozzie;

import fozzie.commands.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class CommandFactory {
	public static final Random RANDOM = new Random();
	private static final List<CommandType> COMMAND_TYPES = Collections.unmodifiableList(Arrays.asList(CommandType.values()));

	public static enum CommandType {
		REF, PCMD, PCMD_MAG/*, FTRIM, CONFIG, CONFIG_IDS, COMWDG, CALIB*/
	}

	public static String makeCommand(int sequenceNumber, CommandType type, boolean safeMode) {
		if (type == null)
			type = getRandomCommandType();
		boolean valid = RANDOM.nextBoolean();
		return buildCommand(sequenceNumber, type, valid, safeMode);
	}

	private static String buildCommand(int sequenceNumber, CommandType type, boolean valid, boolean safeMode) {
		return CommandBuilder.getBuilder(type).build(sequenceNumber, valid, safeMode);
	}

	private static CommandType getRandomCommandType() {
		return COMMAND_TYPES.get(RANDOM.nextInt(COMMAND_TYPES.size()));
	}
}
