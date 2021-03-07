package hristovski.nikola.payment.domain.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.InvoiceItem;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.InvoiceItemCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import hristovski.nikola.generic_store.message.domain.event.ShoppingCartBoughtEvent;
import hristovski.nikola.generic_store.message.domain.event.StripeCustomerRegisteredEvent;
import hristovski.nikola.generic_store.message.domain.rest.payment.request.ChargeRequest;
import hristovski.nikola.payment.domain.remote.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final DomainEventPublisher domainEventPublisher;
    private final UserService userService;

    @Override
    public void createCharge(ChargeRequest chargeRequest, ApplicationUserId applicationUserId)
            throws StripeException, RestRequestException {

        ApplicationUser user;
        try {
            user = userService.getUserById(applicationUserId);
        } catch (Exception ex) {
            throw new RestRequestException("Failed to get user info", ex);
        }

        PaymentMethod paymentMethod = createPaymentMethod(chargeRequest);
        log.info("PaymentMethod {}", paymentMethod.getId());

        Customer customer = createOrGetCustomer(user, chargeRequest, paymentMethod);
        log.info("Customer {}", customer.getId());

        PaymentIntent paymentIntent = createPayment(chargeRequest, customer);
        log.info("PaymentIntent {}", paymentIntent.getId());

        InvoiceItem invoice = createInvoice(chargeRequest, customer);
        log.info("Invoice {}", invoice.getId());

        domainEventPublisher.publish(new ShoppingCartBoughtEvent(
                chargeRequest.getShoppingCartId(),
                new Address(chargeRequest.getDeliveryAddress(), " ", " ", " ")
        ));
    }

    private PaymentIntent createPayment(ChargeRequest chargeRequest, Customer customer) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setCustomer(customer.getId())
                        .setAmount(chargeRequest.getPrice().getAmount().longValue() * 100)
                        .setCurrency(chargeRequest.getPrice().getCurrency().name())
                        .addPaymentMethodType("card")
                        .build();

        return PaymentIntent.create(params);
    }

    @Override
    public InvoiceItem createInvoice(ChargeRequest chargeRequest, Customer customer) throws StripeException {
        InvoiceItemCreateParams invoiceItemCreateParams =
                InvoiceItemCreateParams.builder()
                        .setAmount(chargeRequest.getPrice().getAmount().longValue())
                        .setCurrency(chargeRequest.getPrice().getCurrency().name())
                        .setCustomer(customer.getId())
                        .build();

        return InvoiceItem.create(invoiceItemCreateParams);
    }

    @Override
    public Customer createOrGetCustomer(ApplicationUser applicationUser, ChargeRequest chargeRequest, PaymentMethod paymentMethod)
            throws StripeException {

        if (applicationUser.getCustomerId() == null) {

            log.info("Registering user {} as customer", applicationUser.getApplicationUserId().getId());

            CustomerCreateParams.Shipping shipping = CustomerCreateParams.Shipping.builder()
                    .setAddress(CustomerCreateParams.Shipping.Address.builder()
                            .setLine1(chargeRequest.getDeliveryAddress()).build()
                    ).setName(applicationUser.getName().fullName())
                    .setPhone(chargeRequest.getPhone())
                    .build();

            CustomerCreateParams customerCreateParams =
                    CustomerCreateParams.builder()
                            .setShipping(shipping)
                            .setEmail(applicationUser.getEmail().getEmail())
                            .setPaymentMethod(paymentMethod.getId())
                            .setInvoiceSettings(
                                    CustomerCreateParams.InvoiceSettings.builder()
                                            .setDefaultPaymentMethod(paymentMethod.getId())
                                            .build())
                            .build();

            Customer newCustomer = Customer.create(customerCreateParams);

            domainEventPublisher.publish(new StripeCustomerRegisteredEvent(
                    applicationUser.getApplicationUserId(),
                    newCustomer.getId()
            ));

            return newCustomer;
        } else {
            log.info("{} is already a customer", applicationUser.getApplicationUserId().getId());
            return Customer.retrieve(applicationUser.getCustomerId());
        }
    }

    @Override
    public PaymentMethod createPaymentMethod(ChargeRequest chargeRequest) throws
            StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", chargeRequest.getCreditCard());
        card.put("exp_month", chargeRequest.expiryDateMonth());
        card.put("exp_year", chargeRequest.expiryDateYear());
        card.put("cvc", chargeRequest.getCcv());
        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", card);

        return PaymentMethod.create(params);
    }
}
