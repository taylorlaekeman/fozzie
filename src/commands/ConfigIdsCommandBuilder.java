package fozzie.commands;

public class ConfigIdsCommandBuilder extends CommandBuilder {

	private static ConfigIdsCommandBuilder instance;

	public static CommandBuilder getInstance() {
		if (instance == null)
			instance = new ConfigIdsCommandBuilder();
		return instance;
	}

	private ConfigIdsCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*CONFIG_IDS=%d,%d,%d,%d<CR>",
			sequenceNumber,
			getValidSession(),
			getValidUser(),
			getValidApplication()
		);
	}

	private int getValidSession() {
		return 0;
	}

	private int getValidUser() {
		return 0;
	}

	private int getValidApplication() {
		return 0;
	}
}
