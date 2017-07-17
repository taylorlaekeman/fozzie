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
		REF, PCMD, PCMD_MAG, FTRIM, CONFIG, CONFIG_IDS, COMWDG, CALIB
	}

	public static String makeCommand() {
		return buildCommand(1, getRandomCommandType(), RANDOM.nextBoolean());
	}

	public static String makeCommand(int sequenceNumber) {
		return buildCommand(sequenceNumber, getRandomCommandType(), RANDOM.nextBoolean());
	}

	public static String makeCommand(CommandType type) {
		return buildCommand(1, type, RANDOM.nextBoolean());
	}

	public static String makeCommand(boolean valid) {
		return buildCommand(1, getRandomCommandType(), valid);
	}

	public static String makeCommand(int sequenceNumber, CommandType type) {
		return buildCommand(sequenceNumber, type, RANDOM.nextBoolean());
	}

	public static String makeCommand(int sequenceNumber, boolean valid) {
		return buildCommand(sequenceNumber, getRandomCommandType(), valid);
	}

	public static String makeCommand(CommandType type, boolean valid) {
		return buildCommand(1, type, valid);
	}

	public static String makeCommand(int sequenceNumber, CommandType type, boolean valid) {
		return buildCommand(sequenceNumber, type, valid);
	}

	private static String buildCommand(int sequenceNumber, CommandType type, boolean valid) {
		return CommandBuilder.getBuilder(type).build(sequenceNumber, valid);
	}

	private static CommandType getRandomCommandType() {
		return COMMAND_TYPES.get(RANDOM.nextInt(COMMAND_TYPES.size()));
	}
}
