package com.xrc.mcs.configuration.mendeleev;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xrc.mcs.model.PeriodicTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PeriodicTableConfig {
    private final ObjectMapper objectMapper;

    @Bean
    public PeriodicTable periodicTable() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/data/periodic_table.json");
            if (inputStream == null) {
                log.error("Periodic table file not found");
                throw new FileNotFoundException("Periodic table file not found");
            }
            PeriodicTable periodicTable = objectMapper.readValue(inputStream, PeriodicTable.class);
            log.info("Periodic table: {} has been initialized successfully", periodicTable);
            return periodicTable;
        } catch (IOException e) {
            log.error("Failed to initialize periodic table", e);
            throw new RuntimeException(e);
        }
    }
}
