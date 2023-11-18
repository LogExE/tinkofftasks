package edu.project3.nginxreportdump;

import edu.project3.nginxlogstats.NginxLogReport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NginxReportMDPresenter implements NginxReportPresenter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;
    @Override
    public String present(List<String> paths, LocalDate from, LocalDate to, NginxLogReport report) {
        StringBuilder sb = new StringBuilder();
        sb.append("#### Общая информация");
        sb.append(report);
        return sb.toString();
    }
}
