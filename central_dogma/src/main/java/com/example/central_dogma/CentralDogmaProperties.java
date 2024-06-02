package com.example.central_dogma;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class CentralDogmaProperties {

    @Value("${centraldogma.host}")
    private String CENTRAL_DOGMA_HOST;

    @Value("${centraldogma.port}")
    private Integer CENTRAL_DOGMA_PORT;
}
