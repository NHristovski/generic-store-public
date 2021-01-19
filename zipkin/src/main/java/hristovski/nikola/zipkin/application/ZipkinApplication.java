package hristovski.nikola.zipkin.application;


import hristovski.nikola.zipkin.spring.ZipkinConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@Import({ZipkinConfiguration.class})
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}

