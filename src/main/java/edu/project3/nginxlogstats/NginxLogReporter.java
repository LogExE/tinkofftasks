package edu.project3.nginxlogstats;

import edu.project3.nginxlogparser.NginxLogRecord;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NginxLogReporter {
    private NginxLogReporter() {

    }

    public static NginxLogReport makeReport(Stream<NginxLogRecord> records) {
        TreeMap<String, Integer> resFreq = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Integer> respFreq = new TreeMap<>(Comparator.reverseOrder());
        int count = 0;
        int total = 0;
        for (NginxLogRecord rec : records.toList()) {
            count++;
            total += rec.bodyBytesSent();
            resFreq.merge(rec.resource(), 1, Integer::sum);
            respFreq.merge(rec.status(), 1, Integer::sum);
        }
        return new NginxLogReport(
            count,
            resFreq.entrySet().stream()
                .limit(5)
                .collect(Collectors.toMap(
                    Map.Entry<String, Integer>::getKey,
                    Map.Entry::getValue,
                    (k1, k2) -> k1,
                    TreeMap::new
                )),
            respFreq.entrySet().stream()
                .limit(5)
                .collect(
                    Collectors.toMap(
                        Map.Entry<Integer, Integer>::getKey,
                        Map.Entry::getValue,
                        (k1, k2) -> k1,
                        TreeMap::new
                    )
                ),
            total / count
        );
    }
}
