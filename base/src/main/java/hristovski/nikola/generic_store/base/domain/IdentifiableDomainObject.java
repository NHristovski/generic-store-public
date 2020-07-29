package hristovski.nikola.generic_store.base.domain;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public interface IdentifiableDomainObject<ID extends Serializable> extends DomainObject  {

    @Nullable
    ID id();
}
