package hristovski.nikola.shopping_cart.domain.repository;

import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.shopping_cart.domain.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, ShoppingCartId> {
    ShoppingCartEntity findByApplicationUserId(ApplicationUserId applicationUserId);
}
