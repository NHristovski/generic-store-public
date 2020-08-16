package hristovski.nikola.generic_store.message.domain.rest.payment.request;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChargeRequest {
    @NotNull
    private ShoppingCartId shoppingCartId;
    @NotNull
    private String cardHolderName;
    @NotNull
    private String ccv;
    @NotNull
    private String creditCard;
    @NotNull
    private String deliveryAddress;
    @NotNull
    private String expiryDate;
    @NotNull
    private String phone;
    @NotNull
    private Money price;

    public String expiryDateMonth() {
        // TODO FIX
        return "08";
    }

    public String expiryDateYear() {
        // TODO FIX
        return "2024";
    }
}
