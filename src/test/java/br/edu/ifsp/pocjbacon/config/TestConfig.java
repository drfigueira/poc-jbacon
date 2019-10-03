package br.edu.ifsp.pocjbacon.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class TestConfig {

    @Bean
    public Faker faker() {
        long seed = System.currentTimeMillis();
        return new Faker(new Random(seed));
    }

}
