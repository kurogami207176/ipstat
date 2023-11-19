package com.alaindroid.toys.ipstat.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class LogFileReader implements LogReader {
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public List<String> read(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.readAllLines(path, Charset.forName("utf-8"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException("Failed to read file " + fileName);
        }
    }
}
