package com.elasticpath.refactoring.formatter;

import com.elasticpath.refactoring.PaymentMethodDetails;
import com.elasticpath.refactoring.enums.FulfillmentMessages;
import com.elasticpath.refactoring.enums.PaymentAttributeKey;

import java.util.Locale;

public class GoogleCheckoutFormatter implements PaymentDetailsFormatter {

    @Override
    public String format(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
        final String shipmentNumber = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.SHIPMENT_NUMBER);
        final String email = paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EMAIL);
        return PaymentDetailsFormatter.resolveLocalizedString(FulfillmentMessages.GOOGLE, locale, shipmentNumber, email);
    }
}
