package hristovski.nikola.common.shared.domain.model.all.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Embeddable
@MappedSuperclass
public class Quantity implements ValueObject {

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public Quantity() {
    }

    public Quantity(Long quantity) {
        Objects.requireNonNull(quantity, "Quantity must not be null!");
        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be positive number");
        }
        this.quantity = quantity;
    }

    public Quantity plus(long value) {
        return new Quantity(this.quantity + value);
    }

    public Quantity minus(long value) {
        return new Quantity(this.quantity - value);
    }
}
