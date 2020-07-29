//package hristovski.nikola.product.domain.service;
//
//import hristovski.nikola.product.domain.exception.MaxQuantityReachedException;
//import hristovski.nikola.product.domain.exception.MinQuantityReachedException;
//import hristovski.nikola.product.domain.model.ShoppingCartItem;
//import hristovski.nikola.product.domain.repository.ShoppingCartItemRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
//
//    private final ShoppingCartItemRepository shoppingCartItemRepository;
//
//    @Override
//    public ShoppingCartItem getShoppingCardItem(Long id) {
//        return shoppingCartItemRepository.getOne(id);
//    }
//
//    @Override
//    public ShoppingCartItem addShoppingCardItem(ShoppingCartItem shoppingCartItem) {
//        return shoppingCartItemRepository.save(shoppingCartItem);
//    }
//
//    @Override
//    public ShoppingCartItem editShoppingCardItem(ShoppingCartItem shoppingCartItem) {
//        return shoppingCartItemRepository.save(shoppingCartItem);
//    }
//
//    @Override
//    public void deleteShoppingCardItem(long shoppingCartItemId) {
//        shoppingCartItemRepository.deleteById(shoppingCartItemId);
//    }
//
//    @Override
//    public void incrementQuantity(Long id) throws MaxQuantityReachedException {
//        ShoppingCartItem one = shoppingCartItemRepository.getOne(id);
//
//        if (one.getQuantity() == one.getProduct().getStock()){
//            throw new MaxQuantityReachedException();
//        }
//
//        one.setQuantity(one.getQuantity() + 1);
//        shoppingCartItemRepository.save(one);
//    }
//
//    @Override
//    public void decrementQuantity(Long id) throws MinQuantityReachedException {
//        ShoppingCartItem one = shoppingCartItemRepository.getOne(id);
//
//        if (one.getQuantity() == 1){
//            throw new MinQuantityReachedException();
//        }
//
//        one.setQuantity(one.getQuantity() - 1);
//        shoppingCartItemRepository.save(one);
//    }
//
//}
