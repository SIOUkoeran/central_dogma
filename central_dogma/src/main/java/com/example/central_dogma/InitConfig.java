package com.example.central_dogma;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final FileReadPort fileReadPort;

    @Bean
    public CommandLineRunner initCacheByCentralDogma() {
        return args -> {
            fileReadPort.initFile("demo", "demo", "/demo.json");
        };
    }

    @Bean
    public CommandLineRunner listenCentralDogma() {
        return args -> fileReadPort.watchFile("demo", "demo", "/demo.json");
    }
}
