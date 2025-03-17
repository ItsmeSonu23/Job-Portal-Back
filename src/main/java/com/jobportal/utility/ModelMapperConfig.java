package com.jobportal.utility;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Base64;

import org.modelmapper.AbstractConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper setup and customization.
 * Provides a configured ModelMapper bean with custom type converters.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures a ModelMapper bean with custom type conversions.
     * Sets up strict matching strategy and adds converters for Base64 string 
     * to byte array conversions used in Profile/ProfileDto mappings.
     *
     * @return Configured ModelMapper instance with custom type converters
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Converter to map Base64 string to byte[] (for ProfileDto to Profile conversion)
        modelMapper.addConverter(new AbstractConverter<String, byte[]>() {
            protected byte[] convert(String source) {
                return source != null ? Base64.getDecoder().decode(source) : null;
            }
        });

        // Converter to map byte[] to Base64 string (for Profile to ProfileDto conversion) 
        modelMapper.addConverter(new AbstractConverter<byte[], String>() {
            protected String convert(byte[] source) {
                return source != null ? Base64.getEncoder().encodeToString(source) : null;
            }
        });

        return modelMapper;
    }
}
