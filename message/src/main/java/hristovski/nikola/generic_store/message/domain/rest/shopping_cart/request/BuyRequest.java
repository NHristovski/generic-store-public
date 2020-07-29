package hristovski.nikola.generic_store.message.domain.rest.shopping_cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO Sredi gi polinjava
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyRequest {
    private String shoppingCartId;
    private String cardHolderName;
    private String ccv;
    private String creditCard;
    private String deliveryAddress;
    private String expiryDate;
    private String phone;
}
