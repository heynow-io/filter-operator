package io.heynow.operator.filter;

import io.heynow.operator.filter.groovy.GroovyScriptRun;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;

@Slf4j
@EnableBinding(Sink.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilterOperator {


    private static final String INTERNAL = "internal";

    private Sink channels;
    private GroovyScriptRun runner;

    @Filter(inputChannel = Sink.INPUT, outputChannel = INTERNAL)
    public boolean filter(String message) {
        log.info("filter " + message);
        return runner.run(message);
    }

    @Router(inputChannel = INTERNAL)
    public String router(String message) {
        log.info("router " + message);
        return "ticktock2";
    }
}
