package edu.project3.nginxlogparser;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class NginxLogParser {
    private static final Pattern LOG_PAT = Pattern.compile(
        "(?<remoteAddr>.*)\\s*-\\s*(?<remoteUser>.*)\\s*-\\s*\\[(?<timeLocal>.*)]\\s*\"(?<requestMethod>.*) (?<resource>.*) (?<protoVer>.*)\"\\s*(?<status>\\d*)\\s*(?<bytesSent>\\d*)\\s*\"(?<httpReferer>.*)\"\\s*\"(?<httpAgent>.*)\""
    );

    private static final DateTimeFormatter OFF_DT_FMT = DateTimeFormatter.ofPattern(
        "d/MMM/y:H:m:s Z"
    );

    private NginxLogParser() {

    }

    public static Stream<NginxLogRecord> parse(Stream<String> logLines) {
        return logLines.map(NginxLogParser::strToLogRecord);
    }

    private static NginxLogRecord strToLogRecord(String str) {
        Matcher matcher = LOG_PAT.matcher(str);
        if (!matcher.find()) {
            return null;
        }
        String remoteAddr = matcher.group("remoteAddr");
        String remoteUser = matcher.group("remoteUser");
        OffsetDateTime time = OffsetDateTime.parse(matcher.group("timeLocal"), OFF_DT_FMT);
        String requestMethod = matcher.group("requestMethod");
        String resource = matcher.group("resource");
        String protoVer = matcher.group("protoVer");
        int status = Integer.parseInt(matcher.group("status"));
        int bodyBytes = Integer.parseInt(matcher.group("bytesSent"));
        String httpReferer = matcher.group("httpReferer");
        String httpAgent = matcher.group("httpAgent");
        return new NginxLogRecord(
            remoteAddr,
            remoteUser,
            time,
            requestMethod,
            resource,
            protoVer,
            status,
            bodyBytes,
            httpReferer,
            httpAgent
        );
    }
}
