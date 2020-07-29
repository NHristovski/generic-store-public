package hristovski.nikola.product.domain.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.common.shared.domain.model.category.Category;
import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Data
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity extends AbstractEntity<CategoryId> {

    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "category_name", nullable = false, unique = true))
    private Name categoryName;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductEntity> productEntities;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public CategoryEntity(Name categoryName) {
        super(DomainObjectId.randomId(CategoryId.class));
        this.categoryName = categoryName;
        this.version = 0L;
        productEntities = new HashSet<>();
        deleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryEntity categoryEntity = (CategoryEntity) o;
        return Objects.equals(getId(), categoryEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void delete() {
        this.deleted = TRUE;
    }

    public Category toCategory() {
        return new Category(
                this.id(),
                this.getVersion(),
                this.getCategoryName()
        );
    }
}
