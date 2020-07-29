//package hristovski.nikola.product.domain.service;
//
//import hristovski.nikola.product.domain.exception.FailedToBuyException;
//import hristovski.nikola.product.domain.exception.InvalidQuantityException;
//import hristovski.nikola.product.domain.exception.ProductNotFoundException;
//import hristovski.nikola.product.domain.model.ShoppingCart;
//import hristovski.nikola.product.domain.model.ShoppingCartItem;
//import hristovski.nikola.product.domain.model.category.Category;
//import hristovski.nikola.product.domain.model.product.AddProductRequest;
//import hristovski.nikola.product.domain.model.product.Product;
//import hristovski.nikola.product.domain.model.shoppingCart.BuyRequest;
//import hristovski.nikola.product.domain.repository.ShoppingCartRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ShoppingCartServiceImpl implements ShoppingCartService {
//
//    private final ShoppingCartRepository shoppingCartRepository;
//    private final ProductService productService;
//    private final ShoppingCartItemService shoppingCartItemService;
//    private final PaymentService paymentService;
//
//    @Override
//    public ShoppingCart getShoppingCart(Long shoppingCartId) {
//        return shoppingCartRepository.getOne(shoppingCartId);
//    }
//
//    @Override
//    public ShoppingCart getShoppingCart(String username) {
//
//        try {
//            ShoppingCart cart = shoppingCartRepository.findByUsername(username);
//
//            if (cart == null){
//                ShoppingCart shoppingCart = new ShoppingCart();
//                shoppingCart.setShoppingCartItems(new ArrayList<>());
//                shoppingCart.setUsername(username);
//
//                return addShoppingCart(shoppingCart);
//            }
//
//            return cart;
//        }catch (Exception ex){
//
//            ShoppingCart shoppingCart = new ShoppingCart();
//            shoppingCart.setShoppingCartItems(new ArrayList<>());
//            shoppingCart.setUsername(username);
//
//            return addShoppingCart(shoppingCart);
//        }
//
//    }
//
//    @Override
//    public ShoppingCart getShoppingCartWithPendingItems(String username) {
//        ShoppingCart shoppingCart = getShoppingCart(username);
//
//        List<ShoppingCartItem> allItems = shoppingCart.getShoppingCartItems();
//
//        List<ShoppingCartItem> pendingItems = allItems.stream().filter(ShoppingCartItem::isPending)
//                .collect(Collectors.toList());
//
//        ShoppingCart shoppingCart1 = new ShoppingCart();
//        shoppingCart1.setUsername(shoppingCart.getUsername());
//        shoppingCart1.setId(shoppingCart.getId());
//        shoppingCart1.setShoppingCartItems(pendingItems);
//
//        return shoppingCart1;
//    }
//
//    @Override
//    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
//        return shoppingCartRepository.save(shoppingCart);
//    }
//
//    @Override
//    public void editShoppingCart(ShoppingCart shoppingCart) {
//
//    }
//
//    @Override
//    public void deleteShoppingCart(Long shoppingCartId) {
//
//    }
//
//    @Override
//    public void addProductToShoppingCart(Long productId, Integer quantity, String username) throws ProductNotFoundException {
//
//
//        ShoppingCart shoppingCart = getShoppingCart(username);
//
//        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
//        shoppingCartItem.setQuantity(quantity);
//        shoppingCartItem.setProduct(productService.getById(productId));
//        shoppingCartItem.setPending(true);
//        shoppingCartItem.setBought(false);
//        shoppingCartItem.setDateBought(null);
//
//        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
//        if (shoppingCartItems == null){
//            shoppingCart.setShoppingCartItems(new ArrayList<>());
//        }
//
//        for (var item : shoppingCart.getShoppingCartItems()){
//            if (item.getProduct().getId().equals(productId) && item.isPending()){
//                item.setQuantity(item.getQuantity() + quantity);
//                shoppingCartRepository.save(shoppingCart);
//                return;
//            }
//        }
//
//        ShoppingCartItem item = shoppingCartItemService.addShoppingCardItem(shoppingCartItem);
//
//        shoppingCart.getShoppingCartItems().add(item);
//
//        shoppingCartRepository.save(shoppingCart);
//    }
//
//    @Override
//    public Double getShoppingCartPrice(Long shoppingCartId) {
//        return null;
//    }
//
//    @Override
//    public ShoppingCart getShoppingCartHistory(String username) {
//        ShoppingCart shoppingCart = getShoppingCart(username);
//
//        List<ShoppingCartItem> allItems = shoppingCart.getShoppingCartItems();
//
//        List<ShoppingCartItem> historyItems = allItems.stream().filter(ShoppingCartItem::isBought)
//                .collect(Collectors.toList());
//
//        Collections.reverse(historyItems);
//
//        ShoppingCart shoppingCartResult = new ShoppingCart();
//        shoppingCartResult.setUsername(shoppingCart.getUsername());
//        shoppingCartResult.setId(shoppingCart.getId());
//        shoppingCartResult.setShoppingCartItems(historyItems);
//
//        return shoppingCartResult;
//    }
//
//    @Override
//    public void deleteItem(Long shoppingCartId, Long itemId) {
//        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
//
//        int idx = -1;
//        List<ShoppingCartItem> items = shoppingCart.getShoppingCartItems();
//
//        for (int i = 0; i < items.size(); i++){
//            if (items.get(i).getId() == itemId){
//                idx = i;
//                break;
//            }
//        }
//
//        if (idx != -1) {
//            items.remove(idx);
//        }
//        log.info("Removed item on idx {}, items now {}",idx,items);
//        shoppingCartRepository.save(shoppingCart);
//
//        //shoppingCartItemService.deleteShoppingCardItem(itemId);
//    }
//
//    @Override
//    public void buy(BuyRequest buyRequest) throws InvalidQuantityException, FailedToBuyException, ProductNotFoundException {
//        ShoppingCart shoppingCart = getShoppingCart(buyRequest.getShoppingCartId());
//
//        if (!validShoppingCart(shoppingCart)){
//            throw new InvalidQuantityException();
//        }
//
//        boolean success = paymentService.payment(shoppingCart.totalPrice(),
//                buyRequest.getCardHolderName(),
//                buyRequest.getCreditCard(),
//                buyRequest.getExpiryDate(),
//                buyRequest.getCcv());
//
//        if (success){
//            buyHelper(shoppingCart);
//        }else{
//            throw new FailedToBuyException();
//        }
//
//    }
//
//    private void buyHelper(ShoppingCart shoppingCart) throws ProductNotFoundException {
//        for (var item: shoppingCart.getShoppingCartItems()) {
//            if (item.isPending()) {
//                item.setBought(true);
//                item.setDateBought(Instant.now());
//                item.setPending(false);
//
//                Product product = item.getProduct();
//                product.setStock(product.getStock() - item.getQuantity());
//
//                shoppingCartItemService.editShoppingCardItem(item);
//
//                AddProductRequest request = new AddProductRequest(product.getTitle(),
//                        product.getImageLocation(),
//                        product.getDescription(),
//                        product.getPrice(),
//                        product.getStock());
//
//                if (product.getCategories() != null) {
//                    request.setCategoryNames(product.getCategories().stream().map(Category::getCategoryName)
//                    .collect(Collectors.toList()));
//                }else{
//                    request.setCategoryNames(new ArrayList<>());
//                }
//
//                productService.editProduct(product.getId(), request);
//            }
//        }
//    }
//
//    private boolean validShoppingCart(ShoppingCart shoppingCart) throws ProductNotFoundException {
//        for (var item : shoppingCart.getShoppingCartItems()) {
//            if (item.isPending()) {
//
//                Product byId = productService.getById(item.getProduct().getId());
//                if (item.getQuantity() > byId.getStock()) {
//                    log.error("the item {} has quantity {} but product has stock {}"
//                            , item.getId(), item.getQuantity(), byId.getStock());
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//}
