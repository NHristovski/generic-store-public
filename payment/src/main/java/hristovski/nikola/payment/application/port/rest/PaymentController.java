package hristovski.nikola.payment.application.port.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    //TODO

    // 1. After creating account, fire event to create customer
    // 2. on buy enter card, get customer, create payment_method,
    // 3. CHECK THE DOCUMENTATION
//    private void createCustomer(){
//        CustomerCreateParams params =
//                CustomerCreateParams.builder()
//                        .setEmail("jenny.rosen@example.com")
//                        .setPaymentMethod("pm_1FWS6ZClCIKljWvsVCvkdyWg")
//                        .setInvoiceSettings(
//                                CustomerCreateParams.InvoiceSettings.builder()
//                                        .setDefaultPaymentMethod("pm_1FWS6ZClCIKljWvsVCvkdyWg")
//                                        .build())
//                        .build();
//
//    }
//
//    @GetMapping("/test")
//    public void test() {
//
//
//        Map<String, Object> card = new HashMap<>();
//        card.put("number", "4242424242424242");
//        card.put("exp_month", 7);
//        card.put("exp_year", 2021);
//        card.put("cvc", "314");
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("type", "card");
//        params.put("card", card);
//
//        try {
//            PaymentMethod paymentMethod =
//                    PaymentMethod.create(params);
//
//            log.info(paymentMethod.toJson());
//        } catch (StripeException exception) {
//            log.error("stripe ex: ", exception);
//        }
//
//        Customer.Req
//
//        ChargeCreateParams params1 = ChargeCreateParams.builder()
//                .setCustomer()
//                .set
//
//
//    }
//
//    @GetMapping("/c")
//    public void testc() {
//        Map<String, Object> params = new HashMap<>();
//        params.put(
//                "description",
//                "My First Test Customer (created for API docs)"
//        );
//
//        params.put("email", "nikolahristovski1122@gmail.com");
//
////        Customer customer = null;
////        try {
////            customer = Customer.create(params);
////
////            log.info(customer.toJson());
////        } catch (StripeException exception) {
////            log.error("stripe ex: ", exception);
////        }
//
//        InvoiceItemCreateParams params2 =
//                InvoiceItemCreateParams.builder()
//                        .setAmount(1000L)
//                        .setCurrency("USD")
//                        .setCustomer("cus_Hevo4DL9vkZmr1")
//                        .build();
//
//        try {
//            InvoiceItem invoiceItem = InvoiceItem.create(params2);
//            log.info(invoiceItem.toJson());
//        } catch (StripeException exception) {
//            log.error("invoice stripe ex: ", exception);
//        }
//    }
//
//    @GetMapping("/r")
//    public void r(){
//        PaymentIntentCreateParams params =
//                PaymentIntentCreateParams.builder()
//                        .setAmount(1099L)
//                        .setCurrency("usd")
//                        .addPaymentMethodType("card")
//                        .setReceiptEmail("nikolahristovski1122@gmail.com")
//                        .
//                        .build();
//
//        try {
//            PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//            log.info(paymentIntent.toJson());
//        } catch (Exception e){
//            log.error("receipt error", e);
//        }
//    }

}
