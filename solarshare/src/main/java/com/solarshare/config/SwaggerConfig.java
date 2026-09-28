package com.solarshare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI solarShareOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("SolarShare API")
                        .version("1.0")
                        .description(
                                "Community Rooftop Solar Usage Tracker API. "
                                        + "Manages households, daily solar generation, "
                                        + "household consumption and net energy usage."));
    }
}