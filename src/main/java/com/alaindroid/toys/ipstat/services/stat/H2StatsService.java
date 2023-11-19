package com.alaindroid.toys.ipstat.services.stat;

import com.alaindroid.toys.ipstat.persist.H2LogPersist;
import com.alaindroid.toys.ipstat.persist.LogPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class H2StatsService implements StatsService {

    @Autowired
    private LogPersist logPersist;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List<Map<String, String>> runStat(String name) {
        String filename = "stats/" +  name + ".sql";
        Resource resource = applicationContext.getResource("classpath:" + filename);
        try {
            File file = resource.getFile();
            String queryString = Files.readString(Paths.get(file.getAbsolutePath()), Charset.forName("utf-8"));
            return logPersist.query(queryString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException("Failed to read SQL file " + filename);
        }
    }
}
