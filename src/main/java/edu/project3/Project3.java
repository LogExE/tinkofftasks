package edu.project3;

import edu.project3.cliparser.LogInspectorCLIArgs;
import edu.project3.cliparser.LogInspectorCLIArgsParser;
import java.util.Optional;

public class Project3 {
    private Project3() {
    }

    @SuppressWarnings({"UncommentedMain", "RegexpSingleLineJava"})
    public static void main(String[] args) {
        Optional<LogInspectorCLIArgs> parsed = LogInspectorCLIArgsParser.parse(args);
        if (parsed.isPresent()) {
            LogInspectorCLIArgs par = parsed.get();
            System.out.println("Parsed args: " + par);
        } else {
            System.out.println("Failed to parse arguments!");
        }
    }
}
