package core;

import com.google.gson.Gson;

import model.Command;

public class CommandParser {
	private Gson gson = new Gson();
	
	private CommandParser() {
		
	}
	
	private static class CommandParserWrapper {
		static CommandParser instance = new CommandParser();
	}
	
	
	public String toString(Command command) {
		return gson.toJson(command);
	}
	
	public Command toCommand(String command) {
		return gson.fromJson(command, Command.class);
	}
	
	public static CommandParser getInstance() {
		return CommandParserWrapper.instance;
	}
}
