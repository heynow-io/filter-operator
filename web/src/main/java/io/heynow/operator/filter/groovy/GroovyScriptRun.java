package io.heynow.operator.filter.groovy;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

@Component
public class GroovyScriptRun {

    @Autowired
    private GroovyScriptEvaluator scriptEvaluator;

    public boolean run(String script) {
        return (boolean) scriptEvaluator.evaluate(new StaticScriptSource("return 'abcd'==aaa"), ImmutableMap.of
                ("aaa", script));
    }
}
