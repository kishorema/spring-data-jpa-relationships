package com.example.springdatajparelationships.config;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration
{
    @Bean
    EnhancedRandom enhancedRandom()
    {
        return EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .seed(123L)
                .objectPoolSize(10)
                .stringLengthRange(8, 15)
                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .build();
    }
}
