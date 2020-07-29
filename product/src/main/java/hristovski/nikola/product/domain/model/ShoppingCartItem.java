//package hristovski.nikola.product.domain.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import hristovski.nikola.product.domain.model.product.Product;
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.Instant;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class ShoppingCartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @OneToOne
//    private Product product;
//
//    private int quantity;
//
//    private boolean pending;
//
//    private boolean bought;
//    private Instant dateBought;
//
//    @Override
//    public int hashCode() {
//        return (int)id;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ShoppingCartItem that = (ShoppingCartItem) o;
//        return id == that.id;
//    }
//}
