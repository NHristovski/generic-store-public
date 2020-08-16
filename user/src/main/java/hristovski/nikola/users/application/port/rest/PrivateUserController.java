package hristovski.nikola.users.application.port.rest;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import hristovski.nikola.users.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
@Slf4j
public class PrivateUserController {

    private final UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<GetApplicationUserResponse> getUserByUsername(@PathVariable String username) {
        log.info("Rest request for getUserByUsername {}", username);
        return ResponseEntity.ok(userService.getUser(username));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetApplicationUserResponse> getUserById(@PathVariable String userId) {
        log.info("Rest request for getUserById {}", userId);
        return ResponseEntity.ok(userService.getUser(new ApplicationUserId(userId)));
    }
}
