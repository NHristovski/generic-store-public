package hristovski.nikola.users.application.port.rest;

import hristovski.nikola.generic_store.message.domain.rest.user.request.RegisterRequest;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import hristovski.nikola.generic_store.message.domain.rest.user.response.RegisterResponse;
import hristovski.nikola.users.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
@Slf4j
public class PublicUserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {

        log.info("Register request");
        userService.registerUser(registerRequest);

        return ResponseEntity.ok().build();
    }

}
