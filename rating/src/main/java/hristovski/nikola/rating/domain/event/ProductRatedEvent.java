//package hristovski.nikola.rating.domain.event;
//
//import hristovski.nikola.common.shared.domain.model.product.ProductId;
//import hristovski.nikola.generic_store.base.domain.DomainEvent;
//import hristovski.nikola.generic_store.base.domain.DomainObjectId;
//import lombok.Getter;
//
//import java.time.Instant;
//
//@Getter
//public class ProductRatedEvent implements DomainEvent {
//
//    private DomainObjectId id;
//    private ProductId productId;
//    private Integer rating;
//    private Instant occurredOn;
//    private String topic;
//
//    public ProductRatedEvent(ProductId productId, Integer rating, String topic) {
//        this.id = DomainObjectId.randomId(DomainObjectId.class);
//        this.productId = productId;
//        this.rating = rating;
//        this.topic = topic;
//        this.occurredOn = Instant.now();
//    }
//
//    @Override
//    public Instant occurredOn() {
//        return this.occurredOn;
//    }
//
//    @Override
//    public String topic() {
//        return this.topic;
//    }
//
//    @Override
//    public DomainObjectId id() {
//        return this.id;
//    }
//
//    @Override
//    public String message() {
//        return id().getId() + ";" + this.rating;
//    }
//}
