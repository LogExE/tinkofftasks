package edu.project3.cliparser;

import java.time.LocalDate;
import java.util.List;

public record LogInspectorCLIArgs(List<String> paths,
                                  LocalDate from,
                                  LocalDate to,
                                  LogInspectorOutputFormat format) {
}
