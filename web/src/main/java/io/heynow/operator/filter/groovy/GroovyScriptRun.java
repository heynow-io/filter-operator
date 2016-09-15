package io.heynow.operator.filter.groovy;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GroovyScriptRun {

    @Autowired
    private GroovyScriptEvaluator scriptEvaluator;

    public boolean run(Map<String,Object> payload) {

        return (boolean) scriptEvaluator.evaluate(new StaticScriptSource("return 'abcd'==aaa"), ImmutableMap.of
                ("aaa", payload));
    }
}
