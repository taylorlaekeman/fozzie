package fozzie.commands;

import fozzie.CommandFactory;

public class PcmdCommandBuilder extends CommandBuilder {

	private static PcmdCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new PcmdCommandBuilder();
		return instance;
	}

	protected static float makeBadFloat() {
		if (CommandFactory.RANDOM.nextBoolean())
			return makeOutOfRangeFloat();
		else
			return makeReservedBadFloat();
	}

	protected static float makeOutOfRangeFloat() {
		float f = ((CommandFactory.RANDOM.nextFloat() * (Float.MAX_VALUE - 1)) + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			f *= -1;
		return f;
	}

	protected static float makeReservedBadFloat() {
		switch (CommandFactory.RANDOM.nextInt(3)) {
			case 0 :
				return Float.POSITIVE_INFINITY;
			case 1 :
				return Float.NEGATIVE_INFINITY;
			case 2 :
				return Float.NaN;
			default :
				return 0;
		}
	}

	protected PcmdCommandBuilder() {/* prevent instantiation, protected for PcmdMagCommandBuilder */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*PCMD=%d,%s,%s,%s,%s,%s\r",
			sequenceNumber,
			getValidFlag(),
			getValidRoll(),
			getValidPitch(),
			getValidGaz(),
			getValidYaw()
		);
	}

	@Override
	protected String makeInvalid(int sequenceNumber) {
		// all values should not always be invalid
		return String.format(
			"AT*PCMD=%d,%s,%s,%s,%s,%s\r",
			sequenceNumber,
			CommandFactory.RANDOM.nextBoolean() ? getValidFlag() : getInvalidFlag(),
			CommandFactory.RANDOM.nextBoolean() ? getValidRoll() : getInvalidRoll(),
			CommandFactory.RANDOM.nextBoolean() ? getValidPitch() : getInvalidPitch(),
			CommandFactory.RANDOM.nextBoolean() ? getValidGaz() : getInvalidGaz(),
			CommandFactory.RANDOM.nextBoolean() ? getValidYaw() : getInvalidYaw()
		);
	}

	protected String getValidFlag() {
    int absoluteControlEnableBit = CommandFactory.RANDOM.nextInt(2); 
    int combinedYawEnableBit = CommandFactory.RANDOM.nextInt(2);
    int progressiveCommandsEnableBit = 1;
    int flag = (absoluteControlEnableBit << 2) | (combinedYawEnableBit << 1) | progressiveCommandsEnableBit;
		return formatInt(flag);
	}

	protected String getValidRoll() {
		return formatFloat((CommandFactory.RANDOM.nextFloat() * 2) - 1);
	}

	protected String getValidPitch() {
		return formatFloat((CommandFactory.RANDOM.nextFloat() * 2) - 1);
	}

	protected String getValidGaz() {
		return formatFloat((CommandFactory.RANDOM.nextFloat() * 2) - 1);
	}

	protected String getValidYaw() {
		return formatFloat((CommandFactory.RANDOM.nextFloat() * 2) - 1);
	}

	protected String getInvalidFlag() {
		int flag = CommandFactory.RANDOM.nextInt(); // nextInt with no parameters generates a random number between 0 and 2^32 - 1
		flag = flag | 1; // progressive commands enable bit = 1
		return formatInt(flag);
	}

	protected String getInvalidRoll() {
		return formatFloat(makeBadFloat());
	}

	protected String getInvalidPitch() {
		return formatFloat(makeBadFloat());
	}

	protected String getInvalidGaz() {
		return formatFloat(makeBadFloat());
	}

	protected String getInvalidYaw() {
		return formatFloat(makeBadFloat());
	}
}
