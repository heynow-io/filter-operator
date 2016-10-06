package io.heynow.operator.filter;

import io.heynow.groovy.EnableGroovyScriptRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;

@SpringBootApplication
@EnableGroovyScriptRunner
@EnableFeignClients
public class FilterOperatorApplication {

    @Bean
    public GroovyScriptEvaluator groovyScriptEvaluator() {
        return new GroovyScriptEvaluator();
    }

    public static void main(String[] args) {
        SpringApplication.run(FilterOperatorApplication.class, args);
    }
}
