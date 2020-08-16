package hristovski.nikola.payment.domain.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.InvoiceItem;
import com.stripe.model.PaymentMethod;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUser;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.payment.request.ChargeRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface PaymentService {

    void createCharge(ChargeRequest chargeRequest, ApplicationUserId userId) throws StripeException, RestRequestException;

    InvoiceItem createInvoice(ChargeRequest chargeRequest, Customer customer) throws StripeException;

    Customer createOrGetCustomer(ApplicationUser applicationUser,
                                 ChargeRequest chargeRequest,
                                 PaymentMethod paymentMethod)
            throws StripeException;

    PaymentMethod createPaymentMethod(@RequestBody @Valid ChargeRequest chargeRequest) throws
            StripeException;
}
