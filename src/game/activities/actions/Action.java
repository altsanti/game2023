package game.activities.actions;

public abstract class Action {

	String message;

	Action(String message) {
		this.message = message;
	}

	protected abstract String buildCommand();

	public void executeCommand() {
		System.out.println(buildCommandWithOptionalMessage(buildCommand(), message));
	}

	private static String buildCommandWithOptionalMessage(String command, String message) {
		return message != null ? command + " " + message : command;
	}
}
