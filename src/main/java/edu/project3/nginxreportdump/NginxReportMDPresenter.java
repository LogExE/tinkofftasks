package edu.project3.nginxreportdump;

import edu.project3.nginxlogstats.NginxLogReport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NginxReportMDPresenter implements NginxReportPresenter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @SuppressWarnings("MultipleStringLiterals")
    @Override
    public String present(List<String> paths, LocalDate from, LocalDate to, NginxLogReport report) {
        StringBuilder sb = new StringBuilder();

        sb.append("#### General info\n\n");
        String[] headers = {"Metrics", "Values"};
        String[][] rows = {
            {"Files", enquote(paths)},
            {"Date from", from != LocalDate.MIN ? FORMATTER.format(from) : "-"},
            {"Date to", to != LocalDate.MAX ? FORMATTER.format(to) : "-"},
            {"Request count", String.valueOf(report.count())},
            {"Average response bytes", report.avgBytesSent() + "b"},
            {"Unique visitors", String.valueOf(report.uniqueRemoteAddrs())}
        };
        MDTableAlign[] aligns = {MDTableAlign.CENTER, MDTableAlign.RIGHT};
        sb.append(presentMDTable(headers, rows, aligns));
        sb.append("\n");

        sb.append("#### Requested resources\n\n");
        headers = new String[] {"Resource", "Count"};
        rows = report.resourceFreq().stream()
            .map(rf -> new String[] {enquote(rf.resource()), String.valueOf(rf.frequency())})
            .toArray(sz -> new String[sz][1]);
        aligns = new MDTableAlign[] {MDTableAlign.CENTER, MDTableAlign.RIGHT};
        sb.append(presentMDTable(headers, rows, aligns));
        sb.append("\n");

        sb.append("#### Responses\n\n");
        headers = new String[] {"Response", "Count"};
        rows = report.responseFreq().stream()
            .map(rf -> new String[] {String.valueOf(rf.response()), String.valueOf(rf.frequency())})
            .toArray(sz -> new String[sz][1]);
        aligns = new MDTableAlign[] {MDTableAlign.CENTER, MDTableAlign.RIGHT};
        sb.append(presentMDTable(headers, rows, aligns));
        sb.append("\n");

        sb.append("#### Largest resources sent through GET\n\n");
        headers = new String[] {"Resource"};
        rows = report.largestResourcesGET().stream()
            .map(res -> new String[] {res})
            .toArray(sz -> new String[sz][1]);
        aligns = new MDTableAlign[] {MDTableAlign.CENTER};
        sb.append(presentMDTable(headers, rows, aligns));
        sb.append("\n");

        return sb.toString();
    }

    private static String enquote(Collection<String> arr) {
        return arr.stream().collect(Collectors.joining("`, `", "`", "`"));
    }

    private static String enquote(String str) {
        return "`" + str + "`";
    }

    private enum MDTableAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    @SuppressWarnings("MultipleStringLiterals")
    private String presentMDTable(String[] headers, String[][] rows, MDTableAlign[] colAligns) {
        int[] maxLens = new int[headers.length];
        for (int i = 0; i < headers.length; ++i) {
            maxLens[i] = headers[i].length();
        }
        for (String[] row : rows) {
            for (int j = 0; j < rows[0].length; ++j) {
                maxLens[j] = Math.max(maxLens[j], row[j].length());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < headers.length; ++i) {
            String padded = String.format(" %" + maxLens[i] + "s ", headers[i]);
            sb.append("|").append(padded);
        }
        sb.append("|\n");

        for (int i = 0; i < headers.length; ++i) {
            String leftPart = colAligns[i] != MDTableAlign.RIGHT ? ":" : "-";
            String rightPart = colAligns[i] != MDTableAlign.LEFT ? ":" : "-";
            sb.append("|").append(leftPart).append("-".repeat(maxLens[i])).append(rightPart);
        }
        sb.append("|\n");

        for (String[] row : rows) {
            for (int j = 0; j < rows[0].length; ++j) {
                String padded = String.format(" %" + maxLens[j] + "s ", row[j]);
                sb.append("|").append(padded);
            }
            sb.append("|\n");
        }

        return sb.toString();
    }
}
