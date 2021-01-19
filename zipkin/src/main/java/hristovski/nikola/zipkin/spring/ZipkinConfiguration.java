package hristovski.nikola.zipkin.spring;

import hristovski.nikola.zipkin.ZipkinComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ZipkinComponents.class})
@Slf4j
public class ZipkinConfiguration {
    public ZipkinConfiguration() {
        log.info("Zipkin configuration initialized.");
    }
}
