//package hristovski.nikola.shopping_cart.domain.service;
//
//import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
//import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
//import hristovski.nikola.shopping_cart.application.port.exception.MaxQuantityReachedException;
//import hristovski.nikola.shopping_cart.application.port.exception.MinQuantityReachedException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
//    @Override
//    public ShoppingCartItem getShoppingCardItem(ShoppingCartItemId shoppingCartItemId) {
//        return null;
//    }
//
//    @Override
//    public ShoppingCartItem addShoppingCardItem(ShoppingCartItem shoppingCartItem) {
//        return null;
//    }
//
//    @Override
//    public ShoppingCartItem editShoppingCardItem(ShoppingCartItem shoppingCartItem) {
//        return null;
//    }
//
//    @Override
//    public void deleteShoppingCardItem(ShoppingCartItemId shoppingCartItemId) {
//
//    }
//
//    @Override
//    public void incrementQuantity(ShoppingCartItemId id) throws MaxQuantityReachedException {
//
//    }
//
//    @Override
//    public void decrementQuantity(ShoppingCartItemId id) throws MinQuantityReachedException {
//
//    }
//
//
////    private final ShoppingCartItemRepository shoppingCartItemRepository;
////
////    @Override
////    public ShoppingCartItemEntity getShoppingCardItem(Long id) {
////        return shoppingCartItemRepository.getOne(id);
////    }
////
////    @Override
////    public ShoppingCartItemEntity addShoppingCardItem(ShoppingCartItemEntity shoppingCartItemEntity) {
////        return shoppingCartItemRepository.save(shoppingCartItemEntity);
////    }
////
////    @Override
////    public ShoppingCartItemEntity editShoppingCardItem(ShoppingCartItemEntity shoppingCartItemEntity) {
////        return shoppingCartItemRepository.save(shoppingCartItemEntity);
////    }
////
////    @Override
////    public void deleteShoppingCardItem(long shoppingCartItemId) {
////        shoppingCartItemRepository.deleteById(shoppingCartItemId);
////    }
////
////    @Override
////    public void incrementQuantity(Long id) throws MaxQuantityReachedException {
////        ShoppingCartItemEntity one = shoppingCartItemRepository.getOne(id);
////
////        if (one.getQuantity() == one.getProduct().getStock()){
////            throw new MaxQuantityReachedException();
////        }
////
////        one.setQuantity(one.getQuantity() + 1);
////        shoppingCartItemRepository.save(one);
////    }
////
////    @Override
////    public void decrementQuantity(Long id) throws MinQuantityReachedException {
////        ShoppingCartItemEntity one = shoppingCartItemRepository.getOne(id);
////
////        if (one.getQuantity() == 1){
////            throw new MinQuantityReachedException();
////        }
////
////        one.setQuantity(one.getQuantity() - 1);
////        shoppingCartItemRepository.save(one);
////    }
//
//}
