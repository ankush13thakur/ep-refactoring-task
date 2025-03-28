package com.elasticpath.refactoring;

import com.elasticpath.refactoring.enums.PaymentAttributeKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class FulfillmentPresenterTest {

    @Test
    void testGoogleCheckoutFormatting() {
        FulfillmentPresenter presenter = new FulfillmentPresenter();
        PaymentMethodDetails paymentMethodDetails = new PaymentMethodDetails();
        Map<PaymentAttributeKey, Object> map = new HashMap<>();
        map.put(PaymentAttributeKey.SHIPMENT_NUMBER, "Num123");
        map.put(PaymentAttributeKey.EMAIL, "ankush13thakur@gmail.com");
        paymentMethodDetails.setAttributeMap(map);
        paymentMethodDetails.setPaymentType("GOOGLE_CHECKOUT");
        String result = presenter.getPaymentString(paymentMethodDetails);
        Assertions.assertEquals("Google Checkout Num123 (ankush13thakur@gmail.com)", result, "Test failed");
    }

    @Test
    void testGiftCertificateFormatting() {
        FulfillmentPresenter presenter = new FulfillmentPresenter();
        PaymentMethodDetails paymentMethodDetails = new PaymentMethodDetails();
        Map<PaymentAttributeKey, Object> map = new HashMap<>();
        map.put(PaymentAttributeKey.GIFT_CERTIFICATE_CODE, "Num123");
        paymentMethodDetails.setAttributeMap(map);
        paymentMethodDetails.setPaymentType("GIFT_CERTIFICATE");
        String result = presenter.getPaymentString(paymentMethodDetails);
        Assertions.assertEquals("Gift Certificate: Num123", result, "Test failed");
    }

}
