package hristovski.nikola.generic_store.shipping.domain.service;

import hristovski.nikola.common.shared.domain.model.shipping.ShippingId;

public interface ShippingService {
    void pickUpOrder(ShippingId shippingId);
    void orderDelivered(ShippingId shippingId);
}
