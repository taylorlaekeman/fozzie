package fozzie.commands;

import fozzie.CommandFactory;

public class RefCommandBuilder extends CommandBuilder {

	private static RefCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new RefCommandBuilder();
		return instance;
	}

	private RefCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*REF=%d,%d<CR>",
			sequenceNumber,
			getValidFlag()
		);
	}

	@Override
	protected String makeInvalid(int sequenceNumber) {
		return String.format(
			"AT*REF=%d,%d<CR>",
			sequenceNumber,
			getInvalidFlag()
		);
	}

	private int getValidFlag() {
		/*
		 *	Sending a ref command with the takeoffAndLandBit set to 1 indicates
		 *	that the drone should begin flying if it is not.
		 *	Sending a ref command with the takeoffAndLandBit set to 0 indicates
		 *	that the drone should begin landing if it is flying.
		 *	Sending a ref command with the emergencyBit set to 1 enables emergency
		 *	mode and cuts the engines.
		 *	Sending a ref command with the emergencyBit set to 0 disables emergency
		 *	mode.
		 */
		int takeoffAndLandBit = CommandFactory.RANDOM.nextInt(2);
		int emergencyBit = 0;
		return (1 << 28) + (1 << 24) + (1 << 22) + (1 << 20) + (1 << 18) + (takeoffAndLandBit << 9) + (emergencyBit << 8);
	}

	private int getInvalidFlag() {
		int flag = CommandFactory.RANDOM.nextInt(Integer.MAX_VALUE);
		flag = flag & (Integer.MAX_VALUE - (1 << 8)); // bitwise and with all 1's except in the emergency bit to ensure emergency bit = 0
		flag = flag | (1 << 9); // bitwise or with 1 in the takeoff and land bit so the drone doesn't try to land
		return flag;
	}
}
