package com.xrc.mcs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
//@EnableFeignClients("com.xrc.mcs.client")
public class McsApplication {

    public static void main(String[] args) {
        SpringApplication.run(McsApplication.class, args);
    }

    @Bean
    public ObjectMapper mcsObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.activateDefaultTyping(
//                BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.PROPERTY
//        );

        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

}
