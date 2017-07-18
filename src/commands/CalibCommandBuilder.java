package fozzie.commands;

public class CalibCommandBuilder extends CommandBuilder {

	private static CalibCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new CalibCommandBuilder();
		return instance;
	}

	private CalibCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*CALIB=%d,%d<CR>",
			sequenceNumber,
			getValidDeviceNumber()
		);
	}

	/* Device number is retrievable using ardrone_calibration_device_t, cannot be randomly generated */

	private static int getValidDeviceNumber() {
		return 0;
	}
}
