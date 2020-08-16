package hristovski.nikola.common.shared.domain.model.all.value;

import hristovski.nikola.common.shared.domain.validator.Validators;
import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Embeddable
@MappedSuperclass
@ToString
public class Quantity implements ValueObject {

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public Quantity() {
    }

    public Quantity(Long quantity) {
        Objects.requireNonNull(quantity, "Quantity must not be null!");

        this.quantity = Validators.requireNonNegative(quantity);
    }

    public Quantity plus(long value) {
        return new Quantity(this.quantity + value);
    }

    public Quantity minus(long value) {
        return new Quantity(this.quantity - value);
    }
}
