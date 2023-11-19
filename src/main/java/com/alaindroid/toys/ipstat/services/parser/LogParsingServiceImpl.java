package com.alaindroid.toys.ipstat.services.parser;

import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
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

    private static final String regex = "^([\\d.]+) - - \\[([\\w:/]+) ([-+\\d]+)\\] \"(\\w+) (/[^ ]*) HTTP/\\d.\\d\" (\\d{3}) (\\d+)";

    private static final Pattern pattern = Pattern.compile(regex);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");

    public LogLine parse(String line) {
        System.out.println("Parsing: " + line);
        Matcher matcher = pattern.matcher(line);
        if ( !matcher.find()) {
            System.out.println("Could not parse: " + line);
        }
//        logMatch(matcher);
        String ip = matcher.group(1);

        String dateString = matcher.group(2);
        String zoneString = matcher.group(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        ZoneOffset zoneOffset = ZoneOffset.of(zoneString);
        ZonedDateTime logDate = dateTime.atOffset(zoneOffset).toZonedDateTime();

        LogLine parsedLog = new LogLine(
                ip,
                logDate,
                matcher.group(4),
                matcher.group(5),
                Integer.parseInt(matcher.group(6)),
                Long.parseLong(matcher.group(7))
        );

        System.out.println("Parsed log: " + parsedLog);

        return parsedLog;
    }

    private static void logMatch(Matcher matcher) {
        if (matcher.find()) {
            System.out.println("IP Address: " + matcher.group(1));

            String dateString = matcher.group(2);
            String zoneString = matcher.group(3);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
            ZoneOffset zoneOffset = ZoneOffset.of(zoneString);
            ZonedDateTime zonedDateTime = dateTime.atOffset(zoneOffset).toZonedDateTime();

            System.out.println("Timestamp (ZonedDateTime): " + zonedDateTime);
            System.out.println("HTTP Method: " + matcher.group(4));
            System.out.println("Endpoint: " + matcher.group(5));
            System.out.println("Status Code: " + matcher.group(6));
            System.out.println("Response Size: " + matcher.group(7));

            new LogLine(
                    matcher.group(1),
                    zonedDateTime,
                    matcher.group(4),
                    matcher.group(5),
                    Integer.parseInt(matcher.group(6)),
                    Long.parseLong(matcher.group(7)));

        } else {
            System.out.println("No match found.");
            throw new InvalidParameterException("Could not match");
        }
    }
}
