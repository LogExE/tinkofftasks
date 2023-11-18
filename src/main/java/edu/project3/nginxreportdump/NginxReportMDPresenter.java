package edu.project3.nginxreportdump;

import edu.project3.nginxlogstats.NginxLogReport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NginxReportMDPresenter implements NginxReportPresenter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    @Override
    public String present(List<String> paths, LocalDate from, LocalDate to, NginxLogReport report) {
        StringBuilder sb = new StringBuilder();

        sb.append("#### General info\n");

        String[] headers = {"Metrics", "Values"};
        String[][] rows = {
            {"Files", String.join(" ", paths)},
            {"Date from", FORMATTER.format(from)},
            {"Date to", FORMATTER.format(to)},
            {"Request count", String.valueOf(report.count())},
            {"Average response bytes", String.valueOf(report.avgBytesSent())}
        };
        MDTableAlign[] aligns = {MDTableAlign.CENTER, MDTableAlign.RIGHT};
        sb.append(presentMDTable(headers, rows, aligns));

        return sb.toString();
    }

    private enum MDTableAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    private String presentMDTable(String[] headers, String[][] rows, MDTableAlign[] colAligns) {
        int[] maxLens = new int[headers.length];
        for (int i = 0; i < headers.length; ++i) {
            maxLens[i] = headers[i].length();
        }
        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < rows[0].length; ++j) {
                maxLens[j] = Math.max(maxLens[j], rows[i][j].length());
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

        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < rows[0].length; ++j) {
                String padded = String.format(" %" + maxLens[j] + "s ", rows[i][j]);
                sb.append("|").append(padded);
            }
            sb.append("|\n");
        }

        return sb.toString();
    }
}
