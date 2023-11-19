package com.alaindroid.toys.ipstat.services.parser;

import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LogParsingServiceImplTest {

    private LogParsingService sut = new LogParsingServiceImpl(
//            "^([\\d.]+) - - \\[([\\w:/]+) ([-+\\d]+)\\] \"(\\w+) (/[^ ]*) HTTP/\\d.\\d\" (\\d{3}) (\\d+)"
            "^(\\d+\\.\\d+\\.\\d+\\.\\d+) - (-|admin) \\[([\\w:/]+) ([-+\\d]+)\\] \\\"(GET|POST|PUT|DELETE|HEAD|PATCH) (http[s]?://[^\\s]+|/[^\\s]*) HTTP/1\\.1\\\" (\\d{3}) (\\d+) \\\"-\\\" \\\"(.*?)\\\""
    );
    @Test
    void testBase() {
        String input = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"";
        LogLine expected = new LogLine("177.71.128.21",
                ZonedDateTime.of(2018, 7, 10, 22, 21, 28, 0, ZoneOffset.of("+0200")),
                "GET",
                "/intranet-analytics/",
                200,
                3574L
        );

        LogLine logLine = sut.parse(input);
        assertThat(logLine).isEqualToComparingFieldByField(expected);
    }
}