import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileMerger {

	// TO BE CHANGED IF NEEDED
	private static final String PROJECT_PATH = "C:\\java\\perso\\UnleashTheGeek-2022";
	
	private static final Pattern GAME_IMPORT_PATTERN = Pattern.compile("import game.*");

	private static final Pattern IMPORT_PATTERN = Pattern.compile("import .*");

	private static final Pattern PACKAGE_PATTERN = Pattern.compile("package .*");

	private static final Pattern PUBLIC_CLASS_PATTERN = Pattern.compile("public (.*(class|enum) .*)");

	public static void main(String[] args) throws IOException {

		List<Path> matchingFilesPath = Files.walk(Paths.get(PROJECT_PATH))
				.filter(f -> isJavaFile(f.getFileName().toString())).collect(Collectors.toList());

		Set<String> importSet = new HashSet<>();
		StringBuilder contentBuilder = new StringBuilder();
		for (Path filePath : matchingFilesPath) {
			File file = new File(filePath.toString());
			System.out.println(file.getAbsolutePath());

			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);

				Matcher importMatcher = IMPORT_PATTERN.matcher(data);
				Matcher gameImportMatcher = GAME_IMPORT_PATTERN.matcher(data);
				Matcher packageMatcher = PACKAGE_PATTERN.matcher(data);
				Matcher publicClassMatcher = PUBLIC_CLASS_PATTERN.matcher(data);

				if (packageMatcher.matches() || gameImportMatcher.matches()) {
					// Do nothing
				} else if (importMatcher.matches()) {
					importSet.add(data);
				} else if (publicClassMatcher.matches()) {
					String classLineNoPublic = publicClassMatcher.group(1);
					contentBuilder.append(classLineNoPublic + "\n");
				} else {
					contentBuilder.append(data + "\n");
				}
			}
			myReader.close();
		}

		importSet.forEach(importValue -> contentBuilder.insert(0, importValue + "\n"));
		String content = contentBuilder.toString();
		
		StringSelection selection = new StringSelection(content);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		
		System.out.println("######## Code copied in clipboard #########");
	}

	private static boolean isJavaFile(String name) {
		return !name.equals("MergedJavaFiles.java") && !name.equals("FileMerger.java") && name.endsWith(".java");
	}
}
