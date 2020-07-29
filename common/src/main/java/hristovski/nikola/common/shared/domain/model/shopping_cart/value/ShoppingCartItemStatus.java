//package hristovski.nikola.common.shared.domain.model.shopping_cart.value;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Transient;
//import java.time.Instant;
//import java.util.Objects;
// TODO THIS SHOULD BE COMMENTED
//@Embeddable
//@MappedSuperclass
//public class ShoppingCartItemStatus {
//
//    @Column(name = "status", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private final Status status;
//
//    @Column(name = "date_bought")
//    private final Instant dateBought;
//
//    public ShoppingCartItemStatus() {
//        this.status = Status.PENDING;
//        dateBought = null;
//    }
//
//    public ShoppingCartItemStatus(Instant dateBought) {
//        Objects.requireNonNull(dateBought, "dateBought must not be null!");
//        this.status = Status.BOUGHT;
//        this.dateBought = dateBought;
//    }
//
//    @JsonIgnore
//    @Transient
//    public boolean isBought() {
//        return this.status.equals(Status.BOUGHT);
//    }
//
//    @JsonIgnore
//    @Transient
//    public boolean isPending() {
//        return this.status.equals(Status.PENDING);
//    }
//
//    @JsonIgnore
//    @Transient
//    public boolean isCancelled() {
//        return this.status.equals(Status.CANCELLED);
//    }
//
//    public ShoppingCartItemStatus buyItem() {
//        return new ShoppingCartItemStatus(Instant.now());
//    }
//}
