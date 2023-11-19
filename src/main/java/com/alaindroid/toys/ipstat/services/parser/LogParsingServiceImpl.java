package com.alaindroid.toys.ipstat.services.parser;

import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class LogParsingServiceImpl implements LogParsingService{

    @Value("${log.regex}")
    private String regex;
//    private static final String regexOriginal = "^([\\d.]+) - - \\[([\\w:/]+) ([-+\\d]+)\\] \"(\\w+) (/[^ ]*) HTTP/\\d.\\d\" (\\d{3}) (\\d+)";
//    private static final String regexNew = "^(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(.*?)\\] \"(GET|POST|PUT|DELETE|HEAD|PATCH) (http[s]?://[^\\s]+|/[^\\s]+) HTTP/1\\.1\" (\\d{3}) (\\d+) \"-\" \"(.*?)\"$";
//    private static final String regexX = "^(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(\\d{2}/[A-Za-z]{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d{4})\\] \"(GET|POST|PUT|DELETE|HEAD) (/[^ ]+) HTTP/1\\.1\" (\\d{3}) (\\d+) \"-\" \"(.*)\"$";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");;

    public LogParsingServiceImpl() {
    }

    public LogParsingServiceImpl(String regex) {
        this.regex = regex;
    }

    public LogLine parse(String line) {
        LogLine parsedLog = extract(line);
        return parsedLog;
    }

    private LogLine extract(String line) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String dateString = matcher.group(3);
            String zoneString = matcher.group(4);

            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            ZoneOffset zoneOffset = ZoneOffset.of(zoneString);
            ZonedDateTime zonedDateTime = dateTime.atOffset(zoneOffset).toZonedDateTime();

            return new LogLine(
                    matcher.group(1),
                    zonedDateTime,
                    matcher.group(5),
                    matcher.group(6),
                    Integer.parseInt(matcher.group(7)),
                    Long.parseLong(matcher.group(8)));

        } else {
            System.out.println("No match found.");
            throw new InvalidParameterException("Could not match: " + line);
        }
    }
}
