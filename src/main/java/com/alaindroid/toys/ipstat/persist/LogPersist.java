package com.alaindroid.toys.ipstat.persist;

import com.alaindroid.toys.ipstat.services.parser.model.LogLine;

import java.util.List;
import java.util.Map;

public interface LogPersist {
    void save(LogLine... records);

    List<Map<String, String>> query(String queryString);
}
