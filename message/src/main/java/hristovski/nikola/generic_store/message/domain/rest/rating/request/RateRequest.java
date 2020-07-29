package hristovski.nikola.generic_store.message.domain.rest.rating.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {
    @NonNull
    private String productId;
    @NonNull
    private String applicationUserId;
    @NonNull
    private Integer rating;
}
