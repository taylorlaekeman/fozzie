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
			"AT*PCMD_MAG=%d,%s,%s,%s,%s,%s,%s,%s\r",
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
			"AT*PCMD_MAG=%d,%s,%s,%s,%s,%s,%s,%s\r",
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

	protected String getValidPsi() {
		return formatFloat((CommandFactory.RANDOM.nextFloat() * 2) - 1);
	}

	protected String getValidPsiAccuracy() {
		return formatFloat((CommandFactory.RANDOM.nextFloat() * 2) - 1);
	}

	protected String getInvalidPsi() {
		return formatFloat(makeBadFloat());
	}

	protected String getInvalidPsiAccuracy() {
		return formatFloat(makeBadFloat());
	}
}
