package com.xrc.mcs.docs;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

import java.util.List;

@Configuration

public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MCS medicine radiation calculator")
                        .version("1.0")
                        .description("API documentation for MCS"))
                .tags(List.of(
                        new Tag().name("rad_type").description("Определение типа радиационного оборудования"),
                        new Tag().name("protection").description("Информация о материалах и защитных средствах"),
                        new Tag().name("periodic_table").description("Информация о материалах из периодической таблицы"),
                        new Tag().name("materials_manager").description("Управление материалами"),
                        new Tag().name("protection_calculator").description("Калькулятор основных параметров")
                ));
    }
}
