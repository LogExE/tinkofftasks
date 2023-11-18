package edu.project3.nginxlogstats;

import java.util.SortedMap;

public record NginxLogReport(int reqCount,
                             SortedMap<String, Integer> resourceFreq,
                             SortedMap<Integer, Integer> responseFreq,
                             int avgBytesSent) {
}
