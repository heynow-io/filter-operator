package io.heynow.operator.filter.groovy;

import io.heynow.operator.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.groovy.GroovyScriptFactory;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class GroovyScriptRun {

    private final GroovyScriptFactory groovyScriptFactory;

    @Autowired
    public GroovyScriptRun(GroovyScriptFactory groovyScriptFactory) {
        this.groovyScriptFactory = groovyScriptFactory;
    }

    public boolean run(String script, Map<String,Object> payload) {
        try {
            return Filter.class.cast(groovyScriptFactory.getScriptedObject(new StaticScriptSource(script, "Test") , Filter.class)).filter(payload);
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
