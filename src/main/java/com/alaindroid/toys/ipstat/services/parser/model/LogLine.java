package com.alaindroid.toys.ipstat.services.parser.model;

import java.time.ZonedDateTime;

public record LogLine (
        String ip,
        ZonedDateTime date,
        String httpOperation,
        String url,
        Integer statusCode,
        Long bytes
)
{}
