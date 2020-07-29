package hristovski.nikola.generic_store.message.domain.rest.category.response;

import hristovski.nikola.common.shared.domain.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllCategoriesResponse {
    private List<Category> categories;
}
