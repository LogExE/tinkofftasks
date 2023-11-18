package edu.project3.nginxlogstats;

import java.util.List;

public record NginxLogReport(
    int count,
    List<ResourceFrequency> resourceFreq,
    List<ResponseFrequency> responseFreq,
    long avgBytesSent
) {
}
