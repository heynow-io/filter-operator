package io.heynow.operator.filter;

import io.heynow.operator.filter.groovy.GroovyScriptRun;
import io.heynow.stream.manager.client.model.Note;
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
    public boolean filter(Note note) {
        log.debug("filter " + note);
        //note.getProcessingModel().getCurrent().getId()
        return runner.run("", note.getPayload());
    }

    @Router(inputChannel = INTERNAL)
    public String router(Note note) {
        log.debug("router " + note);
        return note.proceed().getName();
    }
}
