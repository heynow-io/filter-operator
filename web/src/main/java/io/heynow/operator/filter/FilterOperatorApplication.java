package io.heynow.operator.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scripting.groovy.GroovyScriptFactory;

@SpringBootApplication
public class FilterOperatorApplication {

    @Bean
    public GroovyScriptFactory groovyScriptFactory() {
        return new GroovyScriptFactory("filter-script");
    }

    public static void main(String[] args) {
        SpringApplication.run(FilterOperatorApplication.class, args);
    }
}
