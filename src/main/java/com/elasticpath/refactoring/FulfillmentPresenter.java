package com.elasticpath.refactoring;

import com.elasticpath.refactoring.formatter.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FulfillmentPresenter {

    private final Map<String, PaymentDetailsFormatter> formatters = new HashMap<>();

    public FulfillmentPresenter() {
        formatters.put("GOOGLE_CHECKOUT", new GoogleCheckoutFormatter());
        formatters.put("GIFT_CERTIFICATE", new GiftCertificateFormatter());
        formatters.put("PAYPAL_EXPRESS", new PaypalExpressFormatter());
        formatters.put("RETURN_AND_EXCHANGE", new ReturnAndExchangeFormatter());
    }

    /**
     * Allows customers to register their own PaymentDetailsFormatter.
     *
     * @param paymentType
     * @param formatter
     */
    public void registerFormatter(String paymentType, PaymentDetailsFormatter formatter) {
        formatters.put(paymentType, formatter);
    }

    /**
     * Converts an PaymentMethodDetails object into a human-readable string representation.
     *
     * @param paymentMethodDetails a DTO representing the payment details
     * @return human-readable string description of the payment method
     */
    public String getPaymentString(final PaymentMethodDetails paymentMethodDetails) {
        return getPaymentString(paymentMethodDetails, Locale.getDefault());
    }

    /**
     * Converts an PaymentMethodDetails object into a human-readable string representation.
     *
     * @param paymentMethodDetails a DTO representing the payment details
     * @param locale               the user's current locale
     * @return human-readable string description of the payment method
     */
    public String getPaymentString(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
        String paymentString = "";
        if (paymentMethodDetails != null) {
			PaymentDetailsFormatter formatter = formatters.get(paymentMethodDetails.getPaymentType());
			if (formatter == null) {
				formatter = new CardFormatter();
			}
			paymentString = formatter.format(paymentMethodDetails, locale);
        }
        return paymentString;
    }

}
