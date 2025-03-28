package com.elasticpath.refactoring.formatter;

import com.elasticpath.refactoring.PaymentMethodDetails;
import com.elasticpath.refactoring.enums.FulfillmentMessages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public interface PaymentDetailsFormatter {

    /**
     * Format the payment details.
     *
     * @param paymentMethodDetails details of the payment method.
     * @return formatted payment string.
     */
    String format(final PaymentMethodDetails paymentMethodDetails, final Locale locale);

    public static String resolveLocalizedString(final FulfillmentMessages msg, final Locale locale, final Object... bindings) {
        final ResourceBundle bundle = PropertyResourceBundle.getBundle("messages", locale);
        final String key = bundle.getString(msg.getMessageKey());
        return MessageFormat.format(key, bindings);
    }
}
