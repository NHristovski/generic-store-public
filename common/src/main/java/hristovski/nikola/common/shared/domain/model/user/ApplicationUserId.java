package hristovski.nikola.common.shared.domain.model.user;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Embeddable
public class ApplicationUserId extends DomainObjectId {
    public ApplicationUserId() {
        super(DomainObjectId.randomId(ApplicationUserId.class).toString());
    }

    public ApplicationUserId(String id) {
        super(id);
    }
}


