package io.heynow.operator.filter.groovy;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scripting.groovy.GroovyScriptFactory;

import java.io.IOException;
import java.util.Collections;

public class GroovyScriptRunTest {


    @Test
    public void test() throws IOException {
        boolean result = new GroovyScriptRun(new GroovyScriptFactory("filter-script")).run(
                IOUtils.toString(new ClassPathResource("test.groovy").getInputStream(), "UTF-8"),
                Collections.emptyMap());
        Assert.assertFalse(result);
    }
}
