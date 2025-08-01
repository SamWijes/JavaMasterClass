package dev.multipletThreads;

public enum ThreadColour {
	ANSI_RESET("\u001B[0m"),
	ANSI_BLACK("\u001B[30m"),
	ANSI_WHITE("\u001b[37m"),
	ANSI_BLUE("\u001B[34m"),
	ANSI_CYAN("\u001B[36m"),
	ANSI_GREEN("\u001B[32m"),
	ANSI_PURPLE("\u001B[35m"),
	ANSI_RED("\u001B[31m"),
	ANSI_YELLOW("\u001b[33m");

	private final String colour;

	ThreadColour(String colour) {
		this.colour = colour;
	}

	public String colour(){
		return colour;
	}
}
