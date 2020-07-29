package hristovski.nikola.generic_store.auth.application;

import hristovski.nikola.generic_store.auth.spring.AuthConfiguration;
import hristovski.nikola.encryption.spring.EncryptionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({AuthConfiguration.class, EncryptionConfiguration.class})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
