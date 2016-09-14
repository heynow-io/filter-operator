package io.heynow.operator.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;

@SpringBootApplication
public class FilterOperatorApplication {

    @Bean
    public GroovyScriptEvaluator groovyScriptEvaluator() {
        return new GroovyScriptEvaluator();
    }

    public static void main(String[] args) {
        SpringApplication.run(FilterOperatorApplication.class, args);
    }
}
