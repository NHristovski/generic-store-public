package hristovski.nikola.product.domain.persistance.repository;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.product.domain.persistance.entity.CategoryEntity;
import hristovski.nikola.product.domain.persistance.entity.ProductEntity;
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
    List<ProductEntity> findAllByDeletedFalseAndIdEqualsOrDeletedFalseAndInformationTitleContainsIgnoreCaseOrderByCreatedOnDesc
    (ProductId id, String informationTitle);

    long countProductsByDeletedFalseAndCategoriesIsContaining(CategoryEntity categoryEntity);

    long countAllByDeletedFalse();

    Optional<ProductEntity> findByDeletedFalseAndIdEquals(ProductId id);

}
