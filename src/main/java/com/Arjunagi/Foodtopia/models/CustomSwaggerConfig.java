package com.Arjunagi.Foodtopia.models;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSwaggerConfig {
    @Bean
    public OpenApiCustomizer customOpenAPI() {
        return openAPI -> {
            Info info=new Info();
            info.setTitle("FoodTopia");
            info.setDescription("Foodtopia i.e. Ethiopia for the foodies is The Food delivery app which provide the wide range of restaurant and food items for the customers and platform for restaurant owners where they can register their restaurant and sell their food free of cost.");
            openAPI.info(info);
        };
    }


}

