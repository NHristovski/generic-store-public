package hristovski.nikola.encryption.application;

import hristovski.nikola.encryption.spring.EncryptionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({EncryptionConfiguration.class})
public class EncryptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncryptionApplication.class, args);
    }
}
