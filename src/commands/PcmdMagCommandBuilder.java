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

	@Override
	protected String makeInvalid(int sequenceNumber) {
		// all values should not always be invalid
		return String.format(
			"AT*PCMD_MAG=%d,%d,%f,%f,%f,%f,%f,%f<CR>",
			sequenceNumber,
			CommandFactory.RANDOM.nextBoolean() ? getValidFlag() : getInvalidFlag(),
			CommandFactory.RANDOM.nextBoolean() ? getValidRoll() : getInvalidRoll(),
			CommandFactory.RANDOM.nextBoolean() ? getValidPitch() : getInvalidPitch(),
			CommandFactory.RANDOM.nextBoolean() ? getValidGaz() : getInvalidGaz(),
			CommandFactory.RANDOM.nextBoolean() ? getValidYaw() : getInvalidYaw(),
			CommandFactory.RANDOM.nextBoolean() ? getValidPsi() : getInvalidPsi(),
			CommandFactory.RANDOM.nextBoolean() ? getValidPsiAccuracy() : getInvalidPsiAccuracy()
		);
	}

	protected float getValidPsi() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getValidPsiAccuracy() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getInvalidPsi() {
		return makeBadFloat();
	}

	protected float getInvalidPsiAccuracy() {
		return makeBadFloat();
	}
}
