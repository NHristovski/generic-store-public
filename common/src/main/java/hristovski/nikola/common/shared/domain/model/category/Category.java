package hristovski.nikola.common.shared.domain.model.category;

import hristovski.nikola.common.shared.domain.model.all.value.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private CategoryId categoryId;

    private Long version;

    private Name categoryName;
}
