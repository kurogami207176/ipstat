package com.alaindroid.toys.ipstat.services.parser;

import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LogParsingServiceImplTest {

    private LogParsingService sut = new LogParsingServiceImpl();
    @Test
    void testBase() {
        String input = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574";
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