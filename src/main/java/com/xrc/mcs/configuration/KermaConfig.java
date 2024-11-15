package com.xrc.mcs.configuration;

import com.xrc.mcs.model.Kerma;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KermaConfig {
    @Bean
    public Kerma kerma() {
        return new Kerma();
    }
}
