//package hristovski.nikola.shopping_cart.domain.model.value;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.lang.NonNull;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Transient;
//import java.time.Instant;
//import java.util.Objects;
//
//@Embeddable
//@MappedSuperclass
//public class ShoppingCartItemStatus {
//
//    @Column(name = "status", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Status status;
//
//    @Column(name = "date_bought")
//    private Instant dateBought;
//
//    public ShoppingCartItemStatus(){
//        this.status = Status.IN_SHOPPING_CART;
//        dateBought = null;
//    }
//
//    public ShoppingCartItemStatus(@NonNull Instant dateBought){
//        Objects.requireNonNull(dateBought,"dateBought must not be null!");
//        this.status = Status.BOUGHT;
//        this.dateBought = dateBought;
//    }
//
//    @JsonIgnore
//    @Transient
//    public boolean isBought(){
//        return this.status.equals(Status.BOUGHT);
//    }
//
//    @JsonIgnore
//    @Transient
//    public boolean isInShoppingCart(){
//        return this.status.equals(Status.IN_SHOPPING_CART);
//    }
//
//    @JsonIgnore
//    @Transient
//    public boolean isCancelled(){
//        return this.status.equals(Status.CANCELLED);
//    }
//
//    public ShoppingCartItemStatus buyItem(){
//        return new ShoppingCartItemStatus(Instant.now());
//    }
//}
