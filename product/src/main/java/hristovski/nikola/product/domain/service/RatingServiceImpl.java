//package hristovski.nikola.product.domain.service;
//
//import hristovski.nikola.common.shared.domain.model.product.ProductId;
//import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
//import hristovski.nikola.product.domain.model.rating.RatingEntity;
//import hristovski.nikola.product.domain.repository.RatingRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class RatingServiceImpl implements RatingService {
//
//    private final RatingRepository ratingRepository;
//
//    @Override
//    public RatingEntity getCurrentRating(ProductId productId, ApplicationUserId userId) {
//        return ratingRepository.getRatingByProductIdAndApplicationUserId(productId, userId);
//    }
//
//    @Override
//    public Double getAverageRating(ProductId productId) {
//        return ratingRepository.queryAllByProductId(productId)
//                .stream()
//                .mapToDouble(RatingEntity::getRating)
//                .average()
//                .orElseThrow(() -> new RuntimeException(
//                        "Failed to obtain average rating for pruduct " + productId.getId())
//                );
//    }
//
//    @Override
//    public Long getTotalRating(ProductId productId) {
//        return ratingRepository.countAllByProductId(productId);
//    }
//
//    @Override
//    public RatingEntity rate(ProductId productId, ApplicationUserId userId, Integer rating) {
//        RatingEntity ratingEntity = new RatingEntity(productId, userId, rating);
//
//        RatingEntity savedRatingEntity = ratingRepository.save(ratingEntity);
//
//        // TODO SEND EVENT
//
//        return savedRatingEntity;
//    }
//
////    @Override
////    public ProductResponse getCurrentRating(ProductEntity productEntity, String username) {
////        RatingEntity ratingEntity = ratingRepository.getRatingByProductIdAndApplicationUserId(productEntity, username);
////        List<RatingEntity> ratingEntities = ratingRepository.queryAllByProductId(productEntity);
////
////        Double average = ratingEntities
////                .stream()
////                .mapToDouble(RatingEntity::getRating)
////                .average().orElse(0d);
////
////        return new ProductResponse(productEntity, ratingEntity.getRating(),average, ratingEntities.size());
////    }
////
////    @Override
////    public Double getAverageRating(ProductEntity productEntity) {
////        List<RatingEntity> ratingEntities = ratingRepository.queryAllByProductId(productEntity);
////
////        return ratingEntities
////                .stream()
////                .mapToDouble(RatingEntity::getRating)
////                .average().orElse(0d);
////    }
////
////    @Override
////    public Integer getTotalRating(ProductEntity productEntity) {
////        List<RatingEntity> ratingEntities = ratingRepository.queryAllByProductId(productEntity);
////        return ratingEntities.size();
////    }
////
////    @Override
////    public ProductResponse rate(ProductEntity productEntity, Integer ratingScore, String username) {
////
////        try {
////            RatingEntity oldRatingEntity = ratingRepository.findByProductAndUsername(productEntity, username);
////            if (oldRatingEntity != null){
////                oldRatingEntity.setRating(ratingScore);
////                ratingRepository.save(oldRatingEntity);
////            }else{
////                RatingEntity ratingEntity = new RatingEntity();
////                ratingEntity.setProduct(productEntity);
////                ratingEntity.setRating(ratingScore);
////                ratingEntity.setUsername(username);
////                ratingRepository.save(ratingEntity);
////            }
////        }catch (Exception ex) {
////
////            RatingEntity ratingEntity = new RatingEntity();
////            ratingEntity.setProduct(productEntity);
////            ratingEntity.setRating(ratingScore);
////            ratingEntity.setUsername(username);
////            ratingRepository.save(ratingEntity);
////        }
////
////        return getCurrentRating(productEntity,username);
////    }
//}
