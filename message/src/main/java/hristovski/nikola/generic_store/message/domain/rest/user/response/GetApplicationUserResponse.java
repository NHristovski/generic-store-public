package hristovski.nikola.generic_store.message.domain.rest.user.response;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetApplicationUserResponse {
    @NotNull
    private ApplicationUser user;
}
