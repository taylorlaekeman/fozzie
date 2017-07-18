package fozzie.commands;

import fozzie.CommandFactory;

public class PcmdCommandBuilder extends CommandBuilder {

	private static PcmdCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new PcmdCommandBuilder();
		return instance;
	}

	protected PcmdCommandBuilder() {/* prevent instantiation, protected for PcmdMagCommandBuilder */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*PCMD=%d,%d,%f,%f,%f,%f<CR>",
			sequenceNumber,
			getValidFlag(),
			getValidRoll(),
			getValidPitch(),
			getValidGaz(),
			getValidYaw()
		);
	}

	protected int getValidFlag() {
    int absoluteControlEnableBit = CommandFactory.RANDOM.nextInt(2);
    int combinedYawEnableBit = CommandFactory.RANDOM.nextInt(2);
    int progressiveCommandsEnableBit = CommandFactory.RANDOM.nextInt(2);
    return (absoluteControlEnableBit << 2) | (combinedYawEnableBit << 1) | progressiveCommandsEnableBit;
	}

	protected float getValidRoll() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getValidPitch() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getValidGaz() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getValidYaw() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}
}
