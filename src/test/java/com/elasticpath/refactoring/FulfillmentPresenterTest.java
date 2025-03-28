package com.elasticpath.refactoring;

import com.elasticpath.refactoring.enums.PaymentAttributeKey;
import com.elasticpath.refactoring.formatter.PaymentDetailsFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Locale;
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

    @Test
    void testCardFormatting() {
        FulfillmentPresenter presenter = new FulfillmentPresenter();
        PaymentMethodDetails paymentMethodDetails = new PaymentMethodDetails();
        Map<PaymentAttributeKey, Object> map = new HashMap<>();
        map.put(PaymentAttributeKey.CARD_TYPE, "Visa");
        map.put(PaymentAttributeKey.PAN, "PAN123");
        map.put(PaymentAttributeKey.EXPIRY_MONTH, "01");
        map.put(PaymentAttributeKey.EXPIRY_YEAR, "2028");
        paymentMethodDetails.setAttributeMap(map);
        String result = presenter.getPaymentString(paymentMethodDetails);
        Assertions.assertEquals("Visa: PAN123 Expiry: 01/2028", result, "Test failed");
    }

    /**
     * Test case to demonstrate how customer can register their own custom formatter without modifying the source code
     */
    @Test
    void testCustomPaymentDetailsFormatting() {
        FulfillmentPresenter presenter = new FulfillmentPresenter();
        presenter.registerFormatter("CUSTOM", new CustomFormatter());
        PaymentMethodDetails paymentMethodDetails = new PaymentMethodDetails();
        paymentMethodDetails.setPaymentType("CUSTOM");
        String result = presenter.getPaymentString(paymentMethodDetails);
        Assertions.assertEquals("Custom formatting", result, "Test failed");
    }

    private class CustomFormatter implements PaymentDetailsFormatter {
        @Override
        public String format(PaymentMethodDetails paymentMethodDetails, Locale locale) {
            return "Custom formatting";
        }
    }

}
