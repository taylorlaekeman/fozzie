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
		return String.format(
			"AT*PCMD_MAG=%d,%d,%f,%f,%f,%f,%f,%f<CR>",
			sequenceNumber,
			getInvalidFlag(),
			getInvalidRoll(),
			getInvalidPitch(),
			getInvalidGaz(),
			getInvalidYaw(),
			getInvalidPsi(),
			getInvalidPsiAccuracy()
		);
	}

	protected float getValidPsi() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getValidPsiAccuracy() {
		return (CommandFactory.RANDOM.nextFloat() * 2) - 1;
	}

	protected float getInvalidPsi() {
		float psi = (CommandFactory.RANDOM.nextFloat() + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			psi *= -1;
		return psi;
	}

	protected float getInvalidPsiAccuracy() {
		float psiAccuracy = (CommandFactory.RANDOM.nextFloat() + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			psiAccuracy *= -1;
		return psiAccuracy;
	}
}
