package com.james.alten_shop.configs;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    /** Mapper bean. */
    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper result = new ModelMapper();

        result.getConfiguration().setAmbiguityIgnored(true);
        result.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        return result;
    }
}
