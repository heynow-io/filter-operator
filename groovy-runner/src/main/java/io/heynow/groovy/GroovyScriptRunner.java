package io.heynow.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class GroovyScriptRunner {
    @Autowired
    private GroovyShell shell;

    public <T> T run(String scriptContent, Map<String, Object> params, Class<T> expectedType) {
        Script script = shell.parse(scriptContent);
        script.setBinding(new Binding(params));
        Object result = script.run();
        return castResult(expectedType, result);
    }

    private <T> T castResult(Class<T> expectedType, Object result) {
        if (result == null) {
            throw new IllegalArgumentException("Script didn't return an object");
        } else if (!expectedType.isInstance(result)) {
            throw new IllegalArgumentException("Script didn't create an object of the expected type. " + "expected "
                    + "type: " + expectedType.getName() + ", " + "actual type: " + result.getClass().getName() + ", "
                    + "actual value: " + result);
        }
        return expectedType.cast(result);
    }
}
