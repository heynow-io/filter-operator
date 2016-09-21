package io.heynow.groovy;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {GroovyConfiguration.class})
public class GroovyScriptRunnerTest {

    @Autowired
    private GroovyScriptRunner groovyScriptRunner;

    @Test
    public void run_executesSimpleScript() {
        String scriptContent = "1+1";

        Integer result = groovyScriptRunner.run(scriptContent, emptyMap(), Integer.class);

        assertThat(result).isEqualTo(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void run_throwsExceptionOnUnExpectedResultType() {
        String scriptContent = "1+1";

        String result = groovyScriptRunner.run(scriptContent, emptyMap(), String.class);

    }

    @Test(expected = IllegalArgumentException.class)
    public void run_throwsExceptionWhenNoResult() {

        groovyScriptRunner.run("", emptyMap(), String.class);

    }

    @Test
    public void run_appliesParameterToScript() {
        String scriptContent = "1+param";
        Map<String, Object> params = ImmutableMap.of("param", 2);

        Integer result = groovyScriptRunner.run(scriptContent, params, Integer.class);

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void run_mathFunctionsWithoutImports() {
        String scriptContent = "cos PI";

        Double result = groovyScriptRunner.run(scriptContent, emptyMap(), Double.class);

        assertThat(result).isEqualTo(-1);

    }

    @Test(expected = TimeoutException.class)
    public void run_throwTimeoutAfter500ms() {
        String script = "while(true){}";

        groovyScriptRunner.run(script, emptyMap(), Boolean.class);
    }

    @Test(expected = SecurityException.class)
    public void run_throwExceptionOnSystemClass() {
        String script = "System.exit(1)";

        groovyScriptRunner.run(script, emptyMap(), Void.class);
    }

    @Test
    public void run_extractValueFromMap() {
        String script = "payload.field1.field2";
        Map<String, Object> params = ImmutableMap.of(
                "payload", ImmutableMap.of(
                        "field1", ImmutableMap.of("field2", "value")));

        String result = groovyScriptRunner.run(script, params, String.class);

        assertThat(result).isEqualTo("value");
    }

}