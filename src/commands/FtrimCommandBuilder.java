package fozzie.commands;

public class FtrimCommandBuilder extends CommandBuilder {

	private static FtrimCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new FtrimCommandBuilder();
		return instance;
	}

	private FtrimCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format("AT*FTRIM=%d<CR>", sequenceNumber);
	}
}
