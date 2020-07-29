package hristovski.nikola.generic_store.shipping.domain.service;

import hristovski.nikola.common.shared.domain.model.shipping.ShippingId;
import hristovski.nikola.generic_store.shipping.domain.persistance.entity.ShippingEntity;
import hristovski.nikola.generic_store.shipping.domain.persistance.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    @Override
    public void pickUpOrder(ShippingId shippingId) {
        ShippingEntity shippingEntity = shippingRepository.findById(shippingId).orElseThrow(
                () -> new RuntimeException("Shipping with id " + shippingId + " not found.")
        );
        shippingEntity.pickUpOrder();

        shippingRepository.save(shippingEntity);
    }

    @Override
    public void orderDelivered(ShippingId shippingId) {
        ShippingEntity shippingEntity = shippingRepository.findById(shippingId).orElseThrow(
                () -> new RuntimeException("Shipping with id " + shippingId + " not found.")
        );
        shippingEntity.orderDelivered();

        shippingRepository.save(shippingEntity);
    }
}
