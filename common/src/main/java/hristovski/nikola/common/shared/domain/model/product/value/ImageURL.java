package hristovski.nikola.common.shared.domain.model.product.value;

import hristovski.nikola.generic_store.base.domain.ValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ImageURL implements ValueObject {
    @Column(name = "image_url", nullable = false)
    private String url;
}
