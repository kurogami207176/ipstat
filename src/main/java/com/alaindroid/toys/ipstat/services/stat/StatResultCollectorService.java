package com.alaindroid.toys.ipstat.services.stat;

import java.util.List;
import java.util.Map;

public interface StatResultCollectorService {
    void result(String name, List<Map<String, String>> result);
}
