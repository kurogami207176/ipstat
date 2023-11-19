package com.alaindroid.toys.ipstat.services.stat;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysOutStatResultCollectorService implements StatResultCollectorService {
    public void result(String name, List<Map<String, String>> result) {
        System.out.println("Stat: " + name + "\n  Result:" + result);
    }
}
