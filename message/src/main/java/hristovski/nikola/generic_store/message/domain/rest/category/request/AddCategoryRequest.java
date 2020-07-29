package hristovski.nikola.generic_store.message.domain.rest.category.request;

import hristovski.nikola.common.shared.domain.model.all.value.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {

    @NotNull
    Name categoryName;
}
