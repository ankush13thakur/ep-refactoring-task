package com.elasticpath.refactoring.formatter;

import com.elasticpath.refactoring.PaymentMethodDetails;
import com.elasticpath.refactoring.enums.FulfillmentMessages;
import com.elasticpath.refactoring.enums.OrderStatus;
import com.elasticpath.refactoring.enums.PaymentAttributeKey;

import java.util.Locale;

public class ReturnAndExchangeFormatter implements PaymentDetailsFormatter {

    @Override
    public String format(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
        String paymentString = null;
        if (paymentMethodDetails.getOrderStatus() == OrderStatus.AWAITING_EXCHANGE) {
            paymentString = PaymentDetailsFormatter.resolveLocalizedString(FulfillmentMessages.EXCHANGE_PENDING, locale);
        } else if (paymentMethodDetails.getOrderStatus() != OrderStatus.AWAITING_EXCHANGE
                && paymentMethodDetails.getOrderStatus() != OrderStatus.CANCELLED) {
            paymentString = PaymentDetailsFormatter.resolveLocalizedString(FulfillmentMessages.EXCHANGE_COMPLETED, locale);
        }
        return paymentString;
    }
}
