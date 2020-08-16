package hristovski.nikola.common.shared.domain.model.shipping.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Address implements ValueObject {
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "postal_code", nullable = false)
    private String postalCode;
}




