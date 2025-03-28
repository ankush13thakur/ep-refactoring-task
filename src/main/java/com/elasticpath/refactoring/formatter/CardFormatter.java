package com.elasticpath.refactoring.formatter;

import com.elasticpath.refactoring.AuthorizationService;
import com.elasticpath.refactoring.PaymentMethodDetails;
import com.elasticpath.refactoring.enums.FulfillmentMessages;
import com.elasticpath.refactoring.enums.FulfillmentPermissions;
import com.elasticpath.refactoring.enums.PaymentAttributeKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardFormatter implements PaymentDetailsFormatter {

    @Override
    public String format(final PaymentMethodDetails paymentMethodDetails, final Locale locale) {
        final List<String> bindings = new ArrayList<>();
        bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.CARD_TYPE));
        // This determines if the user is authorized to see the full credit card number or just a masked version
        if (AuthorizationService.getInstance().isAuthorized(FulfillmentPermissions.VIEW_FULL_CREDITCARD_NUMBER)) {
            bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.PAN));
        } else {
            bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.MASKED_PAN));
        }
        bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EXPIRY_MONTH));
        bindings.add(paymentMethodDetails.getAttributeAsString(PaymentAttributeKey.EXPIRY_YEAR));
        return PaymentDetailsFormatter.resolveLocalizedString(FulfillmentMessages.CARD_DESCRIPTION, locale, bindings.toArray());
    }
}
