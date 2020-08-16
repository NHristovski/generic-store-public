package hristovski.nikola.payment.application.port.rest;

import com.stripe.exception.StripeException;
import hristovski.nikola.common.shared.domain.constants.Headers;
import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.payment.request.ChargeRequest;
import hristovski.nikola.generic_store.message.domain.rest.payment.response.ChargeResponse;
import hristovski.nikola.payment.application.exception.PaymentException;
import hristovski.nikola.payment.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/charge")
    public ResponseEntity<ChargeResponse> charge(@Valid @RequestBody ChargeRequest chargeRequest,
                                                 HttpServletRequest httpServletRequest)
            throws PaymentException {

        log.info("Got charge request {}", chargeRequest);

        String userId = httpServletRequest.getHeader(Headers.USER_ID);

        try {
            paymentService.createCharge(chargeRequest, new ApplicationUserId(userId));

            return ResponseEntity.ok(ChargeResponse.builder().build());
        } catch (StripeException | RestRequestException exception) {
            throw new PaymentException("Failed to create the charge", exception);
        }
    }


}

