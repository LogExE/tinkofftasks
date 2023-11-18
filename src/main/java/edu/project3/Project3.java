package edu.project3;

import edu.project3.nginxlogcliparser.LogInspectorCLIArgs;
import edu.project3.nginxlogcliparser.LogInspectorCLIArgsParser;
import edu.project3.nginxlogcliparser.LogInspectorOutputFormat;
import edu.project3.nginxlogparser.NginxLogParser;
import edu.project3.nginxlogparser.NginxLogRecord;
import edu.project3.nginxlogstats.NginxLogReport;
import edu.project3.nginxlogstats.NginxLogReporter;
import edu.project3.nginxreportdump.NginxReportADOCPresenter;
import edu.project3.nginxreportdump.NginxReportMDPresenter;
import edu.project3.nginxreportdump.NginxReportPresenter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.stream.Stream;

public class Project3 {
    private static final HttpClient HTTP_CL = HttpClient.newHttpClient();
    private static final int HTTP_TIMEOUT_SECS = 10;

    private Project3() {
    }

    @SuppressWarnings({"UncommentedMain", "RegexpSingleLineJava"})
    public static void main(String[] args) {
        Optional<LogInspectorCLIArgs> parsed = LogInspectorCLIArgsParser.parse(args);
        if (parsed.isEmpty()) {
            System.err.println("Failed to parse arguments!");
            return;
        }
        LogInspectorCLIArgs par = parsed.get();
        System.out.println("Parsed args: " + par);
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        OffsetDateTime fromOff = OffsetDateTime.of(par.from(), LocalTime.MIDNIGHT, offset);
        OffsetDateTime toOff = OffsetDateTime.of(par.to(), LocalTime.MIDNIGHT, offset);

        Stream<String> accum = Stream.empty();
        for (String path : par.paths()) {
            Optional<Stream<String>> lines = tryReadLogURL(path).or(() -> tryReadLogFile(path));
            if (lines.isEmpty()) {
                System.err.println("Failed to read path " + path + "!");
                continue;
            }
            accum = Stream.concat(accum, lines.get());
        }
        Stream<NginxLogRecord> records = NginxLogParser.parse(accum)
            .filter(rec -> rec.time().compareTo(fromOff) * toOff.compareTo(rec.time()) >= 0);
        NginxLogReport report = NginxLogReporter.makeReport(records);
        NginxReportPresenter presenter;
        if (par.format() == LogInspectorOutputFormat.MARKDOWN) {
            presenter = new NginxReportMDPresenter();
        } else {
            presenter = new NginxReportADOCPresenter();
        }
        System.out.println(presenter.present(par.paths(), par.from(), par.to(), report));
    }

    private static Optional<Stream<String>> tryReadLogURL(String url) {
        try {
            URI uri = new URI(url);
            HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .timeout(Duration.ofSeconds(HTTP_TIMEOUT_SECS))
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
