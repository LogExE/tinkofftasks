package edu.project3;

import java.time.OffsetDateTime;

public record NginxLogRecord(String remoteAddr, String remoteUser,
                             OffsetDateTime time,
                             String request,
                             int status,
                             int bodyBytesSent,
                             String httpReferer,
                             String httpUsrAgent) {
}
