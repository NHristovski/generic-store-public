package hristovski.nikola.generic_store.message.domain.rest.rating.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCurrentRatingResponse {
    private Integer rating;
}
