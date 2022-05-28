package It.polimi.ingsw.View.Cli;

/**
 * The enum of the Colors.
 */
public enum StrColor {

	//COLORS

	ANSI_RESET("\u001B[0m"),
	ANSI_GREY("\033[0;90m"),
	ANSI_BLACK("\u001B[30m"),
	ANSI_RED("\033[0;91m"),
	ANSI_GREEN("\033[0;92m"),
	ANSI_YELLOW("\033[0;93m"),
	ANSI_BLUE("\033[0;94m"),
	ANSI_PURPLE("\033[0;95m"),
	ANSI_CYAN("\u001B[36m"),
	ANSI_WHITE("\u001B[37m"),


	//BACKGROUNDS

	ANSI_BLACK_BACKGROUND("\u001B[40m"),
	ANSI_RED_BACKGROUND("\u001B[41m"),
	ANSI_GREEN_BACKGROUND("\u001B[42m"),
	ANSI_YELLOW_BACKGROUND("\u001B[43m"),
	ANSI_BLUE_BACKGROUND("\u001B[44m"),
	ANSI_PURPLE_BACKGROUND("\u001B[45m"),
	ANSI_CYAN_BACKGROUND("\u001B[46m"),
	ANSI_WHITE_BACKGROUND("\u001B[47m");

	private final String ColorCode;

	/**
	 * The constructor of the class.
	 * @param ColorCode: the color code.
	 */
	StrColor(String ColorCode) {
		this.ColorCode = ColorCode;
	}

	/**
	 * Return the name of this enum constant.
	 * @return the name of this enum constant.
	 */
	@Override
	public String toString() {
		return ColorCode;
	}
}
