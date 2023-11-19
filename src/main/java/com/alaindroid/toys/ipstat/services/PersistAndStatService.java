package com.alaindroid.toys.ipstat.services;

import com.alaindroid.toys.ipstat.io.LogReader;
import com.alaindroid.toys.ipstat.persist.LogPersist;
import com.alaindroid.toys.ipstat.services.parser.LogParsingService;
import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
import com.alaindroid.toys.ipstat.services.stat.StatResultCollectorService;
import com.alaindroid.toys.ipstat.services.stat.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersistAndStatService {

    @Autowired
    private LogReader logReader;
    @Autowired
    private LogParsingService logParser;

    @Autowired
    private LogPersist logPersist;

    @Autowired
    private StatsService statsService;

    @Autowired
    private StatResultCollectorService collectorService;

    public Map<String, List<Map<String, String>>> execute(String logSource, String stats) {
        logPersist.save(
                logReader.read(logSource).stream()
                        .map(logParser::parse)
                        .toList()
                        .toArray(new LogLine[0])
        );
        String[] statsToRun = stats.split(",");
        Map<String, List<Map<String, String>>> result = statsService.runStats(statsToRun);
        result.forEach(collectorService::result);
        return result;
    }

}
