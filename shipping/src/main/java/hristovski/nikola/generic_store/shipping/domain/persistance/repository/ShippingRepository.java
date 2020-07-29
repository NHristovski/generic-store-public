package hristovski.nikola.generic_store.shipping.domain.persistance.repository;

import hristovski.nikola.common.shared.domain.model.shipping.ShippingId;
import hristovski.nikola.generic_store.shipping.domain.persistance.entity.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, ShippingId> {
}
