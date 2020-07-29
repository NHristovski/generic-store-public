package hristovski.nikola.generic_store.base.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@Slf4j
public abstract class AbstractEntity<ID extends DomainObjectId> implements IdentifiableDomainObject<ID> {

    @EmbeddedId
    private ID id;

    public AbstractEntity() {

    }

    public AbstractEntity(ID id) {
        this.id=id;
    }

    @Override
    public ID id() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        boolean eq = id != null && id.equals(other.id);
        return eq;
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }

}
