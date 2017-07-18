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

	@Override
	protected String makeInvalid(int sequenceNumber) {
		return String.format(
			"AT*PCMD=%d,%d,%f,%f,%f,%f<CR>",
			sequenceNumber,
			getInvalidFlag(),
			getInvalidRoll(),
			getInvalidPitch(),
			getInvalidGaz(),
			getInvalidYaw()
		);
	}

	protected int getValidFlag() {
    int absoluteControlEnableBit = CommandFactory.RANDOM.nextInt(2); 
    int combinedYawEnableBit = CommandFactory.RANDOM.nextInt(2);
    int progressiveCommandsEnableBit = 1;
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

	protected int getInvalidFlag() {
		int flag = CommandFactory.RANDOM.nextInt(Integer.MAX_VALUE);
		flag = flag | 1; // progressive commands enable bit = 1
		return flag;
	}

	protected float getInvalidRoll() {
		float roll = (CommandFactory.RANDOM.nextFloat() + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			roll *= -1;
		return roll;
	}

	protected float getInvalidPitch() {
		float pitch = (CommandFactory.RANDOM.nextFloat() + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			pitch *= -1;
		return pitch;
	}

	protected float getInvalidGaz() {
		float gaz = (CommandFactory.RANDOM.nextFloat() + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			gaz *= -1;
		return gaz;
	}

	protected float getInvalidYaw() {
		float yaw = (CommandFactory.RANDOM.nextFloat() + 1);
		if (CommandFactory.RANDOM.nextBoolean())
			yaw *= -1;
		return yaw;
	}
}
