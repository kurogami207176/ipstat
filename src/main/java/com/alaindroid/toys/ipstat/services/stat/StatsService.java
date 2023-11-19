package com.alaindroid.toys.ipstat.services.stat;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public interface StatsService {

    default Map<String, List<Map<String, String>>> runStats(String... names) {
        return Stream.of(names)
                .collect(Collectors.toMap(
                    Function.identity(),
                    this::runStat
                )
        );
    }
    List<Map<String, String>> runStat(String names);
}
