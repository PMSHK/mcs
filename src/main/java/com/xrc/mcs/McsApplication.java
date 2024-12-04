package com.xrc.mcs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
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
