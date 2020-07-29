package hristovski.nikola.generic_store.auth.application.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * JWT Configuration
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "security.jwt")
@Component
public class JwtConfig {

    @NotNull
    private String uri;

    @NotNull
    private String header;

    @NotNull
    private String prefix;

    @NotNull
    private int expiration;

    @NotNull
    private String secret;
}
