package com.alaindroid.toys.ipstat.services.stat;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class FileStatResultCollectorService implements StatResultCollectorService {
    public void result(String name, List<Map<String, String>> result) {
        System.out.println("Stat: " + name + "\n  Result:" + result);
        String fileName = "result-" + name + ".txt";
        Path path = Paths.get(fileName);
        String formattedResult = result.stream()
                .map(this::resultMap)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("EEMMPPTTYYYYY");
        byte[] strToBytes = formattedResult.getBytes();

        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed writing result for " + name + " to " + fileName);
        }
    }

    private String resultMap(Map<String, String> resultItem) {
        String str = resultItem.entrySet()
                .stream()
                .map(entrySet -> entrySet.getKey() + "=" + entrySet.getValue())
                .reduce((a, b) -> a + ", " + b)
                .orElse("NOOOONNNNNNNNEEEEE");

        System.out.println("Write this: " + str);
        return str;
    }
}
