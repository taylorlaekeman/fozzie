package fozzie.commands;

public class ComwdgCommandBuilder extends CommandBuilder {

	private static ComwdgCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new ComwdgCommandBuilder();
		return instance;
	}

	private ComwdgCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format("AT*CALIB=%d<CR>", sequenceNumber);
	}
}
