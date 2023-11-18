package edu.project3.nginxreportdump;

import edu.project3.nginxlogstats.NginxLogReport;
import java.time.LocalDate;
import java.util.List;

public class NginxReportADOCPresenter implements NginxReportPresenter {
    @Override
    public String present(List<String> paths, LocalDate from, LocalDate to, NginxLogReport report) {
        return null;
    }
}
