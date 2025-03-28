package com.elasticpath.refactoring.formatter;

import com.elasticpath.refactoring.PaymentMethodDetails;
import com.elasticpath.refactoring.enums.PaymentAttributeKey;

import java.util.Locale;

public class PaypalExpressFormatter implements PaymentDetailsFormatter {

    @Override
    public String format(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
        return paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EMAIL);
    }
}
