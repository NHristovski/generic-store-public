package hristovski.nikola.encryption.spring;

import hristovski.nikola.encryption.EncryptionComponents;
import hristovski.nikola.encryption.application.configuration.EncryptionProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = EncryptionComponents.class)
@Slf4j
public class EncryptionConfiguration {

    private final EncryptionProperties encryptionProperties;

    public EncryptionConfiguration(EncryptionProperties encryptionProperties) {

        this.encryptionProperties = encryptionProperties;

        log.info("Encryption configuration initialized.");
    }

    @Bean
    public AES256TextEncryptor encryptor() {
        AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword(encryptionProperties.getKey());

        return aesEncryptor;
    }
}
