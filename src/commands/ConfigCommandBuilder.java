package fozzie.commands;

public class ConfigCommandBuilder extends CommandBuilder {

	private static ConfigCommandBuilder instance;

	public static ConfigCommandBuilder getInstance() {
		if (instance == null)
			instance = new ConfigCommandBuilder();
		return instance;
	}

	private ConfigCommandBuilder() {/* prevent instantiation */}

	@Override
	protected String makeValid(int sequenceNumber) {
		return String.format(
			"AT*CONFIG=%d,\"%s\",\"%s\"<CR>",
			sequenceNumber,
			getValidSettingName(),
			getValidSettingValue()
		);
	}

	private String getValidSettingName() {
		return "temp valid setting name";
	}

	private String getValidSettingValue() {
		return "temp valid setting value";
	}
}
