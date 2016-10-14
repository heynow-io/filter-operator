package io.heynow.operator.filter;

import io.heynow.groovy.EnableGroovyScriptRunner;
import io.heynow.stream.manager.client.EnableStreamManagerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;

@SpringBootApplication
@EnableGroovyScriptRunner
@EnableEurekaClient
@EnableStreamManagerClient
public class FilterOperatorApplication {

    @Bean
    public GroovyScriptEvaluator groovyScriptEvaluator() {
        return new GroovyScriptEvaluator();
    }

    public static void main(String[] args) {
        SpringApplication.run(FilterOperatorApplication.class, args);
    }
}
