package hristovski.nikola.users.application;

import hristovski.nikola.encryption.spring.EncryptionConfiguration;
import hristovski.nikola.users.spring.KafkaConfiguration;
import hristovski.nikola.users.spring.UserConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({UserConfiguration.class, EncryptionConfiguration.class, KafkaConfiguration.class})
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
