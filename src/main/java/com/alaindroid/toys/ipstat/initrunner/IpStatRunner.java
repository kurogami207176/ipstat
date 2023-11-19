package com.alaindroid.toys.ipstat.initrunner;

import com.alaindroid.toys.ipstat.io.LogReader;
import com.alaindroid.toys.ipstat.persist.LogPersist;
import com.alaindroid.toys.ipstat.services.PersistAndStatService;
import com.alaindroid.toys.ipstat.services.parser.LogParsingService;
import com.alaindroid.toys.ipstat.services.parser.model.LogLine;
import com.alaindroid.toys.ipstat.services.stat.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class IpStatRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PersistAndStatService persistAndStatService;

    @Value("${stats.run}")
    private String stats;

    @Value("${log.source}")
    private String logSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        persistAndStatService.execute(logSource, stats);
    }

}
