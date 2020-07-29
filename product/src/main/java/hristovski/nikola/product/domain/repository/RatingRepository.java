//package hristovski.nikola.product.domain.repository;
//
//import hristovski.nikola.common.shared.domain.model.product.ProductId;
//import hristovski.nikola.common.shared.domain.model.rating.RatingId;
//import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
//import hristovski.nikola.product.domain.model.rating.RatingEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {
//
//    RatingEntity getRatingByProductIdAndApplicationUserId(ProductId productId, ApplicationUserId applicationUserId);
//
//    List<RatingEntity> queryAllByProductId(ProductId productId);
//
//    long countAllByProductId(ProductId productId);
//    //RatingEntity findByProductAndUsername(ProductEntity productEntity, String username);
//}