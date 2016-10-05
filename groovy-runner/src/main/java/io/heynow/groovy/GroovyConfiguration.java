package io.heynow.groovy;

import com.google.common.collect.ImmutableMap;
import groovy.lang.GroovyShell;
import groovy.transform.ThreadInterrupt;
import groovy.transform.TimedInterrupt;
import groovy.util.logging.Log;
import org.codehaus.groovy.ast.tools.GeneralUtils;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.codehaus.groovy.control.customizers.CompilationCustomizer;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Configuration
@ConditionalOnClass(GroovyScriptRunner.class)
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = "io.heynow.groovy")
public class GroovyConfiguration {

    public static final String JAVA_LANG_MATH = "java.lang.Math";

    @Value("${groovy.script.timeout}")
    private Long timeoutValue;


    @Bean
    GroovyShell groovyShell(CompilerConfiguration configuration) {
        return new GroovyShell(configuration);
    }

    @Bean
    CompilerConfiguration configuration(CompilationCustomizer... customizers) {
        return new CompilerConfiguration().addCompilationCustomizers(customizers);
    }

    @Bean
    ASTTransformationCustomizer timedInterrupt() {
        Map<String, Object> params = ImmutableMap.<String, Object>builder().put("value", timeoutValue).put("unit",
                GeneralUtils.propX(GeneralUtils.classX(TimeUnit.class), MILLISECONDS.toString())).build();
        return new ASTTransformationCustomizer(params, TimedInterrupt.class);
    }

    @Bean
    ASTTransformationCustomizer threadInterrupt() {
        return new ASTTransformationCustomizer(ThreadInterrupt.class);
    }

    @Bean
    ASTTransformationCustomizer logger() {
        return new ASTTransformationCustomizer(Log.class);
    }

    @Bean
    ImportCustomizer importCustomizer() {
        return new ImportCustomizer().addStaticStars(JAVA_LANG_MATH);
    }

    @Bean
    SecureASTCustomizer secureCustomizer() {
        SecureASTCustomizer secureASTCustomizer = new SecureASTCustomizer();
        secureASTCustomizer.setReceiversClassesBlackList(Arrays.asList( Runtime.class, System.class));
        secureASTCustomizer.setStaticStarImportsWhitelist(Arrays.asList(JAVA_LANG_MATH));
        return secureASTCustomizer;
    }


}
