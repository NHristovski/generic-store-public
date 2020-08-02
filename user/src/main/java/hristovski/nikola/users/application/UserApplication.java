package hristovski.nikola.users.application;

import hristovski.nikola.users.spring.UserConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import hristovski.nikola.encryption.spring.EncryptionConfiguration;

@SpringBootApplication
@Import({UserConfiguration.class, EncryptionConfiguration.class})
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
