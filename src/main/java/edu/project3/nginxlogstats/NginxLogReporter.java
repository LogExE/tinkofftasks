package edu.project3.nginxlogstats;

import edu.project3.nginxlogparser.NginxLogRecord;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class NginxLogReporter {
    private NginxLogReporter() {

    }

    private static final int MAX_RECORDS = 5;

    public static NginxLogReport makeReport(Stream<NginxLogRecord> records) {
        HashMap<String, Integer> resFreq = new HashMap<>();
        HashMap<Integer, Integer> respFreq = new HashMap<>();
        int count = 0;
        long total = 0;
        HashSet<String> addrs = new HashSet<>();
        HashMap<String, Integer> resSz = new HashMap<>();
        for (NginxLogRecord rec : records.toList()) {
            count++;
            total += rec.bodyBytesSent();
            resFreq.merge(rec.resource(), 1, Integer::sum);
            respFreq.merge(rec.status(), 1, Integer::sum);
            addrs.add(rec.remoteAddr());
            if (rec.requestMethod().equals("GET")) {
                resSz.put(rec.resource(), rec.bodyBytesSent());
            }
        }
        return new NginxLogReport(
            count,
            resFreq.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed())
                .limit(MAX_RECORDS)
                .map(e -> new ResourceFrequency(e.getKey(), e.getValue()))
                .toList(),
            respFreq.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry<Integer, Integer>::getValue).reversed())
                .limit(MAX_RECORDS)
                .map(e -> new ResponseFrequency(e.getKey(), e.getValue()))
                .toList(),
            count != 0 ? total / count : 0,
            addrs.size(),
            resSz.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed())
                .limit(MAX_RECORDS)
                .map(Map.Entry::getKey)
                .toList()
            );
    }
}
