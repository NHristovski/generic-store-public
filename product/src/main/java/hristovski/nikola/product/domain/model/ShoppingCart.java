//package hristovski.nikola.product.domain.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Data
//public class ShoppingCart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String username;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ShoppingCartItem> shoppingCartItems;
//
//    public Double totalPrice(){
//        return shoppingCartItems.stream()
//                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
//                .sum();
//    }
//}
