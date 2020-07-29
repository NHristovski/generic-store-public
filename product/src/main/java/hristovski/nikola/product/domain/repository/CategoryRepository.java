package hristovski.nikola.product.domain.repository;

import hristovski.nikola.common.shared.domain.model.category.CategoryId;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, CategoryId> {

    Optional<CategoryEntity> findByDeletedFalseAndIdEquals(CategoryId categoryId);

    List<CategoryEntity> findAllByDeletedFalse();
}
