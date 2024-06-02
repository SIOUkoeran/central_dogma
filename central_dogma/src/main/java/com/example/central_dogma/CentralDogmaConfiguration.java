package com.example.central_dogma;

import com.linecorp.centraldogma.client.CentralDogma;
import com.linecorp.centraldogma.client.armeria.ArmeriaCentralDogmaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.net.UnknownHostException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CentralDogmaConfiguration {

    private final CentralDogmaProperties centralDogmaProperties;

    @Bean
    public FileReadPort FileReadPort(){
        return new FileReadCentralDogmaAdapter(
                centralDogma(),
                queryHolder()
        );
    }

    @Bean
    public QueryHolder queryHolder() {
        return new InMemoryQueryHolder(
                new ConcurrentReferenceHashMap<>()
        );
    }

    @Bean
    public CentralDogma centralDogma() {
        try {
            return new ArmeriaCentralDogmaBuilder()
                    .host(centralDogmaProperties.getCENTRAL_DOGMA_HOST())
                    .build();
        } catch (UnknownHostException e) {
            log.error("can't access central dogma : {}", e.getMessage());
            throw new RuntimeException();
        }
    }
}
