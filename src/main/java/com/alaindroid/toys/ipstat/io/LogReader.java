package com.alaindroid.toys.ipstat.io;

import java.util.List;

public interface LogReader {
    List<String> read(String uri);
}
