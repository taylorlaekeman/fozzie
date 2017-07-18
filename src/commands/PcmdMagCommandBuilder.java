package fozzie.commands;

import fozzie.CommandFactory;

public class PcmdMagCommandBuilder extends PcmdCommandBuilder {

	private static PcmdMagCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new PcmdMagCommandBuilder();
		return instance;
	}

	private PcmdMagCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*PCMD_MAG=%d,%d,%f,%f,%f,%f,%f,%f<CR>",
			sequenceNumber,
			getValidFlag(),
			getValidRoll(),
			getValidPitch(),
			getValidGaz(),
			getValidYaw(),
			getValidPsi(),
			getValidPsiAccuracy()
		);
	}

	protected float getValidPsi() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getValidPsiAccuracy() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}
}
