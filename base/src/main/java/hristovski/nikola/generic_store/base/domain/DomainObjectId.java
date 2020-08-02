package hristovski.nikola.generic_store.base.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.NonNull;

import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class DomainObjectId implements ValueObject {
    private String id;

    public DomainObjectId(String id) {
        this.id = id;
    }

    /**
     * Creates a new, random instance of the given {@code idClass}.
     */
    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }

        DomainObjectId that = (DomainObjectId) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
