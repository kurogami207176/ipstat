package com.alaindroid.toys.ipstat.services.parser;

import com.alaindroid.toys.ipstat.services.parser.model.LogLine;

public interface LogParsingService {
    LogLine parse(String line);
}
