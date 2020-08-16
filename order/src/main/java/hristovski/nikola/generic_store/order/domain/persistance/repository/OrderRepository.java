package hristovski.nikola.generic_store.order.domain.persistance.repository;

import hristovski.nikola.common.shared.domain.model.shipping.OrderId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.order.domain.persistance.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, OrderId> {
    List<OrderEntity> findAllByUserIdEqualsOrderByCreatedOnDesc(ApplicationUserId applicationUserId);

    List<OrderEntity> findAllByIdIdContainsIgnoreCaseOrUserIdIdContainsIgnoreCaseOrderByCreatedOnDesc
            (String query1, String query2);

    List<OrderEntity> findAllByOrderByCreatedOnDesc();
}
