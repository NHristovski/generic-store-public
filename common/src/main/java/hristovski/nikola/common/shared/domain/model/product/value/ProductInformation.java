package hristovski.nikola.common.shared.domain.model.product.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ProductInformation implements ValueObject {
    @Column(name = "product_title", nullable = false)
    private String title;
    @Column(name = "product_short_desc", nullable = false)
    private String shortDescription;
    @Column(name = "product_long_desc", nullable = false, length = 4000)
    private String longDescription;
}
