package io.heynow.operator.filter;

import com.google.common.collect.ImmutableMap;
import io.heynow.groovy.GroovyScriptRunner;
import io.heynow.stream.manager.client.EnableStreamManagerClient;
import io.heynow.stream.manager.client.facade.StreamManagerClient;
import io.heynow.stream.manager.client.model.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;

import java.util.Map;

@Slf4j
@EnableBinding(Sink.class)
@EnableStreamManagerClient
public class FilterOperator {


    private static final String INTERNAL = "internal";
    private static final String SCRIPT_KEY = "script";
    @Autowired
    private GroovyScriptRunner runner;
    @Autowired
    private StreamManagerClient streamManagerClient;

    @Filter(inputChannel = Sink.INPUT, outputChannel = INTERNAL)
    public boolean filter(Note note) {
        log.debug("filter " + note);
        String script = findScript(note);
        return runner.run(script, ImmutableMap.of("payload", note.getPayload()), Boolean.class);
    }

    @Router(inputChannel = INTERNAL)
    public String router(Note note) {
        log.debug("router " + note);
        return note.proceed().getName();
    }

    private String findScript(Note note) {
        Long id = note.getProcessingModel().getCurrent().getId();
        Map<String, Object> map = streamManagerClient.getProperties(id);
        return (String) map.getOrDefault(SCRIPT_KEY, null);
    }
}
