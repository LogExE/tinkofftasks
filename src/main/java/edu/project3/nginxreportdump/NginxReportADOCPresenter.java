package edu.project3.nginxreportdump;

import edu.project3.nginxlogstats.NginxLogReport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NginxReportADOCPresenter implements NginxReportPresenter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    @Override
    public String present(List<String> paths, LocalDate from, LocalDate to, NginxLogReport report) {
        StringBuilder sb = new StringBuilder();

        String title = "General info";
        String[] headers = {"Metrics", "Values"};
        String[][] rows = {
            {"Files", String.join(" ", paths)},
            {"Date from", from != LocalDate.MIN ? FORMATTER.format(from) : "-"},
            {"Date to", to != LocalDate.MAX ? FORMATTER.format(to) : "-"},
            {"Request count", String.valueOf(report.count())},
            {"Average response bytes", String.valueOf(report.avgBytesSent())}
        };
        sb.append(presentADOCTable(title, headers, rows));
        sb.append("\n");

        title = "Requested resources";
        headers = new String[] {"Resource", "Count"};
        rows = report.resourceFreq().stream()
            .map(rf -> new String[] {rf.resource(), String.valueOf(rf.frequency())})
            .toArray(sz -> new String[sz][1]);
        sb.append(presentADOCTable(title, headers, rows));
        sb.append("\n");

        title = "Responses";
        headers = new String[] {"Response", "Count"};
        rows = report.responseFreq().stream()
            .map(rf -> new String[] {String.valueOf(rf.response()), String.valueOf(rf.frequency())})
            .toArray(sz -> new String[sz][1]);
        sb.append(presentADOCTable(title, headers, rows));
        return sb.toString();
    }

    private String presentADOCTable(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();
        sb.append(".").append(title).append("\n");
        sb.append("[%autowidth]\n");
        sb.append("|===\n");
        for (String header : headers) {
            sb.append("|").append(header).append(" ");
        }
        sb.append("\n");
        for (String[] row : rows) {
            sb.append("\n");
            for (String data : row) {
                sb.append("|").append(data);
            }
        }
        sb.append("\n|===\n");
        return sb.toString();
    }
}
