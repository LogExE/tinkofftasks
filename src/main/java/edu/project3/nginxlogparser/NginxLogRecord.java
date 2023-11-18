package edu.project3.nginxlogparser;

import java.time.OffsetDateTime;

public record NginxLogRecord(String remoteAddr, String remoteUser,
                             OffsetDateTime time,
                             String requestMethod,
                             String resource,
                             String protoVersion,
                             int status,
                             int bodyBytesSent,
                             String httpReferer,
                             String httpUsrAgent) {
}
