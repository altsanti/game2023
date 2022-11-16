package game.utils;

import java.util.ArrayList;
import java.util.List;

public class Log {
	public static void log(Object... args) {
		log(convertObjectToString(args));
	}

	public static void logLn(Object... args) {
		logLn(convertObjectToString(args));
	}

	public static void log(String... args) {
		System.err.println(String.join(" ", args));
		System.err.println("\n");
	}

	public static void logLn(String... args) {
		for (String arg : args) {
			System.err.println(arg);
		}
		System.err.println("\n");
	}

	private static String[] convertObjectToString(Object[] objects) {
		List<String> strings = new ArrayList<>();

		for (Object obj : objects) {
			strings.add(obj.toString());
		}
		return strings.toArray(new String[0]);
	}
}
