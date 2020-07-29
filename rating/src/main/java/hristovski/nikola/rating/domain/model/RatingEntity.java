package hristovski.nikola.rating.domain.model;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.rating.RatingId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

// TODO MOVE IN DIFFERENT MODULE
@Data
@Entity
@Table(name = "rating")
@NoArgsConstructor
@AllArgsConstructor
public class RatingEntity extends AbstractEntity<RatingId> {
    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private ApplicationUserId applicationUserId;

    private Integer rating;

    public RatingEntity(ProductId productId, ApplicationUserId userId, Integer rating) {
        super(DomainObjectId.randomId(RatingId.class));
        this.version = 0L;
        this.productId = productId;
        this.applicationUserId = userId;
        this.rating = rating;
    }
}
