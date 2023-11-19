package com.alaindroid.toys.ipstat.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersistAndStatServiceTest {

    @Autowired
    private PersistAndStatService persistAndStatService;

    private String logSource = "logs.log";

    @Test
    void testUniqueIpsWithInternalLog() {
        Map<String, List<Map<String, String>>> result = persistAndStatService.execute(logSource,
                "unique_ips");
        assertThat(result.get("unique_ips"))
                .isNotNull();
        List<Map<String, String>> resultList = result.get("unique_ips");
        assertThat(resultList).hasSize(1);
        Map<String, String> resultItem = resultList.get(0);
        assertThat(resultItem.get("CNT"))
                .isEqualTo("7");
    }
    @Test
    void testTop3IpWithInternalLog() {
        Map<String, List<Map<String, String>>> result = persistAndStatService.execute(logSource,
                "top_3_ips");
        assertThat(result.get("top_3_ips"))
                .isNotNull();
        List<Map<String, String>> resultList = result.get("top_3_ips");
        assertThat(resultList)
                .hasSize(3);
        List<String> urlList = resultList.stream()
                .map(item -> item.get("IP"))
                .toList();
        assertThat(urlList).contains(
                "208.67.222.222",
                "193.45.67.89",
                "193.45.67.89"
        );
    }
    @Test
    void testTop3UrlWithInternalLog() {
        Map<String, List<Map<String, String>>> result = persistAndStatService.execute(logSource,
                "top_3_urls");
        assertThat(result.get("top_3_urls"))
                .isNotNull();
        List<Map<String, String>> resultList = result.get("top_3_urls");
        assertThat(resultList)
                .hasSize(3);
        List<String> urlList = resultList.stream()
                .map(item -> item.get("URL"))
                .toList();
        assertThat(urlList).contains(
                "/api/status-check/",
                "/home-page/",
                "/api/data-update/"
        );
    }
}