package hristovski.nikola.product.domain.repository;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import hristovski.nikola.product.domain.model.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, ProductId> {

    Page<ProductEntity> queryProductsByDeletedFalseAndCategoriesIsContainingOrderByCreatedOnDesc
            (CategoryEntity categoryEntity, Pageable pageable);

    Page<ProductEntity> queryProductsByDeletedFalseAndCategoriesContainsOrderByCreatedOnDesc
            (CategoryEntity categoryEntity, Pageable pageable);

    Page<ProductEntity> findAllByDeletedFalseOrderByRatingStatisticsAverageRatingDesc(Pageable pageable);

    //Iterable<ProductEntity> findAllByDeletedFalseOrderByRatingStatisticsAverageRatingAsc();

    Page<ProductEntity> findAllByDeletedFalseOrderByCreatedOnDesc(Pageable pageable);
    // TODO CHECK THIS
    List<ProductEntity> findAllByDeletedFalseAndIdEqualsOrInformationTitleContainsOrderByCreatedOnDesc
    (ProductId id, String informationTitle);

    long countProductsByDeletedFalseAndCategoriesIsContaining(CategoryEntity categoryEntity);

    long countAllByDeletedFalse();

    Optional<ProductEntity> findByDeletedFalseAndIdEquals(ProductId id);

}
