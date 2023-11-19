package com.alaindroid.toys.ipstat.persist;

import com.alaindroid.toys.ipstat.persist.model.LogLineRecord;
import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class H2LogPersist implements LogPersist {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(LogLine... records) {
        Stream.of(records)
                .map(this::map)
                .forEach(logsRepository::save);
    }

    @Override
    public List<Map<String, String>> query(String queryString) {
        return jdbcTemplate.query(queryString,
                (rs, rowNumber) -> resultSetToString(rs) );
    }

    private LogLineRecord map(LogLine logLine) {
        LogLineRecord logLineRecord = new LogLineRecord();
        logLineRecord.setIp(logLine.ip());
        logLineRecord.setDate(logLine.date());
        logLineRecord.setEndPoint(logLine.url());
        logLineRecord.setHttpOperation(logLine.httpOperation());
        logLineRecord.setStatusCode(logLine.statusCode());
        logLineRecord.setBytes(logLine.bytes());
        return logLineRecord;
    }

    public static Map<String, String> resultSetToString(ResultSet rs) throws SQLException {
        if (rs == null) {
            return Collections.EMPTY_MAP;
        }

        Map<String, String> result = new HashMap<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            result.put(metaData.getColumnName(i), rs.getString(i));
        }
        return result;
    }
}
