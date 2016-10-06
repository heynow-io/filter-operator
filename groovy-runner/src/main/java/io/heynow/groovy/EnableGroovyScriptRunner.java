package io.heynow.groovy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Import(GroovyConfiguration.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface EnableGroovyScriptRunner {
}
