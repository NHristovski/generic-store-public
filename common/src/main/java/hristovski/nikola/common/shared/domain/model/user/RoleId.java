package hristovski.nikola.common.shared.domain.model.user;

import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class RoleId extends DomainObjectId {

    public RoleId() {
        super(DomainObjectId.randomId(RoleId.class).toString());
    }

    public RoleId(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
