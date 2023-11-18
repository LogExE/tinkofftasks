package edu.project3;

import edu.project3.cliparser.LogInspectorCLIArgs;
import edu.project3.cliparser.LogInspectorCLIArgsParser;
import edu.project3.nginxlogparser.NginxLogParser;
import edu.project3.nginxlogparser.NginxLogRecord;
import edu.project3.nginxlogstats.NginxLogReport;
import edu.project3.nginxlogstats.NginxLogReporter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Project3 {
    private static final HttpClient HTTP_CL = HttpClient.newHttpClient();

    private Project3() {
    }

    @SuppressWarnings({"UncommentedMain", "RegexpSingleLineJava"})
    public static void main(String[] args) {
        Optional<LogInspectorCLIArgs> parsed = LogInspectorCLIArgsParser.parse(args);
        if (parsed.isEmpty()) {
            System.out.println("Failed to parse arguments!");
            return;
        }
        LogInspectorCLIArgs par = parsed.get();
        System.out.println("Parsed args: " + par);
        for (String path : par.paths()) {
            System.out.println(path);
            Optional<Stream<String>> lines = tryReadLogURL(path).or(() -> tryReadLogFile(path));
            if (lines.isEmpty()) {
                System.out.println("Failed to read path " + path);
                continue;
            }
            Stream<NginxLogRecord> records = NginxLogParser.parse(lines.get());
            NginxLogReport report = NginxLogReporter.makeReport(records);
            System.out.println(report);
        }
    }

    private static Optional<Stream<String>> tryReadLogURL(String url) {
        try {
            URI uri = new URI(url);
            HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
            var resp = HTTP_CL.send(req, HttpResponse.BodyHandlers.ofLines());
            return Optional.of(resp.body());
        } catch (IllegalArgumentException | URISyntaxException | IOException | InterruptedException e) {
            return Optional.empty();
        }
    }

    private static Optional<Stream<String>> tryReadLogFile(String path) {
        try {
            return Optional.of(Files.lines(Paths.get(path)));
        } catch (InvalidPathException | IOException e) {
            return Optional.empty();
        }
    }
}
